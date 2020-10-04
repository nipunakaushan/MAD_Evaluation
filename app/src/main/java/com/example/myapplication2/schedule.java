package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class schedule extends AppCompatActivity {

    EditText em,ag,we,he;
    Button btn;
    DatabaseReference ref;
    details detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        em = findViewById(R.id.email);
        ag = findViewById(R.id.age);
        we = findViewById(R.id.weight);
        he = findViewById(R.id.height);

        detail = new details();

        btn = findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref = FirebaseDatabase.getInstance().getReference().child("Customer");

                String ItemId = ref.push().getKey();

                detail.setEmail(em.getText().toString().trim());
                detail.setAge(ag.getText().toString().trim());
                detail.setHeight(he.getText().toString().trim());
                detail.setWeight(we.getText().toString().trim());

                ref.child(ItemId).setValue(detail);

                String data1 = em.getText().toString().trim();
                String data2 = ag.getText().toString().trim();
                String data3 = we.getText().toString().trim();
                String data4 = he.getText().toString().trim();

                Intent i = new Intent(getApplicationContext(),viewDetails.class);
                i.putExtra("key",ItemId);
                Toast.makeText(getApplicationContext(),"key"+ItemId,Toast.LENGTH_SHORT).show();

                i.putExtra("em",data1);
                i.putExtra("ag",data2);
                i.putExtra("we",data3);
                i.putExtra("he",data4);

                startActivity(i);
            }
        });
    }
}