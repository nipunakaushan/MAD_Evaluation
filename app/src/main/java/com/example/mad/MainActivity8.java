package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity8 extends AppCompatActivity {
    private Button button11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity8();
        }
        });
    }
    public void openActivity8(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}