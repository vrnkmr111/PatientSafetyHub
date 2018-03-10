package com.varun.patientsafetyhub;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserHome extends AppCompatActivity {
TextView txtWelcome;
  public String m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        txtWelcome=(TextView) findViewById(R.id.txtWelcome);
        Bundle b=getIntent().getExtras();
         m=b.getString("message");

        txtWelcome.setText("Welocme To "+m+"'s Home");
    }
    public void add(View v)
    {
        Intent in=new Intent(this,AddDetails.class);
        in.putExtra("var",m);
        startActivity(in);
    }
    public void mydetails(View v)
    {
        Intent in=new Intent(this,ListDetails_Single.class);
        in.putExtra("useremail",m);
        startActivity(in);
    }
    public void delete(View v)
    {
        Intent in=new Intent(this,DeleteDetails.class);
        startActivity(in);
    }
    public void update(View v)
    {
        Intent in=new Intent(this,UpdateDetails.class);
        startActivity(in);
    }

    public void logout(View v)
    {
        Intent in=new Intent(this,HomeActivity.class);
        startActivity(in);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("yes Or No");
        builder.setMessage("Do you want to logout ? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent in=new Intent(UserHome.this,HomeActivity.class);
                startActivity(in);
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    }

