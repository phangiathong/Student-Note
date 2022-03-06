package com.academy.studentnote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.academy.studentnote.database.Database;

public class ActivitySubjectInformation extends AppCompatActivity {

    TextView txtTitle, txtCredit, txtTime, txtPlace;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_information);

        txtTitle=findViewById(R.id.textviewSubjectTitleInfor);
        txtCredit=findViewById(R.id.textviewSubjectCreditInfor);
        txtTime=findViewById(R.id.textviewSubjectTimeInfor);
        txtPlace=findViewById(R.id.textviewSubjectPlaceInfor);
        database=new Database(this);

        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        int credit=intent.getIntExtra("credit",0);
        String time=intent.getStringExtra("time");
        String place=intent.getStringExtra("place");

        txtTitle.setText(title);
        txtCredit.setText(credit+"");
        txtTime.setText(time);
        txtPlace.setText(place);

    }
}