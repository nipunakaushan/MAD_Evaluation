package com.example.myapplication;

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

public class card1 extends AppCompatActivity {

    TextView name,number,mon,year,cvc;
    Button btn,btn2,btn3;
    DatabaseReference ref;

    private void clear(){
        name.setText("");
        number.setText("");
        mon.setText("");
        year.setText("");
        cvc.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1);

        final String s1 =getIntent().getStringExtra("key");

        name = findViewById(R.id.cName);
        number = findViewById(R.id.cNum);
        cvc = findViewById(R.id.cvc);
        mon = findViewById(R.id.month);
        year = findViewById(R.id.year);

        String cn = getIntent().getStringExtra("cn");
        String cN = getIntent().getStringExtra("cN");
        String mo = getIntent().getStringExtra("mo");
        String yr = getIntent().getStringExtra("yr");
        String cc = getIntent().getStringExtra("cc");

        name.setText(cn);
        number.setText(cN);
        cvc.setText(cc);
        mon.setText(mo);
        year.setText(yr);

        btn = findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        btn2 = findViewById(R.id.up);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data1 = name.getText().toString().trim();
                String data2 = number.getText().toString().trim();
                String data3 = mon.getText().toString().trim();
                String data4 = year.getText().toString().trim();
                String data5 = cvc.getText().toString().trim();

                Intent i = new Intent(getApplicationContext(),updateCard.class);
                i.putExtra("key",s1);
                Toast.makeText(getApplicationContext(),"key"+s1,Toast.LENGTH_SHORT).show();

                i.putExtra("cn",data1);
                i.putExtra("cN",data2);
                i.putExtra("mo",data3);
                i.putExtra("yr",data4);
                i.putExtra("cc",data5);
                startActivity(i);
            }
        });

        btn3 = findViewById(R.id.de);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference dlRef = FirebaseDatabase.getInstance().getReference().child("Card");
                dlRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(s1)){
                            ref =FirebaseDatabase.getInstance().getReference().child("Card").child(s1);
                            ref.removeValue();
                            clear();
                            Toast.makeText(getApplicationContext(),"Deleted successfully!",Toast.LENGTH_LONG).show();


                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
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