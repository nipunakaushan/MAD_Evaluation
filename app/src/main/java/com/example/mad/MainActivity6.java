package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity6 extends AppCompatActivity {

    TextView name,email,phno,nic,age,gender,pname;


    Nutrition nutrition;
    register Register;
    DatabaseReference ref;
    DatabaseReference ref1;
    private Button button5;
    private Button button4;
    private Button button3;
    private Button button2;

    public void clearControls(){

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
        setContentView(R.layout.activity_main6);



        name = findViewById(R.id.Esname);
        email = findViewById(R.id.Esemail);
        phno = findViewById(R.id.EsphNo);
        nic = findViewById(R.id.Esnic);
        age = findViewById(R.id.Esage);

        gender = findViewById(R.id.gender);
        pname=findViewById(R.id.EPPName);


        nutrition = new Nutrition();
        Register = new register();




        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity4();
        }
        });



        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity3();
        }
        });


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




    public void openActivity3(){
        Intent intent = new Intent(this,DeleteProfile.class);
        startActivity(intent);



    }

    public void openActivity4(){
        Intent intent = new Intent(this,MainActivity4.class);
        startActivity(intent);



    }




    }
