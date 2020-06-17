package com.example.smartschedulelecturer.Lecturer.Note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartschedulelecturer.Model.note;
import com.example.smartschedulelecturer.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Timestamp;
import java.util.Date;

public class AddNoteLecturer extends AppCompatActivity {
    private Button BtnAdd;

    private ImageView ImgCancel;

    private SearchableSpinner
            title_guidance,
            students_nim;
    private EditText note;

    private static String
            Title_guidance,
            Students_nim,
            Notes;

    private DatabaseReference
            mNote,
            mDataStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_lecturer);

        title_guidance = findViewById(R.id.titleSpinnerLecturer_id);
        students_nim = findViewById(R.id.nimSpinnerLecturer_id);

        note = findViewById(R.id.noteLecturer_id);

        ImgCancel = findViewById(R.id.btnCancel);
        ImgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mDataStudents  = FirebaseDatabase.getInstance().getReference("faculty");


        BtnAdd = findViewById(R.id.btnAddNote_id);
        BtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title_guidance = title_guidance.getSelectedItem().toString().trim();
                Students_nim = students_nim.getSelectedItem().toString().trim();
                Notes = note.getText().toString().trim();
                String status = "0";
                String classCode = "123";
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                Date date = timestamp;
                mNote = FirebaseDatabase.getInstance().getReference("note");

                com.example.smartschedulelecturer.Model.note Note = new note(
                        Title_guidance,
                        Students_nim,
                        "",
                        Notes,
                        status,
                        classCode,
                        date.toString()
                );
                mNote.push().setValue(Note).addOnSuccessListener(AddNoteLecturer.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data Successfully Added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });


            }
        });
    }
}
