package com.example.smartschedulelecturer.Model;

public class faculty {
    public String faculty_code, faculty_name;
    public faculty(){

    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public String getFaculty_code() {
        return faculty_code;
    }

    public void setFaculty_code(String faculty_code) {
        this.faculty_code = faculty_code;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }
}
