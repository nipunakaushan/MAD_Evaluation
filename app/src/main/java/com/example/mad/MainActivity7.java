package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainActivity7 extends AppCompatActivity {
    private StorageReference mStorageReference;

    TextView nplan,ndu,nwater,nemail;
    Button btn,btn2;

    Nutritionmeal nutritionmeal;
    DatabaseReference ref;
    public void clearControls(){
        nplan.setText("");
        ndu.setText("");
        nwater.setText("");
        nemail.setText("");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        nplan = findViewById(R.id.Tplan);
        ndu = findViewById(R.id.Tdu);

        nwater = findViewById(R.id.Twater);
        nemail=findViewById(R.id.Temail);
        btn = findViewById(R.id.button12);

        nutritionmeal = new Nutritionmeal();
        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity();
        }
        });


        ////////////
        DatabaseReference readRefR = FirebaseDatabase.getInstance().getReference().child("NutritionMeal/NutritionMeal");
        readRefR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChildren()){
                    nplan.setText(dataSnapshot.child("nplan").getValue().toString());
                    ndu.setText(dataSnapshot.child("ndu").getValue().toString());
                    nwater.setText(dataSnapshot.child("nwater").getValue().toString());
                    nemail.setText(dataSnapshot.child("nemail").getValue().toString());


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
    public void openActivity(){
        Intent intent = new Intent(this,MainActivity5.class);
        startActivity(intent);



    }
}