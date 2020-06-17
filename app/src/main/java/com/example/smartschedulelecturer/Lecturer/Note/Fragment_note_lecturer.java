package com.example.smartschedulelecturer.Lecturer.Note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Lecturer.Note.Adapter.ListNotedapter;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_note_lecturer extends Fragment {
    private ListNotedapter listnotedapter;
    private RecyclerView recyclerView;
    private ArrayList<classData> arrayListClassData;//list;
    private FloatingActionButton btn_fab;
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
        View view = inflater.inflate(R.layout.fragment_note_lecturer, container, false);
        firstname_lecturer = getActivity().getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer = getActivity().getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer = getActivity().getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer = getActivity().getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer = getActivity().getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer = getActivity().getIntent().getExtras().getString("nik_lecturer");
       // btn_fab = view.findViewById(R.id.fab);


        recyclerView = view.findViewById(R.id.ListSchedule);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        arrayListClassData = new ArrayList<classData>();

        Query dbListNote = FirebaseDatabase.getInstance().getReference("class")
                .orderByChild("nik_stat")
                .equalTo(nik_lecturer+"_"+"0");

        dbListNote.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayListClassData.clear();
                // System.out.println(dataSnapshot.getChildren());
                for (DataSnapshot listNote : dataSnapshot.getChildren()){
                    if (listNote.exists()){
                        classData ClassData = listNote.getValue(classData.class);
                        arrayListClassData.add(ClassData);
                    }
                }
                listnotedapter = new ListNotedapter(getActivity(), arrayListClassData);
                recyclerView.setAdapter(listnotedapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
//        btn_fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), AddNoteLecturer.class);
//                startActivity(i);
//            }
//        });
        return view;
    }
}
