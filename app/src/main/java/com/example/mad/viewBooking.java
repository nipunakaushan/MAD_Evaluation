package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class viewBooking extends AppCompatActivity {

    TextView nm,ag,lc,tm;
    Button btn,btn2,btn3;
    DatabaseReference ref;

    public void clear(){
        nm.setText("");
        ag.setText("");
        lc.setText("");
        tm.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);

        final String s1 =getIntent().getStringExtra("key");

        nm= findViewById(R.id.name);
        ag = findViewById(R.id.age);
        lc = findViewById(R.id.location);
        tm = findViewById(R.id.time);

        String ne = getIntent().getStringExtra("nm");
        String ae = getIntent().getStringExtra("ag");
        String lt = getIntent().getStringExtra("lc");
        String tt = getIntent().getStringExtra("tm");

        nm.setText(ne);
        ag.setText(ae);
        lc.setText(lt);
        tm.setText(tt);

        btn = findViewById(R.id.book);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),booking.class);
                Toast.makeText(getApplicationContext(),"You have successfully booked a time!",Toast.LENGTH_LONG).show();
                startActivity(i);
            }
        });

        btn2 = findViewById(R.id.up);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = nm.getText().toString().trim();
                String data2 = ag.getText().toString().trim();
                String data3 = lc.getText().toString().trim();
                String data4 = tm.getText().toString().trim();

                Intent i = new Intent(getApplicationContext(),updateBooking.class);
                i.putExtra("key",s1);
                Toast.makeText(getApplicationContext(),"key"+s1,Toast.LENGTH_SHORT).show();

                i.putExtra("nm",data1);
                i.putExtra("ag",data2);
                i.putExtra("lc",data3);
                i.putExtra("tm",data4);

                startActivity(i);
            }
        });

        btn3 = findViewById(R.id.del);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dlRef = FirebaseDatabase.getInstance().getReference().child("BookingDetails");
                dlRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(s1)){
                            ref =FirebaseDatabase.getInstance().getReference().child("BookingDetails").child(s1);
                            ref.removeValue();
                            clear();
                            Toast.makeText(getApplicationContext(),"Deleted successfully!",Toast.LENGTH_LONG).show();


                            Intent i = new Intent(getApplicationContext(),booking.class);
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
    }
}