package com.raghav.sos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginactivity extends AppCompatActivity {

    EditText txtemail, txtpassword;
    Button btn_login, forgotpass;;
    FirebaseAuth firebaseAuth;
    TextView textsignup;
    private FirebaseAuth.AuthStateListener mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);


        txtemail = (EditText) findViewById(R.id.email);
        txtpassword = (EditText) findViewById(R.id.pass);
        btn_login = (Button) findViewById(R.id.button);
        textsignup = (TextView) findViewById(R.id.textView);
        forgotpass=(Button)findViewById(R.id.btn_forgotpass);
        firebaseAuth = FirebaseAuth.getInstance();

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(loginactivity.this,Forgotpassword.class);
                startActivity(i);
            }
        });

        mAuth = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(loginactivity.this, "You are logged in ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(loginactivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(loginactivity.this, "Please login ", Toast.LENGTH_LONG).show();
                }
            }
        };

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(loginactivity.this, "ENTER AN EMAIL ADDRESS", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(loginactivity.this, "ENTER A PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(loginactivity.this, "PASSWORD IS TOO SHORT MUST BE 6 CHARACTERS LENGTH", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(loginactivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    if (firebaseAuth.getCurrentUser().isEmailVerified()) {

                                        Toast.makeText(loginactivity.this, "LOGGED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(loginactivity.this, MainActivity.class);
                                        startActivity(i);

                                    }


                                } else {

                                    Toast.makeText(loginactivity.this, "LOG IN FAILED PLEASE VERIFY YOUR EMAIL ID", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });

            }
        });

        textsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(loginactivity.this, Register.class);
                startActivity(i);
            }
        });
    }
    protected void onStart () {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuth);
    }
}



