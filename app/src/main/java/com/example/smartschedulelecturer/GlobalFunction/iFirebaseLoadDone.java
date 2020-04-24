package com.example.smartschedulelecturer.GlobalFunction;

import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.Model.faculty;
import com.example.smartschedulelecturer.Model.Section;

import java.util.List;

public interface iFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<faculty> faculty);
    void onFirebaseLoadSuccess1(List<Section> section);



    void onFirebaseLoadFailed(String message);
    void onFirebaseLoadFailed1(String message);
    void onFirebaseLoadSuccess2(List<classData> ClassData);
    void onFirebaseLoadSuccess3(List<Section> section);
}
