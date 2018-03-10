package com.varun.patientsafetyhub;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class DoctorLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
    }
    public void retrive(View v)
    {
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor courses = db.query(
                    STDatabase.PATIENT_TABLE_NAME,null,null,null,null,null,null);

            String from [] = {"_id","name","emailid","doc", "symptoms","docinfo"};
            int to [] = {R.id.textid ,R.id.textNa,R.id.textEm,R.id.textDc, R.id.textSymp, R.id.textDoc};

            SimpleCursorAdapter ca=new SimpleCursorAdapter(this,R.layout.course_row, courses,from,to);

            ListView listresult = (ListView) this.findViewById( R.id.listresult);
            listresult.setAdapter(ca);

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
