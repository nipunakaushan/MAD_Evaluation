package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updateSchedule extends AppCompatActivity {

    EditText em,ag,we,he;
    Button btn;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_schedule);


        final String s1 =getIntent().getStringExtra("key");


        em = findViewById(R.id.email);
        ag = findViewById(R.id.age);
        we = findViewById(R.id.weight);
        he = findViewById(R.id.height);

        String email = getIntent().getStringExtra("em");
        String ae = getIntent().getStringExtra("ag");
        String weight = getIntent().getStringExtra("we");
        String height = getIntent().getStringExtra("he");

        em.setText(email);
        ag.setText(ae);
        we.setText(weight);
        he.setText(height);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = em.getText().toString();
                String age = ag.getText().toString();
                String weight = we.getText().toString();
                String height = he.getText().toString();

                ref = FirebaseDatabase.getInstance().getReference().child("Customer");

                HashMap hashMap = new HashMap();

                hashMap.put("age", age);
                hashMap.put("email", email);
                hashMap.put("height", height);
                hashMap.put("weight", weight);

                ref.child(s1).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),schedule.class);
                        startActivity(i);
                    }
                });

            }
        });
    }
}