package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updateBooking extends AppCompatActivity {

    EditText nm,ag,lc;
    Spinner tm;
    Button btn;
    DatabaseReference ref;

    private void clear(){
        nm.setText("");
        ag.setText("");
        lc.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_booking);

        final String s1 =getIntent().getStringExtra("key");

        nm= findViewById(R.id.name);
        ag = findViewById(R.id.age);
        lc = findViewById(R.id.location);
        tm = findViewById(R.id.time);

        String ne = getIntent().getStringExtra("nm");
        String ae = getIntent().getStringExtra("ag");
        String lt = getIntent().getStringExtra("lc");
        //String tt = getIntent().getStringExtra("tm");

        nm.setText(ne);
        ag.setText(ae);
        lc.setText(lt);
       // tm.setText(tt);

        btn = findViewById(R.id.book);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nm.getText().toString();
                String age = ag.getText().toString();
                String location = lc.getText().toString();
                String time = tm.getSelectedItem().toString();

                ref = FirebaseDatabase.getInstance().getReference().child("BookingDetails");

                HashMap hashMap = new HashMap();

                hashMap.put("age", age);
                hashMap.put("location", location);
                hashMap.put("name", name);
                hashMap.put("time", time);

                ref.child(s1).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),booking.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}