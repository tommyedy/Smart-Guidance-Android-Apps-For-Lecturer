package com.example.smartschedulelecturer.Lecturer.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Lecturer.Home.Adapter.ListClassAdapter;
import com.example.smartschedulelecturer.Lecturer.Schedule.Adapter.ListScheduleAdapter;
import com.example.smartschedulelecturer.Lecturer.Schedule.AddScheduleLecturer;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.Model.schedule;
import com.example.smartschedulelecturer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_schedule_lecturer extends Fragment {
    private FloatingActionButton btn_fab;
    //private ListClassAdapter listClassAdapter;// list_schedule_adapter;
    private ListScheduleAdapter listScheduleAdapter;
    private RecyclerView recyclerView;
    private ArrayList<classData> arrayListSchedule;
    //private ArrayList<schedule> arrayListSchedule;
  //  private ArrayList<classData> arrayListClassData;

    private static String
            firstname_lecturer,
            lastname_lecturer,
            email_lecturer,
            Faculty_lecturer,
            Section_lecturer,
            nik_lecturer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_lecturer, container, false);
        firstname_lecturer = getActivity().getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer = getActivity().getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer = getActivity().getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer = getActivity().getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer = getActivity().getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer = getActivity().getIntent().getExtras().getString("nik_lecturer");

        recyclerView = view.findViewById(R.id.ListSchedule);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // arrayListSchedule = new ArrayList<schedule>();
        arrayListSchedule = new ArrayList<classData>();

        Query dbListSchedule = FirebaseDatabase.getInstance().getReference("class")
                .orderByChild("nik_stat")
                .equalTo(nik_lecturer+"_"+"0");

        dbListSchedule.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListSchedule.clear();
                for (DataSnapshot list_schedule : dataSnapshot.getChildren()){
                    if (list_schedule.exists()){
                        classData classdata  = list_schedule.getValue(classData.class);
                        arrayListSchedule.add(classdata);
                    }
                }
                listScheduleAdapter = new ListScheduleAdapter(getActivity(), arrayListSchedule);
                recyclerView.setAdapter(listScheduleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        btn_fab = view.findViewById(R.id.fab);
//        btn_fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddScheduleLecturer.class);
//                startActivity(i);
//            }
//        });
        return view;
    }
}
