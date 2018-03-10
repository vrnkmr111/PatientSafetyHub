package com.varun.patientsafetyhub;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editEmail,editPass,editCpass;


    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_main);
        editEmail=(EditText)findViewById(R.id.editEmail);
        editPass=(EditText) findViewById(R.id.editPass);
        editCpass=(EditText) findViewById(R.id.editCpass);

    }


    public void register(View v) {
        try
        {
            DatabaseSample helper = new DatabaseSample(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            String edit_Email = editEmail.getText().toString();
            String edit_Pass = editPass.getText().toString();
            String edit_Cpass = editCpass.getText().toString();

            Cursor cursor=db.query(DatabaseSample.REG_TABLE_NAME,null,DatabaseSample.COLUMN_EMAIL+"=?",new String[]{edit_Email},null,null,null);
            if(edit_Email.isEmpty()||edit_Pass.isEmpty()||edit_Cpass.isEmpty())
            {
                Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_LONG).show();
            }

            else if (!edit_Pass.equals(edit_Cpass)) {
                Toast.makeText(this, "password not match", Toast.LENGTH_LONG).show();
            }

            else if(cursor.moveToFirst())
            {
                Toast.makeText(this,"Email already exists",Toast.LENGTH_LONG).show();
            }
            else {
                ContentValues cv=new ContentValues();
                cv.put("email",edit_Email);
                cv.put("pass",edit_Pass);
                db.insert(DatabaseSample.REG_TABLE_NAME,null,cv);
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("SuccessFully Registerd! Press ok to login");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(i);
                            }
                        });
                alertDialog.show();

                editEmail.setText("");
                editPass.setText("");
                editCpass.setText("");


            }

        }
        catch(Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    public void login(View v)
    {
        Intent in=new Intent(this,LoginActivity.class);
        startActivity(in);
    }
}

