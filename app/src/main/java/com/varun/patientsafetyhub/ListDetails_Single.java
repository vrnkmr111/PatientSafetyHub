package com.varun.patientsafetyhub;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListDetails_Single extends AppCompatActivity {
EditText ed2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details__single);
        Bundle b=getIntent().getExtras();
        String s1=b.getString("useremail");
        ed2 = (EditText) this.findViewById(R.id.ed2);
        ed2.setText(s1,null);
        ed2.setFocusableInTouchMode(false);

    }
    public void mydetails(View v) {
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            String cond = STDatabase.PATIENT_EMAIL + "=?";

            Cursor cursor = db.query(
                    STDatabase.PATIENT_TABLE_NAME, null, cond,
                    new String[]{ed2.getText().toString()}, null, null, null);
            String from[] = {"_id", "name", "emailid", "doc", "symptoms", "docinfo"};
            int to[] = {R.id.textid, R.id.textNa, R.id.textEm, R.id.textDc, R.id.textSymp, R.id.textDoc};
            SimpleCursorAdapter ca = new SimpleCursorAdapter(this, R.layout.course_row, cursor, from, to);
            ListView listCourses = (ListView) this.findViewById(R.id.listCourses);
            listCourses.setAdapter(ca);

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    }

