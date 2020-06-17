package com.example.smartschedulelecturer.Model;

import java.util.Date;

public class userDetails {
    public String uid, identity_number, register_date;

    public userDetails(String uid, String identity_number, String register_date) {
        this.uid = uid;
        this.identity_number = identity_number;
        this.register_date = register_date;
    }

    public userDetails(){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

}

