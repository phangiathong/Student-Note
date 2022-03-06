package com.academy.studentnote;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.academy.studentnote.adapter.AdapterSubject;
import com.academy.studentnote.database.Database;
import com.academy.studentnote.model.Subject;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    ListView listViewSubject;
    ArrayList<Subject> arrayListSubject;
    AdapterSubject adapterSubject;
    Database database;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewSubject = findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayListSubject = new ArrayList<>();
        database = new Database(this);

        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String subjectTitle = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            arrayListSubject.add(new Subject(id, subjectTitle, credit, time, place));
        }
        adapterSubject = new AdapterSubject(this, arrayListSubject);
        listViewSubject.setAdapter(adapterSubject);

        cursor.moveToNext();
        cursor.close();

        listViewSubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivitySubject.this, ActivityStudent.class);
                int idSubject = arrayListSubject.get(position).getId();
                intent.putExtra("idSubject", idSubject);
                startActivity(intent);
            }
        });
    }

    public void updateSubject(int idSubject) {

        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id==idSubject){
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                Intent intent = new Intent(ActivitySubject.this, ActivityUpdateSubject.class);
                intent.putExtra("idSubject",id);
                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);
                startActivity(intent);
            }
        }
    }

    public void deleteSubject(int position) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_delete_subject);
        Button btnYes = dialog.findViewById(R.id.buttonDeleteYes);
        Button btnNo = dialog.findViewById(R.id.buttonDeleteNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteSubject(position);
                database.deleteSubjectStudent(position);
                dialog.dismiss();
                //Cập nhật lại activity subject
                Intent intent=new Intent(ActivitySubject.this,ActivitySubject.class);
                startActivity(intent);
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

    public void information(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(ActivitySubject.this, ActivitySubjectInformation.class);
                intent.putExtra("id", id);
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuAdd:
                Intent intent = new Intent(ActivitySubject.this, ActivityAddSubject.class);
                startActivity(intent);
                break;
            default:
                Intent intent1 = new Intent(ActivitySubject.this, ActivitySubject.class);
                startActivity(intent1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}