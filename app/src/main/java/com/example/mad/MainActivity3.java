package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {


    TextView Weight,height,budget,name,email,phno,nic,age,gender,pname;


    Nutrition nutrition;
    register Register;
    DatabaseReference ref;
    DatabaseReference ref1;
    private Button button6;

    public void clearControls(){
        height.setText("");
        Weight.setText("");
        budget.setText("");
        name.setText("");
        email.setText("");
        phno.setText("");
        nic.setText("");
        age.setText("");

        gender.setText("");
        pname.setText("");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        height = findViewById(R.id.txtheight1);
        Weight = findViewById(R.id.txtweight1);
        budget = findViewById(R.id.txtbudget1);
        name = findViewById(R.id.Esname);
        email = findViewById(R.id.Esemail);
        phno = findViewById(R.id.EsphNo);
        nic = findViewById(R.id.Esnic);
        age = findViewById(R.id.Esage);

        gender = findViewById(R.id.gender);
        pname=findViewById(R.id.EPPName);


        nutrition = new Nutrition();
        Register = new register();
        ref = FirebaseDatabase.getInstance().getReference().child("Nutrition");

        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){

            DatabaseReference delRef =FirebaseDatabase.getInstance().getReference().child("Nutrition");
            delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild("Nutrition")) {
                        ref = FirebaseDatabase.getInstance().getReference().child("Nutrition").child("Nutrition");
                        ref.removeValue();
                        clearControls();
                        Toast.makeText(getApplicationContext(), "Date deleted successfully", Toast.LENGTH_SHORT).show();
                        openActivity8();
                    } else
                        Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        });


        //////////////
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("Nutrition/Nutrition");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    height.setText(dataSnapshot.child("height").getValue().toString());
                    Weight.setText(dataSnapshot.child("weight").getValue().toString());
                    budget.setText(dataSnapshot.child("budget").getValue().toString());

                }
                else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ////////////

        ////////////
        DatabaseReference readRefR = FirebaseDatabase.getInstance().getReference().child("register/register");
        readRefR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    name.setText(dataSnapshot.child("name").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    gender.setText(dataSnapshot.child("genderSpin").getValue().toString());
                    phno.setText(dataSnapshot.child("phNo").getValue().toString());
                    nic.setText(dataSnapshot.child("nic").getValue().toString());
                    age.setText(dataSnapshot.child("age").getValue().toString());

                    pname.setText(dataSnapshot.child("name").getValue().toString());

                }
                else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ////////////




    }

    public void openActivity8(){
        Intent intent = new Intent(this,MainActivity8.class);
        startActivity(intent);
    }
}