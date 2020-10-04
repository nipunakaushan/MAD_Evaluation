package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class viewCard extends AppCompatActivity {

    EditText card,number,cvc;
    Spinner month,year;
    Button btn;
    DatabaseReference ref;
    Card cd;
    //Ruwan Sirimanna

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        card = findViewById(R.id.name);
        number = findViewById(R.id.number);
        cvc = findViewById(R.id.cvc);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        cd = new Card();

        btn = findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference().child("Card");

                String ItemId = ref.push().getKey();

                cd.setcName(card.getText().toString().trim());
                cd.setcNum(number.getText().toString().trim());
                cd.setMonth(month.getSelectedItem().toString().trim());
                cd.setYear(year.getSelectedItem().toString().trim());
                cd.setCvc(cvc.getText().toString().trim());

                ref.child(ItemId).setValue(cd);

                String data1 = card.getText().toString().trim();
                String data2 = number.getText().toString().trim();
                String data3 = month.getSelectedItem().toString().trim();
                String data4 = year.getSelectedItem().toString().trim();
                String data5 = cvc.getText().toString().trim();

                Intent i = new Intent(getApplicationContext(),card1.class);
                i.putExtra("key",ItemId);
                Toast.makeText(getApplicationContext(),"key"+ItemId,Toast.LENGTH_SHORT).show();

                i.putExtra("cn",data1);
                i.putExtra("cN",data2);
                i.putExtra("mo",data3);
                i.putExtra("yr",data4);
                i.putExtra("cc",data5);

                startActivity(i);
            }
        });
    }
}