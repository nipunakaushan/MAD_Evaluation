package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewDetails extends AppCompatActivity {

    TextView ema,age,wei,hei;
    Button btn,btn1,btn2;
    DatabaseReference ref;

    public void clear(){
        ema.setText("");
        age.setText("");
        wei.setText("");
        hei.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        final String s1 =getIntent().getStringExtra("key");


        ema = findViewById(R.id.email);
        age = findViewById(R.id.age);
        wei = findViewById(R.id.weight);
        hei = findViewById(R.id.height);

        String em = getIntent().getStringExtra("em");
        String ag = getIntent().getStringExtra("ag");
        String we = getIntent().getStringExtra("we");
        String he = getIntent().getStringExtra("he");

        ema.setText(em);
        age.setText(ag);
        wei.setText(we);
        hei.setText(he);

        btn = findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data1 = ema.getText().toString().trim();
                String data2 = age.getText().toString().trim();
                String data3 = wei.getText().toString().trim();
                String data4 = hei.getText().toString().trim();

                Intent i = new Intent(getApplicationContext(),updateSchedule.class);
                i.putExtra("key",s1);
                Toast.makeText(getApplicationContext(),"key"+s1,Toast.LENGTH_SHORT).show();

                i.putExtra("em",data1);
                i.putExtra("ag",data2);
                i.putExtra("we",data3);
                i.putExtra("he",data4);

                startActivity(i);
            }
        });

        btn1 = findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dlRef = FirebaseDatabase.getInstance().getReference().child("Customer");
                dlRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(s1)){
                            ref =FirebaseDatabase.getInstance().getReference().child("Customer").child(s1);
                            ref.removeValue();
                            clear();
                            Toast.makeText(getApplicationContext(),"Deleted successfully!",Toast.LENGTH_LONG).show();


                            Intent i = new Intent(getApplicationContext(),schedule.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"No source to delete!",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        btn2 = findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Schedule will sent to your email short leave. Stay tuned!",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),schedule.class);
                startActivity(i);
            }
        });
    }
}