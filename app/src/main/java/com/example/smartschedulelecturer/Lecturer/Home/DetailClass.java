package com.example.smartschedulelecturer.Lecturer.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartschedulelecturer.Lecturer.Home.Adapter.ListClassDetailAdapter;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.Model.detailClass;
import com.example.smartschedulelecturer.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Timestamp;
import java.util.ArrayList;

public class DetailClass extends AppCompatActivity {
    private ImageView btn_delete, btn_cancel;
    private TextView txtCodeClass;
    private DatabaseReference mDetailClass;
    private static String
            classCode,
            codeActivation,
            status,
            Section_lecturer,
            nik_lecturer,
            nik_stat,
            Faculty_lecturer,
            timestamp;
    private ListClassDetailAdapter list_detail_class_adapter;
    private RecyclerView recyclerView;
    private ArrayList<detailClass> list_detail_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_class_lecturer);
        classCode        = getIntent().getExtras().getString("classCode");
        codeActivation   = getIntent().getExtras().getString("codeActivation");
        status           = getIntent().getExtras().getString("status");
        Section_lecturer = getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer     = getIntent().getExtras().getString("nik_lecturer");
        nik_stat         = getIntent().getExtras().getString("nik_stat");
        timestamp        = getIntent().getExtras().getString("timestamp");
        Faculty_lecturer = getIntent().getExtras().getString("Faculty_lecturer");

        btn_delete = findViewById(R.id.btnDelete_id);
        btn_cancel = findViewById(R.id.btnCancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        txtCodeClass = findViewById(R.id.nameClass_id);
        txtCodeClass.setText(classCode);

        recyclerView = findViewById(R.id.listPeopleRequest);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list_detail_class = new ArrayList<detailClass>();

        Query dbListdetailClass = FirebaseDatabase.getInstance().getReference("detail_class")
                .orderByChild("classCode_stat")
                .equalTo(classCode+"_"+"0");

        dbListdetailClass.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list_detail_class.clear();
                    for (DataSnapshot list_detail_class_ : dataSnapshot.getChildren()) {
                        if (dataSnapshot.exists()){
                            detailClass detailclass = list_detail_class_.getValue(detailClass.class);
                            //  if (kelas.fac_sec_stat.equals(where)) {
                            list_detail_class.add(detailclass);
                            //list.add(kelas);
                        }
                    }
                    list_detail_class_adapter = new ListClassDetailAdapter(DetailClass.this, list_detail_class);
                    recyclerView.setAdapter(list_detail_class_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailClass.this, ""+ databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailClass.this);

                // Set a title for alert dialog
                builder.setTitle("Dialog Confirmation");

                // Ask the final question
                builder.setMessage("Delete Class?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when user clicked the Yes button
                        // Set the TextView visibility GONE

                        Query DetailClass =  FirebaseDatabase.getInstance().getReference("class");
                        DetailClass.orderByChild("class_code")
                                .equalTo(classCode)
                                .addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        classData classdata = new classData(
                                                classCode,
                                                codeActivation,
                                                "1",
                                                Faculty_lecturer,
                                                Section_lecturer,
                                                nik_lecturer,
                                                nik_lecturer+"_"+"1",
                                                timestamp
                                        );
                                        mDetailClass = FirebaseDatabase.getInstance().getReference("class");
                                        mDetailClass.child(dataSnapshot.getKey())
                                                .setValue(classdata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                getIntent().removeExtra("classCode");
                                                getIntent().removeExtra("codeActivation");
                                                getIntent().removeExtra("status");
                                                getIntent().removeExtra("Faculty_lecturer");
                                                getIntent().removeExtra("Section_lecturer");
                                                getIntent().removeExtra("nik_stat");
                                                getIntent().removeExtra("timestamp");
                                                getIntent().removeExtra("nik_lecturer");
                                                Toast.makeText(getApplicationContext(), "Class Delete Succesfully", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
            }

        });

    }

}
