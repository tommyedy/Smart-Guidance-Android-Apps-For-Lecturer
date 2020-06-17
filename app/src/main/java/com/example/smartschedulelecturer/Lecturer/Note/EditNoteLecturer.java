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

public class EditNoteLecturer extends AppCompatActivity {
    private Button BtnEdit;

    private ImageView ImgCancel;

    private EditText
            title_guidance,
            students_nim,
            note;

    private static String
            Title_guidance,
            Students_nim,
            Notes;

    private DatabaseReference mNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note_lecturer);
        BtnEdit = findViewById(R.id.edit_profile_id);
        ImgCancel = findViewById(R.id.btnCancel);

        note = findViewById(R.id.noteLecturer_id);
        title_guidance = findViewById(R.id.title_guidance_id);
        students_nim = findViewById(R.id.nimStudents_id);

        Title_guidance = title_guidance.getText().toString().trim();
        Notes = note.getText().toString().trim();
        Students_nim = students_nim.getText().toString().trim();

        BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title_guidance = title_guidance.getText().toString().trim();
                Notes = note.getText().toString().trim();
                Students_nim = students_nim.getText().toString().trim();

                mNote = FirebaseDatabase.getInstance().getReference("note");
                com.example.smartschedulelecturer.Model.note Note = new note ();

                mNote.push().setValue(Note).addOnSuccessListener(EditNoteLecturer.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Data Successfully Edited", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });


    }
}
