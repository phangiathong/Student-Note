package com.academy.studentnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.academy.studentnote.database.Database;
import com.academy.studentnote.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    EditText edtTitle, edtCredit, edtTime, edtPlace;
    Button btnAdd;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        edtTitle=findViewById(R.id.editTextSubjectTitle);
        edtCredit=findViewById(R.id.editTextSubjectCredit);
        edtTime=findViewById(R.id.editTextSubjectTime);
        edtPlace=findViewById(R.id.editTextSubjectPlace);
        btnAdd=findViewById(R.id.buttonAddSubject);
        database=new Database(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddSubject();
            }
        });
    }

    private Subject createSubject(){
        String subjectTitle=edtTitle.getText().toString();
        int credit=Integer.parseInt(edtCredit.getText().toString());
        String time=edtTime.getText().toString();
        String place=edtPlace.getText().toString();

        Subject subject=new Subject(subjectTitle,credit,time,place);
        return subject;
    }

    private void dialogAddSubject() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_subject);

        Button btnYes=dialog.findViewById(R.id.buttonAddYes);
        Button btnNo=dialog.findViewById(R.id.buttonAddNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleSubject=edtTitle.getText().toString().trim();
                String creditSubject=edtCredit.getText().toString().trim();
                String timeSubject=edtTime.getText().toString().trim();
                String placeSubject=edtPlace.getText().toString().trim();

                if (titleSubject.equals("") || creditSubject.equals("") || timeSubject.equals("") ||placeSubject.equals("")){
                    Toast.makeText(getApplicationContext(), "Please fill in the information!", Toast.LENGTH_SHORT).show();
                }else {
                    Subject subject=createSubject();
                    database.addSubject(subject);

                    Intent intent=new Intent(ActivityAddSubject.this,ActivitySubject.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "More success!", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }

        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}