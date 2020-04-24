package com.example.smartschedulelecturer.Model;

public class Section {
    public String section_name, faculty_id, section_id;

    public Section(){

    }

    public Section(String section_name, String faculty_id, String section_id) {
        this.section_name = section_name;
        this.faculty_id = faculty_id;
        this.section_id = section_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public String getFaculty_id() {
        return faculty_id;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public void setFaculty_id(String faculty_id) {
        this.faculty_id = faculty_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }
}
