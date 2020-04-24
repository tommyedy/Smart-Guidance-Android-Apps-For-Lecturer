package com.example.smartschedulelecturer.Model;

public class userStudents {
    private String nim, firstname, lastname, email, faculty, section, register_date;


    public userStudents(String nim, String firstname, String lastname, String email, String faculty, String section, String register_date) {
        this.nim = nim;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.faculty = faculty;
        this.section = section;
        this.register_date = register_date;
    }

    public userStudents(){

    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
