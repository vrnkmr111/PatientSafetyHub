package com.varun.patientsafetyhub;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddDetails extends AppCompatActivity {
    private EditText editName,editEmailid, editSymp,editDoc, editDocinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=getIntent().getExtras();
        String m=b.getString("var");
        setContentView(R.layout.activity_add_details);
        editName = (EditText) this.findViewById(R.id.editName);
        editEmailid = (EditText) this.findViewById(R.id.editEmail);
        editEmailid.setText(m,null);
        editEmailid.setFocusableInTouchMode(false);
        editEmailid.setClickable(false);
        editDoc = (EditText)this.findViewById(R.id.editDate);
        editSymp = (EditText) this.findViewById(R.id.editSymp);
        editDocinfo = (EditText) this.findViewById(R.id.editDocinfo);
    }
    public void addDetails(View v) {

        // get access to writeable database
        try {
            String s1=editName.getText().toString();
            String s2=editDoc.getText().toString();
            String s3=editSymp.getText().toString();
            String s4=editDocinfo.getText().toString();

            if(!s1.isEmpty()&&!s2.isEmpty()&&!s3.isEmpty()&&!s4.isEmpty()) {
                STDatabase dbhelper = new STDatabase(this);
                SQLiteDatabase db = dbhelper.getWritableDatabase();

                // execute insert command

                ContentValues values = new ContentValues();
                values.put(STDatabase.PATIENT_NAME, editName.getText().toString());
                values.put(STDatabase.PATIENT_EMAIL, editEmailid.getText().toString());
                values.put(STDatabase.PATIENT_DATE, editDoc.getText().toString());
                values.put(STDatabase.PATIENT_SYMPTOMS, editSymp.getText().toString());
                values.put(STDatabase.PATIENT_DOCINFO, editDocinfo.getText().toString());

                db.insert(STDatabase.PATIENT_TABLE_NAME, null, values);
                Toast.makeText(this, "Added Details Successfully!",
                        Toast.LENGTH_SHORT).show();
                editName.setText("");
                editEmailid.setText("");
                editDoc.setText("");
                editDocinfo.setText("");
                editSymp.setText("");
            }
            else {
                AlertDialog alertDialog = new AlertDialog.Builder(AddDetails.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("All fields are Mandatory");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
