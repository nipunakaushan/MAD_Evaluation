package com.example.mad;

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

public class booking extends AppCompatActivity {

    EditText nm,ag,lc;
    Spinner tm;
    Button btn;
    DatabaseReference ref;
    bDetails booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        nm= findViewById(R.id.name);
        ag = findViewById(R.id.age);
        lc = findViewById(R.id.location);
        tm = findViewById(R.id.time);

        btn = findViewById(R.id.book);

        booking = new bDetails();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference().child("BookingDetails");

                String ItemId = ref.push().getKey();

                booking.setName(nm.getText().toString().trim());
                booking.setAge(ag.getText().toString().trim());
                booking.setLocation(lc.getText().toString().trim());
                booking.setTime(tm.getSelectedItem().toString().trim());

                ref.child(ItemId).setValue(booking);

                String data1 = nm.getText().toString().trim();
                String data2 = ag.getText().toString().trim();
                String data3 = lc.getText().toString().trim();
                String data4 = tm.getSelectedItem().toString().trim();

                Intent i = new Intent(getApplicationContext(),viewBooking.class);
                i.putExtra("key",ItemId);
                Toast.makeText(getApplicationContext(),"key"+ItemId,Toast.LENGTH_SHORT).show();

                i.putExtra("nm",data1);
                i.putExtra("ag",data2);
                i.putExtra("lc",data3);
                i.putExtra("tm",data4);

                startActivity(i);
            }
        });
    }
}