package com.academy.studentnote;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.academy.studentnote.database.Database;
import com.academy.studentnote.model.Subject;

public class ActivityUpdateSubject extends AppCompatActivity {

    EditText edtTittle, edtCredit, edtTime, edtPlace;
    Button btnUpdate;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_subject);

        edtTittle=findViewById(R.id.editTextUpdateSubjectTitle);
        edtCredit=findViewById(R.id.editTextUpdateSubjectCredit);
        edtTime=findViewById(R.id.editTextUpdateSubjectTime);
        edtPlace=findViewById(R.id.editTextUpdateSubjectPlace);
        btnUpdate=findViewById(R.id.buttonUpdateSubject);
        database=new Database(this);

        Intent intent=getIntent();
        int id=intent.getIntExtra("idSubject",0);
        String title=intent.getStringExtra("title");
        int credit=intent.getIntExtra("credit",0);
        String time=intent.getStringExtra("time");
        String place=intent.getStringExtra("place");

        edtTittle.setText(title);
        edtCredit.setText(credit+"");
        edtTime.setText(time);
        edtPlace.setText(place);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateSubject(id);
            }
        });
    }

    public void dialogUpdateSubject(int id){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_subject);

        Button btnYes=dialog.findViewById(R.id.buttonUpdateSubjectYes);
        Button btnNo=dialog.findViewById(R.id.buttonUpdateSubjectNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=edtTittle.getText().toString().trim();
                int credit=Integer.parseInt(edtCredit.getText().toString().trim());
                String time=edtTime.getText().toString().trim();
                String place=edtPlace.getText().toString().trim();

                Subject subject=new Subject(title,credit,time,place);
                database.updateSubject(subject,id);
                Toast.makeText(getApplicationContext(), "Update successful!", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(ActivityUpdateSubject.this,ActivitySubject.class);
                startActivity(intent1);
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