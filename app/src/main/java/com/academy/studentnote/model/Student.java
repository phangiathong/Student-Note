package com.academy.studentnote.model;

public class Student {

    private int idStudent;
    private String nameStudent;
    private String sexStudent;
    private String codeStudent;
    private String dateOfBirthStudent;
    private int idSubject;

    public Student(int idStudent, String nameStudent, String sexStudent, String codeStudent, String dateOfBirthStudent, int idSubject) {
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.sexStudent = sexStudent;
        this.codeStudent = codeStudent;
        this.dateOfBirthStudent = dateOfBirthStudent;
        this.idSubject = idSubject;
    }

    public Student(String nameStudent, String sexStudent, String codeStudent, String dateOfBirthStudent, int idSubject) {
        this.nameStudent = nameStudent;
        this.sexStudent = sexStudent;
        this.codeStudent = codeStudent;
        this.dateOfBirthStudent = dateOfBirthStudent;
        this.idSubject = idSubject;
    }

    public Student(String nameStudent, String sexStudent, String codeStudent, String dateOfBirthStudent) {
        this.nameStudent = nameStudent;
        this.sexStudent = sexStudent;
        this.codeStudent = codeStudent;
        this.dateOfBirthStudent = dateOfBirthStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getSexStudent() {
        return sexStudent;
    }

    public void setSexStudent(String sexStudent) {
        this.sexStudent = sexStudent;
    }

    public String getCodeStudent() {
        return codeStudent;
    }

    public void setCodeStudent(String codeStudent) {
        this.codeStudent = codeStudent;
    }

    public String getDateOfBirthStudent() {
        return dateOfBirthStudent;
    }

    public void setDateOfBirthStudent(String dateOfBirthStudent) {
        this.dateOfBirthStudent = dateOfBirthStudent;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }
}
