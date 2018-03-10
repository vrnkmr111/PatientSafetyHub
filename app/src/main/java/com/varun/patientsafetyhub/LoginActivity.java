package com.varun.patientsafetyhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
     EditText editRemail,editRpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editRemail=(EditText) findViewById(R.id.editRemail);
        editRpass=(EditText)findViewById(R.id.editRpass);
    }


    public void userlogin(View v)
    {
        try
        {
            DatabaseSample helper=new DatabaseSample(this);
            SQLiteDatabase db=helper.getReadableDatabase();
           String Remail=editRemail.getText().toString();
            //String Rpass=editRpass.getText().toString();
         Cursor cursor1=db.query(DatabaseSample.REG_TABLE_NAME,null,DatabaseSample.COLUMN_EMAIL+"=?",new String[]{editRemail.getText().toString()},null,null,null,null);
            Cursor cursor2=db.query(DatabaseSample.REG_TABLE_NAME,null,DatabaseSample.COLUMN_PASS+"=?",new String[]{editRpass.getText().toString()},null,null,null,null);

             if(cursor1.moveToFirst() && cursor2.moveToFirst())
            {
                Toast.makeText(this,"Login SuccessFull",Toast.LENGTH_LONG).show();
                editRemail.setText("");
                editRpass.setText("");
                Intent intent=new Intent(this,UserHome.class);
                 intent.putExtra("message",Remail);
                startActivity(intent);

                }
                else
                {
                    Toast.makeText(this,"Login Failure",Toast.LENGTH_LONG).show();
                }
            }

        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }
}
