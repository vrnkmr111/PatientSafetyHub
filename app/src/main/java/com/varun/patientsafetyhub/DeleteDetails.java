package com.varun.patientsafetyhub;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteDetails extends AppCompatActivity {

    private EditText editName,editId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_details);
        editName = (EditText)this.findViewById(R.id.editName);
        editId=(EditText)this.findViewById(R.id.editIde);
    }


    public void deleteCourse(View v) {
        try {
            STDatabase dbhelper = new STDatabase(this);
            SQLiteDatabase db = dbhelper.getWritableDatabase();

            String cond = STDatabase.PATIENT_EMAIL+ " = ? AND "+STDatabase.PATIENT_ID+"=?";

            Log.d("Storage", cond);
            int count = db.delete(STDatabase.PATIENT_TABLE_NAME,cond,
                    new String[] {editName.getText().toString(),editId.getText().toString()});
            if (count == 1) {

                AlertDialog alertDialog = new AlertDialog.Builder(DeleteDetails.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Deleted SuccessFully");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

            else
            {
                Toast.makeText(this, "Details Not Found!",
                        Toast.LENGTH_SHORT).show();
            }
            editId.setText("");
            editName.setText("");
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
