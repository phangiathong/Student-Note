package com.academy.studentnote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.academy.studentnote.ActivitySubject;
import com.academy.studentnote.R;
import com.academy.studentnote.model.Subject;

import java.util.ArrayList;

public class AdapterSubject extends BaseAdapter {

    private ActivitySubject context;
    private ArrayList<Subject> ArrayListSubject;

    public AdapterSubject(ActivitySubject context, ArrayList<Subject> arrayListSubject) {
        this.context = context;
        ArrayListSubject = arrayListSubject;
    }

    @Override
    public int getCount() {
        return ArrayListSubject.size();
    }

    @Override
    public Object getItem(int position) {
        return ArrayListSubject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(R.layout.list_subject,null);
        TextView txtSubjectTitle=convertView.findViewById(R.id.textviewSubjectTitle);
        TextView txtCredit=convertView.findViewById(R.id.textviewCredit);
        ImageButton imgBtnSubject=convertView.findViewById(R.id.imageButtonSubject);
        ImageButton imgBtnUpdate=convertView.findViewById(R.id.imageButtonUpdate);
        ImageButton imgBtnDelete=convertView.findViewById(R.id.imageButtonDelete);

        Subject subject=ArrayListSubject.get(position);
        txtSubjectTitle.setText(subject.getSubjectTitle());
        txtCredit.setText(subject.getNumberOfCredit()+"");
        int id=subject.getId();

        imgBtnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.information(id);
            }
        });

        imgBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.updateSubject(id);
            }
        });

        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.deleteSubject(id);
            }
        });

        return convertView;
    }
}
