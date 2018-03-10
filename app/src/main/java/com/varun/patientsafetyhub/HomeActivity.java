package com.varun.patientsafetyhub;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends Activity {

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_home);
    }

    public void move1(View v)
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
    }
    public void move2(View v)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
    public void move3(View v)
    {
        Intent i=new Intent(this,DoctorSignIn.class);
        startActivity(i);
    }


    @Override
    public void onBackPressed()
    {
     moveTaskToBack(true);
    }
}
