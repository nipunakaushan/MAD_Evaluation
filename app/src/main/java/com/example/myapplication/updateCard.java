package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updateCard extends AppCompatActivity {

    EditText name,number,cvc;
    Spinner mon,year;
    Button btn;
    DatabaseReference ref;

    public void clear(){
        name.setText("");
        number.setText("");
        cvc.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_card);

        final String s1 =getIntent().getStringExtra("key");

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
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
        //mon.setText(mo);
        //year.setText(yr);

        btn = findViewById(R.id.done);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nm = name.getText().toString();
                String nu = number.getText().toString();
                String cc = cvc.getText().toString();
                String mo = mon.getSelectedItem().toString();
                String yr = year.getSelectedItem().toString();

                ref = FirebaseDatabase.getInstance().getReference().child("Card");

                HashMap hashMap = new HashMap();

                hashMap.put("cName", nm);
                hashMap.put("cNum", nu);
                hashMap.put("cvc", cc);
                hashMap.put("month", mo);
                hashMap.put("year", yr);

                ref.child(s1).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                });
            }
        });
    }
}