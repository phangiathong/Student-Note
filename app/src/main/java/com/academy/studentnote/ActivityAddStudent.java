package com.academy.studentnote;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.academy.studentnote.database.Database;
import com.academy.studentnote.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    EditText edtNameStudent, edtCodeStudent, edtBirthdayStudent;
    RadioGroup rdoGroupSex;
    RadioButton rdoButtonSex;
    Button btnAddStudent;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        edtNameStudent=findViewById(R.id.editTextStudentName);
        edtCodeStudent=findViewById(R.id.editTextStudentCode);
        edtBirthdayStudent=findViewById(R.id.editTextStudentBirthday);
        rdoGroupSex=findViewById(R.id.radioGroupSex);
        rdoButtonSex=findViewById(R.id.radioButtonMale);
        rdoButtonSex=findViewById(R.id.radioButtonFemale);
        btnAddStudent=findViewById(R.id.buttonAddStudent);
        database=new Database(this);
        Intent intent=getIntent();
        int idSubject=intent.getIntExtra("idSubject",0);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddStudent(idSubject);
            }
        });
    }

    private void dialogAddStudent(int idSubject) {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_student);
        Button btnYes=dialog.findViewById(R.id.buttonAddStudentYes);
        Button btnNo=dialog.findViewById(R.id.buttonAddStudentNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameStudent=edtNameStudent.getText().toString().trim();
                int idButtonSex=rdoGroupSex.getCheckedRadioButtonId();
                rdoButtonSex=findViewById(idButtonSex);
                String sexStudent= (String) rdoButtonSex.getText();
                String codeStudent=edtCodeStudent.getText().toString().trim();
                String birthDayStudent=edtBirthdayStudent.getText().toString().trim();

                if (nameStudent.equals("") || sexStudent.equals("") || codeStudent.equals("") ||birthDayStudent.equals("") ){
                    Toast.makeText(getApplicationContext(), "Please fill in the information!", Toast.LENGTH_SHORT).show();
                }else {
                    Student student=new Student(nameStudent,sexStudent,codeStudent,birthDayStudent,idSubject);
                    database.addStudent(student);
                    Intent intent1=new Intent(ActivityAddStudent.this,ActivityStudent.class);
                    intent1.putExtra("idSubject",idSubject);
                    startActivity(intent1);
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