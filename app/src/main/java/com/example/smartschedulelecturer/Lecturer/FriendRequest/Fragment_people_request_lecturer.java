package com.example.smartschedulelecturer.Lecturer.FriendRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartschedulelecturer.Lecturer.FriendRequest.Adapter.ListPeopleRequestAdapter;
import com.example.smartschedulelecturer.Model.detailClass;
import com.example.smartschedulelecturer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_people_request_lecturer extends Fragment {
    private FloatingActionButton btn_fab;
     ListPeopleRequestAdapter list_people_request;
    RecyclerView recyclerView;
    ArrayList<detailClass> listDetailClass;
    private DatabaseReference Db_list_class;
    private static String firstname_lecturer,
            lastname_lecturer,
            email_lecturer,
            Faculty_lecturer,
            Section_lecturer,
            nik_lecturer;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people_request_lecturer, container, false);
        firstname_lecturer = getActivity().getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer = getActivity().getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer = getActivity().getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer = getActivity().getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer = getActivity().getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer = getActivity().getIntent().getExtras().getString("nik_lecturer");

        recyclerView = view.findViewById(R.id.listPeopleRequest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //  System.out.println(where);
        listDetailClass = new ArrayList<detailClass>();

         Query dbListPeopleRequest = FirebaseDatabase.getInstance().getReference("detail_class")
                 .orderByChild("nik_stat")
                 .equalTo(nik_lecturer+"_"+"0");
        dbListPeopleRequest.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listDetailClass.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    if (dataSnapshot.exists()){
                    detailClass DetailClass = dataSnapshot1.getValue(detailClass.class);

                        listDetailClass.add(DetailClass);
                    }
                }
                list_people_request = new ListPeopleRequestAdapter(getActivity(), listDetailClass);
                recyclerView.setAdapter(list_people_request);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), ""+ databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       
        return view;
    }
}
