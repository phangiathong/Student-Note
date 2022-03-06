package com.academy.studentnote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.academy.studentnote.ActivityStudent;
import com.academy.studentnote.R;
import com.academy.studentnote.model.Student;

import java.util.ArrayList;

public class AdapterStudent extends BaseAdapter {

    ActivityStudent context;
    ArrayList<Student> studentArrayList;

    public AdapterStudent(ActivityStudent context, ArrayList<Student> studentArrayList) {
        this.context = context;
        this.studentArrayList = studentArrayList;
    }

    @Override
    public int getCount() {
        return studentArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.list_student,null);
        TextView txtStudentName=convertView.findViewById(R.id.textviewStudentName);
        TextView txtStudentCode=convertView.findViewById(R.id.textviewStudentCode);
        ImageButton imgInforStudent=convertView.findViewById(R.id.imageButtonInforStudent);
        ImageButton imgUpdateStudent=convertView.findViewById(R.id.imageButtonUpdateStudent);
        ImageButton imgDeleteStudent=convertView.findViewById(R.id.imageButtonDeleteStudent);

        Student student=studentArrayList.get(position);
        txtStudentName.setText(student.getNameStudent());
        txtStudentCode.setText(student.getCodeStudent());
        int idStudent=studentArrayList.get(position).getIdStudent();

        imgInforStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.informationStudent(idStudent);
            }
        });

        imgUpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateStudent(idStudent);
            }
        });

        imgDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteStudent(idStudent);
            }
        });

        return convertView;
    }
}
