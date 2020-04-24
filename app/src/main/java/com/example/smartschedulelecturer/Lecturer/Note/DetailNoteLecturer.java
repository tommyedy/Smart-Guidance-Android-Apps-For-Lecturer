package com.example.smartschedulelecturer.Lecturer.Note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartschedulelecturer.Lecturer.Schedule.AddScheduleLecturer;
import com.example.smartschedulelecturer.Lecturer.Schedule.DetailScheduleLecturer;
import com.example.smartschedulelecturer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailNoteLecturer extends AppCompatActivity {
    private FloatingActionButton btn_fab;
    private ImageView btn_cancel;
    private TextView captionClass;
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
        setContentView(R.layout.activity_detail_note_lecturer);
        classCode        = getIntent().getExtras().getString("classCode");
//        codeActivation   = getIntent().getExtras().getString("codeActivation");
//        status           = getIntent().getExtras().getString("status");
//        Section_lecturer = getIntent().getExtras().getString("Section_lecturer");
//        nik_lecturer     = getIntent().getExtras().getString("nik_lecturer");
//        nik_stat         = getIntent().getExtras().getString("nik_stat");
//        timestamp        = getIntent().getExtras().getString("timestamp");
//        Faculty_lecturer = getIntent().getExtras().getString("Faculty_lecturer");

        captionClass = findViewById(R.id.captionClass);
        captionClass.setText(classCode);

        btn_cancel = findViewById(R.id.btnCancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_fab = findViewById(R.id.fab);
        btn_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailNoteLecturer.this, AddNoteLecturer.class);
                startActivity(i);
            }
        });
    }
}
