package com.academy.studentnote;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.academy.studentnote.adapter.AdapterStudent;
import com.academy.studentnote.database.Database;
import com.academy.studentnote.model.Student;

import java.util.ArrayList;

public class ActivityStudent extends AppCompatActivity {

    ListView lvStudent;
    ArrayList<Student> studentArrayList;
    AdapterStudent adapterStudent;
    Toolbar toolbar;

    Database database;
    int id_subject=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        toolbar=findViewById(R.id.toolbarStudent);
        lvStudent=findViewById(R.id.listviewStudent);
        database=new Database(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        studentArrayList=new ArrayList<>();
        studentArrayList.clear();

        Intent intent=getIntent();
        id_subject=intent.getIntExtra("idSubject",0);

        Cursor cursor=database.getDataStudent(id_subject);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String nameStudent=cursor.getString(1);
            String sexStudent=cursor.getString(2);
            String codeStudent=cursor.getString(3);
            String birthdayStudent=cursor.getString(4);
            int idSb=cursor.getInt(5);
            studentArrayList.add(new Student(id,nameStudent,sexStudent,codeStudent,birthdayStudent,idSb));
        }
        adapterStudent=new AdapterStudent(ActivityStudent.this,studentArrayList);
        lvStudent.setAdapter(adapterStudent);

        cursor.moveToNext();
        cursor.close();
    }

    public void deleteStudent(final int idStudent){
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_student);
        dialog.setCanceledOnTouchOutside(true);

        Button btnYes=dialog.findViewById(R.id.buttonYesDeleteStudent);
        Button btnNo=dialog.findViewById(R.id.buttonNoDeleteStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteStudent(idStudent);

                //Open Activity Student again.
                Intent intent=new Intent(ActivityStudent.this,ActivityStudent.class);
                intent.putExtra("idSubject",id_subject);
                startActivity(intent);

                Toast.makeText(ActivityStudent.this, "More delete !", Toast.LENGTH_SHORT).show();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void updateStudent(int idStudent) {
        Cursor cursor = database.getDataStudent(id_subject);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id==idStudent){
                Intent intent = new Intent(ActivityStudent.this, ActivityUpdateStudent.class);
                intent.putExtra("id",idStudent);
                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);
                int idSubject=cursor.getInt(5);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                intent.putExtra("idSubject",idSubject);

                startActivity(intent);
            }
        }
        cursor.close();
    }

    public void informationStudent(final int pos) {
        Cursor cursor = database.getDataStudent(id_subject);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(ActivityStudent.this, ActivityInformationStudent.class);
                intent.putExtra("id", id);
                String name = cursor.getString(1);
                String sex = cursor.getString(2);
                String code = cursor.getString(3);
                String birthday = cursor.getString(4);

                intent.putExtra("name", name);
                intent.putExtra("sex", sex);
                intent.putExtra("code", code);
                intent.putExtra("birthday", birthday);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuAddStudent:
                Intent intent = new Intent(ActivityStudent.this, ActivityAddStudent.class);
                intent.putExtra("idSubject",id_subject);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(ActivityStudent.this, ActivitySubject.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}