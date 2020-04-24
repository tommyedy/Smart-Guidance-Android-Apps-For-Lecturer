package com.example.smartschedulelecturer.Lecturer.Schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartschedulelecturer.Model.schedule;
import com.example.smartschedulelecturer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditScheduleLecturer extends AppCompatActivity {
    private Button
            btnEdit,
            btnCancel;

    private ImageView ImageCancel;

    private EditText
            Et_title_guidance,
            Et_time,
            Et_date,
            Et_places;

    private static String
            titleGuidance,
            time,
            date,
            places;

    private static String
            title_guidance_change,
            time_change,
            date_change,
            places_change;

    private static String
            title_guidance,
            Time,
            Date,
            Places;

    private DatabaseReference mschedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_schedule_lecturer);
        titleGuidance        = getIntent().getExtras().getString("titleGuidance");
        time = getIntent().getExtras().getString("time");
        date = getIntent().getExtras().getString("date");
        places = getIntent().getExtras().getString("places");

        Et_title_guidance = findViewById(R.id.title_guidance_id);
        Et_time = findViewById(R.id.time_id);
        Et_date = findViewById(R.id.date_id);
        Et_places = findViewById(R.id.places_id);
        ImageCancel = findViewById(R.id.btnCancel_id);

        btnCancel = findViewById(R.id.cancel_action);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Et_title_guidance.setText(titleGuidance);
        Et_places.setText(places);
        Et_time.setText(time);
        Et_date.setText(date);





        ImageCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnEdit = findViewById(R.id.Edit_action);
        btnCancel = findViewById(R.id.cancel_action);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title_guidance = Et_title_guidance.getText().toString();
                Time = Et_time.getText().toString();
                Date = Et_date.getText().toString();
                Places = Et_places.getText().toString();
                mschedule = FirebaseDatabase.getInstance().getReference("lecturer");


//                //String nik_lecturer, nik_stat_class_id = "0";
//                schedule Schedule = new schedule(
//                        class_id,
//                        title_guidance,
//                        time,
//                        Date,
//                        Places,
//                        nik_lecturer ="0",
//                        nik_stat_class_id
//                );

//                mschedule.child()
//                        .setValue(Schedule)
//                        .addOnSuccessListener(EditScheduleLecturer.this, new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void aVoid) {
//                                Toast.makeText(EditScheduleLecturer.this, "Data Successfully Edited", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(EditScheduleLecturer.this, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(EditScheduleLecturer.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });


                // mAuth.getInstance().signOut();
                // Intent i = new Intent(getActivity(), MainActivity.class);
                // startActivity(i);
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
