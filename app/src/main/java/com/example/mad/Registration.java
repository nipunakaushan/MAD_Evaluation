package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    EditText name,email,phno,nic,age,health;
    Spinner genderSpin;
     Button singup;

    register Register;


    DatabaseReference ref;
    public void clearControls(){
        name.setText("");
        email.setText("");

        phno.setText("");
        nic.setText("");
        age.setText("");
        health.setText("");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        name = findViewById(R.id.Ename);
        email = findViewById(R.id.Eemail);
        phno = findViewById(R.id.EphNo);
        nic = findViewById(R.id.Enic);
        age = findViewById(R.id.Eage);
        health = findViewById(R.id.Ehelth);

        genderSpin = findViewById(R.id.genderSpin);

        singup = findViewById(R.id.singup);

        Register = new register();

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("register");
                try {


                    if (TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a name", Toast.LENGTH_SHORT).show();


                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a Email", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(phno.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a Phone number", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(nic.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a NIC", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(age.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a age", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(health.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a health issues", Toast.LENGTH_SHORT).show();

                    else {

                        Register.setName(name.getText().toString().trim());
                        Register.setEmail(email.getText().toString().trim());
                        Register.setNic(nic.getText().toString().trim());
                        Register.setHealth(health.getText().toString().trim());
                        Register.setPhNo(Integer.parseInt(phno.getText().toString().trim()));
                        Register.setAge(Integer.parseInt(age.getText().toString().trim()));
                        Register.setGenderSpin(genderSpin.getSelectedItem().toString());



                        //ref.push().setValue(donor);
                        ref.child("register").setValue(Register);
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
    }
}