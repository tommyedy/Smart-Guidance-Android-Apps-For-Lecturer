package com.example.smartschedulelecturer.Lecturer.Schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartschedulelecturer.Lecturer.Schedule.Adapter.ListDetailScheduleAdapter;
import com.example.smartschedulelecturer.Lecturer.Schedule.Adapter.ListScheduleAdapter;
import com.example.smartschedulelecturer.Model.schedule;
import com.example.smartschedulelecturer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailScheduleLecturer extends AppCompatActivity {

    private FloatingActionButton btn_fab;
    private ImageView btn_cancel;
    private ListDetailScheduleAdapter listDetailScheduleAdapter;
    private RecyclerView recyclerView;
    private ArrayList<schedule> arrayListSchedule;
    private TextView nameClass;
    private static String
            classCode,
            codeActivation,
            status,
            Section_lecturer,
            nik_lecturer,
            nik_stat,
            Faculty_lecturer,
            timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_schedule_lecturer);
        classCode        = getIntent().getExtras().getString("classCode");
        codeActivation   = getIntent().getExtras().getString("codeActivation");
        status           = getIntent().getExtras().getString("status");
        Section_lecturer = getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer     = getIntent().getExtras().getString("nik_lecturer");
        nik_stat         = getIntent().getExtras().getString("nik_stat");
        timestamp        = getIntent().getExtras().getString("timestamp");
        Faculty_lecturer = getIntent().getExtras().getString("Faculty_lecturer");

        nameClass = findViewById(R.id.nameClass_id);
        nameClass.setText(classCode.toUpperCase());

        btn_cancel = findViewById(R.id.btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyclerView = findViewById(R.id.ListSchedule);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DetailScheduleLecturer.this));

        // arrayListSchedule = new ArrayList<schedule>();
        arrayListSchedule = new ArrayList<schedule>();

        Query dbListSchedule = FirebaseDatabase.getInstance().getReference("schedule")
                .orderByChild("nik_stat_class_id")
                .equalTo(nik_lecturer+"_"+"0"+"_"+classCode);

        dbListSchedule.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListSchedule.clear();
                for (DataSnapshot list_schedule : dataSnapshot.getChildren()){
                    if (list_schedule.exists()){
                        schedule Schedule  = list_schedule.getValue(schedule.class);
                        arrayListSchedule.add(Schedule);
                    }
                }
                listDetailScheduleAdapter = new ListDetailScheduleAdapter(DetailScheduleLecturer.this, arrayListSchedule);
                recyclerView.setAdapter(listDetailScheduleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_fab = findViewById(R.id.fab);
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailScheduleLecturer.this, AddScheduleLecturer.class);
                i.putExtra("classCode", classCode);
                i.putExtra("codeActivation", codeActivation);
                i.putExtra("status", status);
                i.putExtra("Section_lecturers", Section_lecturer);
                i.putExtra("nik_lecturer", nik_lecturer);
                i.putExtra("nik_stat", nik_stat);
                i.putExtra("timestamp", timestamp);
                i.putExtra("Faculty_lecturer", Faculty_lecturer);
                startActivity(i);
            }
        });
    }
}
