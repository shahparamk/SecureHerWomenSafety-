package com.raghav.sos;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText txtemail,txtpassword,txtconfirmpassword,name;
    Button btn_signup;
    ProgressBar progess;
    private FirebaseAuth firebaseAuth;
    DatabaseReference reff;
    Sign sign;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        name=(EditText)findViewById(R.id.PersonName);
        txtemail=(EditText)findViewById(R.id.emailAddress);
        txtpassword=(EditText)findViewById(R.id.Password);
        txtconfirmpassword=(EditText)findViewById(R.id.Password2);
        progess=(ProgressBar)findViewById(R.id.progressBar2);
        btn_signup=(Button)findViewById(R.id.signup1);
        sign=new Sign();
        textView=(TextView)findViewById(R.id.textView);
        reff= FirebaseDatabase.getInstance().getReference().child("Sign");
        firebaseAuth = FirebaseAuth.getInstance();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Register.this,Login.class);
                startActivity(intent);
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = name.getText().toString().trim();
                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();
                String confirm = txtconfirmpassword.getText().toString().trim();

                sign.setName(name.getText().toString().trim());
                sign.setEmail(txtemail.getText().toString().trim());
                reff.push().setValue(sign);
                Toast.makeText(Register.this,"DATA HAS BEEN SEND SUCCESSFULLY",Toast.LENGTH_LONG).show();




                if(TextUtils.isEmpty(mail)){
                    Toast.makeText(Register.this,"ENTER YOUR FULL NAME",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"ENTER AN EMAIL ADDRESS",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"ENTER A PASSWORD",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(confirm)){
                    Toast.makeText(Register.this,"PASSSWORD DOES NOT MATCH",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6) {
                    Toast.makeText(Register.this, "PASSWORD IS TOO SHORT MUST BE 6 CHARACTERS LENGTH", Toast.LENGTH_SHORT).show();
                }

                progess.setVisibility(View.VISIBLE);

                if(password.equals(confirm)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progess.setVisibility(View.GONE);


                                    if (task.isSuccessful()) {

                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) ;
                                                startActivity(new Intent(getApplicationContext(), Login.class));
                                                Toast.makeText(Register.this, "REGISTRATION SUCCESSFUL PLEASE VERIFY YOUR EMAIL ID", Toast.LENGTH_SHORT).show();
                                            }

                                        });
                                    }

                                    else {
                                        Toast.makeText(Register.this,"EMAIL ID IS ALREADY USED",Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });

                }
                else{

                    Toast.makeText(Register.this,"PASSWORD DOES NOT MATCH",Toast.LENGTH_SHORT).show();
                }



            }
        });







    }
}

