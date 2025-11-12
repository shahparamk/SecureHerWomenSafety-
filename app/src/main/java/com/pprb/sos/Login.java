package com.raghav.sos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Login extends AppCompatActivity {
    public Button button;
    Button phoneNumber;
    FloatingActionButton btn_google,btn_twitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button) findViewById(R.id.loginemail);
        btn_google=(FloatingActionButton)findViewById(R.id.google_but);
        phoneNumber=(Button)findViewById(R.id.phone);
        btn_twitter=(FloatingActionButton)findViewById(R.id.btn_twitter);



        btn_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,TwitterActivity.class);
                startActivity(i);
            }
        });


        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SendOTPActivity.class);
                startActivity(intent);
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,GoogleSignIn.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,loginactivity.class);
                startActivity(intent);
            }
        });

        button=(Button)findViewById(R.id.signupemail);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        }));

    }
}