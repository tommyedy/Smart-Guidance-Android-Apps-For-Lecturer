package com.example.smartschedulelecturer.Model;

import java.util.Date;

public class classData {
    //public String class_code, activation_code, status, faculty, section, nik_lecturer, nik_stat, created_at;
    public String activation_code, status, faculty, section, nik_lecturer, nik_stat, created_at;

    public classData(String class_code, String activation_code, String status, String faculty, String section, String nik_lecturer, String nik_stat, String created_at) {
      //  this.class_code = class_code;
        this.activation_code = activation_code;
        this.status = status;
        this.faculty = faculty;
        this.section = section;
        this.nik_lecturer = nik_lecturer;
        this.nik_stat = nik_stat;
        this.created_at = created_at;
    }

    public classData(){

    }

 //   public String getClass_code() {
  //      return class_code;
  //  }

    public String getActivation_code() {
        return activation_code;
    }

    public String getStatus() {
        return status;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSection() {
        return section;
    }

    public String getNik_lecturer() {
        return nik_lecturer;
    }

 //   public void setClass_code(String class_code) {
//        this.class_code = class_code;
//    }

    public void setActivation_code(String activation_code) {
        this.activation_code = activation_code;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setNik_lecturer(String nik_lecturer) {
        this.nik_lecturer = nik_lecturer;
    }

    public String getNik_stat() {
        return nik_stat;
    }

    public void setNik_stat(String nik_stat) {
        this.nik_stat = nik_stat;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
