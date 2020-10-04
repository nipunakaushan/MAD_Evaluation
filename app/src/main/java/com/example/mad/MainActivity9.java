package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity9 extends AppCompatActivity {
    EditText nplan,ndu,nwater,nemail;
    Button btn;
    Nutritionmeal nutritionmeal;
    DatabaseReference ref;
    public void clearControls(){
        nplan.setText("");
        ndu.setText("");
        nwater.setText("");
        nemail.setText("");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);


        nplan = findViewById(R.id.NPlan);
        ndu = findViewById(R.id.NDu);

        nwater = findViewById(R.id.NWater);
        nemail=findViewById(R.id.Nemail);
        btn = findViewById(R.id.button10);

        nutritionmeal = new Nutritionmeal();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref = FirebaseDatabase.getInstance().getReference().child("NutritionMeal");
                try {


                    if (TextUtils.isEmpty(nplan.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a health issues", Toast.LENGTH_SHORT).show();


                    else if (TextUtils.isEmpty(ndu.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a Duration", Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(nwater.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a your daily water level", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(nwater.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Enter a your Email", Toast.LENGTH_SHORT).show();

                    else {

                        nutritionmeal.setNplan(nplan.getText().toString().trim());
                        nutritionmeal.setNdu(ndu.getText().toString().trim());
                        nutritionmeal.setNwater(nwater.getText().toString().trim());
                        nutritionmeal.setNemail(nemail.getText().toString().trim());




                        //ref.push().setValue(donor);
                        ref.child("NutritionMeal").setValue(nutritionmeal);
                        Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity7 .class);
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