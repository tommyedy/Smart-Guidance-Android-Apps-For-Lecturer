package com.example.smartschedulelecturer.Model;

public class userLecturer {
    private static String nik, firstname, lastname, email, faculty, section, register_date;



    public userLecturer(String nik, String firstname, String lastname, String email, String faculty, String section) {
        this.nik = nik;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.faculty = faculty;
        this.section = section;
    }

    public userLecturer(){

    }

    public String getNik() {
        return nik;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSection() {
        return section;
    }

    public static void setNik(String nik) {
        userLecturer.nik = nik;
    }

    public static void setFirstname(String firstname) {
        userLecturer.firstname = firstname;
    }

    public static void setLastname(String lastname) {
        userLecturer.lastname = lastname;
    }

    public static void setEmail(String email) {
        userLecturer.email = email;
    }

    public static void setFaculty(String faculty) {
        userLecturer.faculty = faculty;
    }

    public static void setSection(String section) {
        userLecturer.section = section;
    }

    public static String getRegister_date() {
        return register_date;
    }

    public static void setRegister_date(String register_date) {
        userLecturer.register_date = register_date;
    }
}

