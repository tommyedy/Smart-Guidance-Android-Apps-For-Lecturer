package com.example.smartschedulelecturer.Model;

import java.util.Date;

public class schedule {
    public String class_id, date, places,  time, title_guidance, nik_lecturer, nik_stat_class_id, created_at;


    public schedule(String class_id, String date, String places, String time, String title_guidance, String nik_lecturer, String nik_stat_class_id, String created_at) {
        this.class_id = class_id;
        this.date = date;
        this.places = places;
        this.time = time;
        this.title_guidance = title_guidance;
        this.nik_lecturer = nik_lecturer;
        this.nik_stat_class_id = nik_stat_class_id;
        this.created_at = created_at;
    }

    public schedule(){

    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlaces() {
        return places;
    }

    public void setPlaces(String places) {
        this.places = places;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle_guidance() {
        return title_guidance;
    }

    public void setTitle_guidance(String title_guidance) {
        this.title_guidance = title_guidance;
    }

    public String getNik_lecturer() {
        return nik_lecturer;
    }

    public void setNik_lecturer(String nik_lecturer) {
        this.nik_lecturer = nik_lecturer;
    }

    public String getNik_stat_class_id() {
        return nik_stat_class_id;
    }

    public void setNik_stat_class_id(String nik_stat_class_id) {
        this.nik_stat_class_id = nik_stat_class_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
