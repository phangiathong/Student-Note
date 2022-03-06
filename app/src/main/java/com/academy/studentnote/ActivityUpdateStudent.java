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

public class ActivityUpdateStudent extends AppCompatActivity {

    EditText edtNameStudent, edtCodeStudent, edtBirthdayStudent;
    RadioGroup radioGroup;
    RadioButton rdMale, rdFemale;

    Button btnUpdate;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        edtNameStudent=findViewById(R.id.editTextStudentNameUpdate);
        edtCodeStudent=findViewById(R.id.editTextStudentCodeUpdate);
        edtBirthdayStudent=findViewById(R.id.editTextStudentBirthdayUpdate);
        radioGroup=findViewById(R.id.radioGroupSex);
        rdMale=findViewById(R.id.radioButtonMaleUpdate);
        rdFemale=findViewById(R.id.radioButtonFemaleUpdate);
        btnUpdate=findViewById(R.id.buttonUpdateStudent);

        Intent intent=getIntent();
        int id=intent.getIntExtra("id",0);
        String name=intent.getStringExtra("name");
        String sex=intent.getStringExtra("sex");
        String code=intent.getStringExtra("code");
        String birthday=intent.getStringExtra("birthday");
        int idSubject=intent.getIntExtra("idSubject",0);

        //Hiển thị giá trị lại
        edtNameStudent.setText(name);
        edtCodeStudent.setText(code);
        edtBirthdayStudent.setText(birthday);

        if (sex.equals("Male")) {
            radioGroup.check(R.id.radioButtonMaleUpdate);
        }else {
            radioGroup.check(R.id.radioButtonFemaleUpdate);
        }

        database=new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogUpdateStudent(id,idSubject);
            }
        });
    }

    private void dialogUpdateStudent(int id,int idSubject) {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_student);

        Button btnYes=dialog.findViewById(R.id.buttonUpdateStudentYes);
        Button btnNo=dialog.findViewById(R.id.buttonUpdateStudentNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtNameStudent.getText().toString().trim();
                String code=edtCodeStudent.getText().toString().trim();
                String birthday=edtBirthdayStudent.getText().toString().trim();

                Student student=createStudent();

                if (name.equals("") || code.equals("") || birthday.equals("")){
                    Toast.makeText(ActivityUpdateStudent.this, "Chưa điền đủ thông tin !", Toast.LENGTH_SHORT).show();
                }else {
                    database.updateStudent(student,id);

                    //Chuyển qua activity student và cập nhật lại danh sách sinh viên
                    Intent intent=new Intent(ActivityUpdateStudent.this,ActivityStudent.class);
                    intent.putExtra("idSubject",idSubject);
                    startActivity(intent);
                    Toast.makeText(ActivityUpdateStudent.this, "Cập nhật thành công !", Toast.LENGTH_SHORT).show();
                }
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
    private Student createStudent(){
        String name=edtNameStudent.getText().toString().trim();
        String code=edtCodeStudent.getText().toString().trim();
        String birthday=edtBirthdayStudent.getText().toString().trim();
        String sex="";

        if (rdMale.isChecked()){
            sex="Male";
        }else {
            sex="Female";
        }

        Student student=new Student(name,sex,code,birthday);
        return student;
    }
}