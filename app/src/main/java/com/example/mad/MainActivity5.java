package com.example.mad;

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

public class MainActivity5 extends AppCompatActivity {
    TextView pname;

    register Register;
    DatabaseReference ref1;
    public void clearControls(){

        pname.setText("");

    }
    private Button button14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        pname=findViewById(R.id.EPPName);



        Register = new register();

        button14 = (Button) findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity2();
        }
        });

        ////////////
        DatabaseReference readRefR = FirebaseDatabase.getInstance().getReference().child("register/register");
        readRefR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){


                    pname.setText(dataSnapshot.child("name").getValue().toString());

                }
                else
                    Toast.makeText(getApplicationContext(), "No source to display", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ////////////
    }

    public void openActivity2(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}