package com.academy.studentnote.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.academy.studentnote.model.Student;
import com.academy.studentnote.model.Subject;

public class Database extends SQLiteOpenHelper {

    //Database name
    private static String DATABASE_NAME = "studentmanagement";

    //Table subject
    private static String TABLE_SUBJECTS = "subject";
    private static String ID_SUBJECTS = "idsubject";
    private static String SUBJECT_TITLE = "subjecttitle";
    private static String CREDITS = "credits";
    private static String TIME = "time";
    private static String PLACE = "place";
    private static int VERSION = 1;

    //Table sutdent
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STUDENT_NAME = "sudentname";
    private static String SEX = "sex";
    private static String STUDENT_CODE = "studentcode";
    private static String DATE_OF_BIRTH = "dateofbirth";

    //Create talbe subject
    private String SQLQuery = "CREATE TABLE " + TABLE_SUBJECTS + " ( " + ID_SUBJECTS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + SUBJECT_TITLE + " TEXT, "
            + CREDITS + " INTEGER, "
            + TIME + " TEXT, "
            + PLACE + " TEXT) ";

    //Create table student
    private String SQLQuery1 = "CREATE TABLE " + TABLE_STUDENT + " ( " + ID_STUDENT + " integer primary key AUTOINCREMENT, "
            + STUDENT_NAME + " TEXT, "
            + SEX + " TEXT, "
            + STUDENT_CODE + " TEXT, "
            + DATE_OF_BIRTH + " TEXT, "
            + ID_SUBJECTS + " INTEGER , FOREIGN KEY ( " + ID_SUBJECTS + " ) REFERENCES " +
            TABLE_SUBJECTS + "(" + ID_SUBJECTS + "))";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Delete student of subject
    public void deleteSubjectStudent(int i) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_STUDENT, ID_SUBJECTS + "=" + i, null);
    }

    //Add student
    public void addStudent(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getNameStudent());
        values.put(SEX, student.getSexStudent());
        values.put(STUDENT_CODE, student.getCodeStudent());
        values.put(DATE_OF_BIRTH, student.getDateOfBirthStudent());
        values.put(ID_SUBJECTS, student.getIdSubject());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    //update student
    public boolean updateStudent(Student student,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(STUDENT_NAME,student.getNameStudent());
        values.put(STUDENT_CODE,student.getCodeStudent());
        values.put(SEX,student.getSexStudent());
        values.put(DATE_OF_BIRTH,student.getDateOfBirthStudent());

        db.update(TABLE_STUDENT,values,ID_STUDENT+" = "+id,null);

        return true;
    }

    //Delete student
    public int deleteStudent(int i){
        SQLiteDatabase db=this.getWritableDatabase();
        int res=db.delete(TABLE_STUDENT,ID_STUDENT+"="+i,null);
        return res;
    }
    //Delete subject
    public int deleteSubject(int id) {
        SQLiteDatabase db = getWritableDatabase();
        int res = db.delete(TABLE_SUBJECTS, ID_SUBJECTS + "=" + id, null);
        return res;
    }

    //Get data subject
    public Cursor getDataSubject() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SUBJECTS, null);
        return cursor;
    }

    //Get data student
    public Cursor getDataStudent(int idSubject) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT + " WHERE " + ID_SUBJECTS + "=" + idSubject, null);
        return cursor;
    }

    public boolean updateSubject(Subject subject, int id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBJECT_TITLE, subject.getSubjectTitle());
        values.put(CREDITS, subject.getNumberOfCredit());
        values.put(TIME, subject.getTime());
        values.put(PLACE, subject.getPlace());

        db.update(TABLE_SUBJECTS, values, ID_SUBJECTS + "=" + id, null);
        return true;
    }

    public void addSubject(Subject subject) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBJECT_TITLE, subject.getSubjectTitle());
        values.put(CREDITS, subject.getNumberOfCredit());
        values.put(TIME, subject.getTime());
        values.put(PLACE, subject.getPlace());

        db.insert(TABLE_SUBJECTS, null, values);
        db.close();
    }
}
