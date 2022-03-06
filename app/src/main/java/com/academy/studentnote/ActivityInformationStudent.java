package com.academy.studentnote;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityInformationStudent extends AppCompatActivity {

    TextView txtNameStudent, txtSexStudent, txtCodeStudent, txtBirthdayStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_student);

        txtNameStudent=findViewById(R.id.textviewStudentNameInfor);
        txtSexStudent=findViewById(R.id.textviewStudentSexInfor);
        txtCodeStudent=findViewById(R.id.textviewStudentCodeInfor);
        txtBirthdayStudent=findViewById(R.id.textviewStudentBirthdayInfor);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String sex=intent.getStringExtra("sex");
        String code=intent.getStringExtra("code");
        String birthday=intent.getStringExtra("birthday");

        txtNameStudent.setText(name);
        txtSexStudent.setText(sex);
        txtCodeStudent.setText(code);
        txtBirthdayStudent.setText(birthday);
    }
}