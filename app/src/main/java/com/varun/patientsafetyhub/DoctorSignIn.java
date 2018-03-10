package com.varun.patientsafetyhub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DoctorSignIn extends AppCompatActivity {
    EditText DocId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_in);
        DocId=(EditText)this.findViewById(R.id.DocId);

    }
    public void dlogin(View v)
    {
        String s="admin";
        try {
            if (s.equals( DocId.getText().toString()))
            {
                 Intent in=new Intent(this,ListDetailsUser.class);
                 startActivity(in);
            }
            else {
                Toast.makeText(DoctorSignIn.this, "Please Enter valid ID", Toast.LENGTH_SHORT).show();
            }
            DocId.setText("");
        }
        catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }
}
