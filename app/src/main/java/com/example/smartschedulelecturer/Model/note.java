package com.example.smartschedulelecturer.Model;

import java.util.Date;

public class note {
    public String note_title, nim_students, students_name,  note, status, classCode, date;

    public note(String note_title, String nim_students, String students_name, String note, String status, String classCode, String date) {
        this.note_title = note_title;
        this.nim_students = nim_students;
        this.students_name = students_name;
        this.note = note;
        this.status = status;
        this.classCode = classCode;
        this.date = date;
    }

    public note(){

    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNim_students() {
        return nim_students;
    }

    public void setNim_students(String nim_students) {
        this.nim_students = nim_students;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getStudents_name() {
        return students_name;
    }

    public void setStudents_name(String students_name) {
        this.students_name = students_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
