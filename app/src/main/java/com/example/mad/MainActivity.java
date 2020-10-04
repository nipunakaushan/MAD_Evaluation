package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText Weight12,height12,budget12;
    TextView name,email,phno,nic,age,gender,pname;

    Spinner spin;
    Button btn;

    Nutrition nutrition;
    register Register;
    DatabaseReference ref;
    DatabaseReference ref1;

    public void clearControls(){
        height12.setText("");
        Weight12.setText("");
        budget12.setText("");


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
        setContentView(R.layout.activity_main);

        Spinner aSpinner = findViewById(R.id.spin);
        aSpinner.setOnItemSelectedListener(this);

        height12 = findViewById(R.id.txtheight);
        Weight12 = findViewById(R.id.txtweight);

        budget12 = findViewById(R.id.txtbudget);
        spin = findViewById(R.id.spin);

        btn = findViewById(R.id.button);

        name = findViewById(R.id.Esname);
        email = findViewById(R.id.Esemail);
        phno = findViewById(R.id.EsphNo);
        nic = findViewById(R.id.Esnic);
        age = findViewById(R.id.Esage);

        gender = findViewById(R.id.gender);
        pname=findViewById(R.id.EPPName);


        nutrition = new Nutrition();
        Register = new register();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("Nutrition");
                try {


                    if (TextUtils.isEmpty(height12.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a height", Toast.LENGTH_SHORT).show();


                    else if (TextUtils.isEmpty(Weight12.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a weight", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(budget12.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a budget", Toast.LENGTH_SHORT).show();

                    else {

                        nutrition.setHeight(height12.getText().toString().trim());
                        nutrition.setWeight(Weight12.getText().toString().trim());
                        nutrition.setBudget(budget12.getText().toString().trim());
                        nutrition.setSpinn(spin.getSelectedItem().toString());



                        //ref.push().setValue(donor);
                        ref.child("Nutrition").setValue(nutrition);
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity2 .class);
                        clearControls();

                        startActivity(i);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}