package com.example.smartschedulelecturer.Lecturer.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Lecturer.Home.Adapter.ListClassAdapter;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Fragment_home_lecturer extends Fragment {
    private Button btn_addClass;//, btn_list_class;
    private TextView tv_nama_user;
    private FirebaseAuth mAuth;
    private static String
            firstname_lecturer,
            lastname_lecturer,
            email_lecturer,
            Faculty_lecturer,
            Section_lecturer,
            nik_lecturer;
   // List<classData> ListClassAdapter;
    private ListClassAdapter list_class_adapter;
    private RecyclerView recyclerView;
    private ArrayList<classData> list_class_;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_lecturer, container, false);
        btn_addClass = view.findViewById(R.id.create_class);
        tv_nama_user = view.findViewById(R.id.nama_user);

        firstname_lecturer = getActivity().getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer  = getActivity().getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer     = getActivity().getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer   = getActivity().getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer   = getActivity().getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer       = getActivity().getIntent().getExtras().getString("nik_lecturer");

        String full_name  = firstname_lecturer+ " "+lastname_lecturer;
        tv_nama_user.setText(full_name);

        recyclerView = view.findViewById(R.id.ListClass);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list_class_ = new ArrayList<classData>();

        Query dbListClass = FirebaseDatabase.getInstance().getReference("class")
                .orderByChild("nik_stat")
                .equalTo(nik_lecturer+"_"+"0");

        dbListClass.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    list_class_.clear();
                    for (DataSnapshot list_class : dataSnapshot.getChildren()) {
                        if (dataSnapshot.exists()){
                        classData kelas = list_class.getValue(classData.class);
                        //  if (kelas.fac_sec_stat.equals(where)) {
                            list_class_.add(kelas);
                        //list.add(kelas);
                         }
                    }
                    list_class_adapter = new ListClassAdapter(getActivity(), list_class_);
                    recyclerView.setAdapter(list_class_adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), ""+ databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //Db_list_class.addListenerForSingleValueEvent();
//        ListClassAdapter List_class = new ListClassAdapter();
//
//        ListClassAdapter.add(new classData("BX01",
//                "",
//                "",
//                "TEknik",
//                "SI",
//                ""));
//
//        recyclerView.setAdapter(List_class);


        btn_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddClass.class);
                i.putExtra("Firstname_lecturer", firstname_lecturer);
                i.putExtra("Lastname_lecturer", lastname_lecturer);
                i.putExtra("email_lecturer", email_lecturer);
                i.putExtra("Faculty_lecturer", Faculty_lecturer);
                i.putExtra("Section_lecturer", Section_lecturer);
                i.putExtra("nik_lecturer", nik_lecturer);
                startActivity(i);
            }
        });

//        btn_list_class = view.findViewById(R.id.list_kelas);
//        btn_list_class.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signOut();
//            }
//        });
        return view;
    }
}
