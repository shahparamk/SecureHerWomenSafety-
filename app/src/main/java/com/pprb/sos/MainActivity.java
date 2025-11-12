package com.raghav.sos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //     Bundle extras = getIntent().getExtras();
        //     String V1 = extras.getString(Intent.EXTRA_TEXT);
        //     Log.d("NumberMainActivity", V1);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = getIntent().getExtras();
        String V1 = extras.getString(Intent.EXTRA_TEXT);
        Log.d("NumberMainActivity", V1);
    }

   //       public void myProfile(View v){
     //  Intent i = new Intent(getApplicationContext(), MyProfile.class);
       // startActivity(i);
   // }

    public void addRelative(View v) {
        Intent i = new Intent(getApplicationContext() , MainActivity2.class);
        startActivity(i);
    }

    public void helplineNumbers(View v) {
       Intent i = new Intent(getApplicationContext(), helplineCall.class);
       startActivity(i);
   }

    public void triggers(View v) {
        Intent i = new Intent(getApplicationContext(), TrigActivity.class);
        startActivity(i);
    }

    public void developedBy(View v) {
       Intent i = new Intent(getApplicationContext(), DeveloperByActivity.class);
        startActivity(i);
    }
    public void HowTo(View v) {
        Intent i = new Intent(getApplicationContext(), HowToSwipe.class);
        startActivity(i);
    }

    public void LogOut(View v) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
     }


}