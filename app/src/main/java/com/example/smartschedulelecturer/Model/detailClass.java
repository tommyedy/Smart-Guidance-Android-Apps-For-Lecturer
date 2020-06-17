package com.example.smartschedulelecturer.Model;

import java.util.Date;

public class detailClass {
    public String idObjectCLass, nim_students, status, students_name, nik_stat, faculty, section, join_date, join_date_acc;

    public detailClass(String idObjectCLass, String nim_students, String status, String students_name, String nik_stat, String faculty, String section, String join_date, String join_date_acc) {
        this.idObjectCLass = idObjectCLass;
        this.nim_students = nim_students;
        this.status = status;
        this.students_name = students_name;
        this.nik_stat = nik_stat;
        this.faculty = faculty;
        this.section = section;
        this.join_date = join_date;
        this.join_date_acc = join_date_acc;
    }

    public detailClass(){

    }

    public String getIdObjectCLass() {
        return idObjectCLass;
    }

    public void setIdObjectCLass(String idObjectCLass) {
        this.idObjectCLass = idObjectCLass;
    }

    public String getNim_students() {
        return nim_students;
    }

    public void setNim_students(String nim_students) {
        this.nim_students = nim_students;
    }

    public String getStudents_name() {
        return students_name;
    }

    public void setStudents_name(String students_name) {
        this.students_name = students_name;
    }

    public String getNik_stat() {
        return nik_stat;
    }

    public void setNik_stat(String nik_stat) {
        this.nik_stat = nik_stat;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getJoin_date_acc() {
        return join_date_acc;
    }

    public void setJoin_date_acc(String join_date_acc) {
        this.join_date_acc = join_date_acc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
