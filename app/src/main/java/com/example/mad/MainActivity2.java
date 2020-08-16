package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button button5;
    private Button button4;
    private Button button3;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity5();
        }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity4();
        }
        });

        button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity7();
        }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            openActivity3();
        }
        });
    }
    public void openActivity7(){
        Intent intent = new Intent(this,MainActivity7.class);
        startActivity(intent);



    }
    public void openActivity3(){
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);



    }

    public void openActivity4(){
        Intent intent = new Intent(this,MainActivity4.class);
        startActivity(intent);



    }

    public void openActivity5(){
        Intent intent = new Intent(this,MainActivity5.class);
        startActivity(intent);



    }
}