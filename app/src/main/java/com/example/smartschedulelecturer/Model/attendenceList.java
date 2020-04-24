package com.example.smartschedulelecturer.Model;


public class attendenceList {
    public String id_schedule, nim_students, faculty, section, status;

    public attendenceList(String id_schedule, String nim_students, String faculty, String section, String status) {
        this.id_schedule = id_schedule;
        this.nim_students = nim_students;
        this.faculty = faculty;
        this.section = section;
        this.status = status;
    }

    public attendenceList(){

    }

    public String getId_schedule() {
        return id_schedule;
    }

    public void setId_schedule(String id_schedule) {
        this.id_schedule = id_schedule;
    }

    public String getNim_students() {
        return nim_students;
    }

    public void setNim_students(String nim_students) {
        this.nim_students = nim_students;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
