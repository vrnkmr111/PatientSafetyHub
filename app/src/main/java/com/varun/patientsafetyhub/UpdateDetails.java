package com.varun.patientsafetyhub;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UpdateDetails extends AppCompatActivity {
    private EditText editupname, editupid,editup1,editup2,editup3;
    private LinearLayout layoutDetails ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        editupname = (EditText) this.findViewById(R.id.editupname);
        editupid = (EditText) this.findViewById(R.id.editupid);
        editup1 = (EditText) this.findViewById(R.id.editup1);
        editup2 = (EditText) this.findViewById(R.id.editup2);
        editup3 = (EditText) this.findViewById(R.id.editup3);
        layoutDetails = (LinearLayout) this.findViewById(R.id.layoutDetails);
    }

    public void getDetails(View v) {


        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getReadableDatabase();

            Cursor details = db.query(STDatabase.PATIENT_TABLE_NAME, null,
                    STDatabase.PATIENT_NAME + " = ? AND "+STDatabase.PATIENT_ID+"=?",  // select
                    new String[]{editupname.getText().toString(),editupid.getText().toString()}, //  selectargs,
                    null, null, null);

            if ( details.moveToFirst()) { // found row
                editup1.setText(  details.getString( details.getColumnIndex( STDatabase.PATIENT_DATE)));
                editup2.setText(  details.getString( details.getColumnIndex( STDatabase.PATIENT_SYMPTOMS)));
                editup3.setText(details.getString( details.getColumnIndex( STDatabase.PATIENT_DOCINFO)));
                layoutDetails.setVisibility( View.VISIBLE);
            }
            else{
                layoutDetails.setVisibility( View.INVISIBLE);
                Toast.makeText(this, "Sorry! Course not found!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }



    public void updateCourse(View v) {

        // get access to writeable database
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            // execute update command

            ContentValues values = new ContentValues();
            values.put( STDatabase.PATIENT_DATE , editup1.getText().toString());
            values.put(STDatabase.PATIENT_SYMPTOMS, editup2.getText().toString());
            values.put( STDatabase.PATIENT_DOCINFO , editup3.getText().toString());

            int count  = db.update(STDatabase.PATIENT_TABLE_NAME,values,
                    STDatabase.PATIENT_NAME + " = ? AND "+STDatabase.PATIENT_ID+"=?" ,
                    new String[] { editupname.getText().toString(),editupid.getText().toString() });

            if ( count == 1)
                Toast.makeText(this, "Updated Data Successfully!",Toast.LENGTH_LONG).show();

            else
                Toast.makeText(this, "Sorry! Could not update details!", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
