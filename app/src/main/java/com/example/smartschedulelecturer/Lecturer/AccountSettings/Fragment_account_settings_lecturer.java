package com.example.smartschedulelecturer.Lecturer.AccountSettings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smartschedulelecturer.Lecturer.AccountSettings.ChangePasswordLecturer;
import com.example.smartschedulelecturer.MainActivity;
import com.example.smartschedulelecturer.Model.userLecturer;
import com.example.smartschedulelecturer.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fragment_account_settings_lecturer extends Fragment {
    private static String
            firstname_lecturer,
            lastname_lecturer,
            email_lecturer,
            Faculty_lecturer,
            Section_lecturer,
            nik_lecturer;

    private static String
            firstname_lecturer_change,
            lastname_lecturer_change,
            email_lecturer_change,
            Faculty_lecturer_change,
            Section_lecturer_change,
            nik_lecturer_change;

    private Button
            btn_logout,
            btn_edit,
            btn_password;

    private EditText
            Firstname_lectuer,
            Lastname_lecturer,
            Email_lecturer,
            faculty_lecturer,
            section_lecturer,
            Nik_lecturer;

    private FirebaseAuth mAuth;

    private DatabaseReference musers;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_settings_lecturer, container, false);
        btn_logout = view.findViewById(R.id.logout);
        btn_edit = view.findViewById(R.id.edit_profile);
      //  btn_password = view.findViewById(R.id.changePasswordLecturer_id);

        firstname_lecturer = getActivity().getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer = getActivity().getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer = getActivity().getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer = getActivity().getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer = getActivity().getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer = getActivity().getIntent().getExtras().getString("nik_lecturer");

        Firstname_lectuer = view.findViewById(R.id.firstname);
        Lastname_lecturer = view.findViewById(R.id.lastname);
        Email_lecturer = view.findViewById(R.id.email);
        faculty_lecturer = view.findViewById(R.id.faculty);
        section_lecturer = view.findViewById(R.id.section);
        Nik_lecturer = view.findViewById(R.id.nikLecturer_id);

        Firstname_lectuer.setText(firstname_lecturer);
        Lastname_lecturer.setText(lastname_lecturer);
        Email_lecturer.setText(email_lecturer);
        Email_lecturer.setEnabled(false);
        faculty_lecturer.setText(Faculty_lecturer);
        faculty_lecturer.setEnabled(false);
        section_lecturer.setText(Section_lecturer);
        section_lecturer.setEnabled(false);
        Nik_lecturer.setText(nik_lecturer);
        Nik_lecturer.setEnabled(false);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musers = FirebaseDatabase.getInstance().getReference("lecturer");
                        firstname_lecturer_change = Firstname_lectuer.getText().toString().trim();
                        lastname_lecturer_change = Lastname_lecturer.getText().toString().trim();
                        email_lecturer_change = Email_lecturer.getText().toString().trim();
                        Faculty_lecturer_change = faculty_lecturer.getText().toString().trim();
                        Section_lecturer_change = section_lecturer.getText().toString().trim();
                       nik_lecturer_change = Nik_lecturer.getText().toString().trim();
                userLecturer UserLecturer = new userLecturer(
                        nik_lecturer_change,
                        firstname_lecturer_change,
                        lastname_lecturer_change,
                        email_lecturer_change,
                        Faculty_lecturer_change,
                        Section_lecturer_change
                );
                       musers.child(nik_lecturer)
                               .setValue(UserLecturer)
                               .addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {
                                       Toast.makeText(getActivity(), "Data Successfully Edited", Toast.LENGTH_SHORT).show();
                                   }
                               }).addOnFailureListener(getActivity(), new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {
                               Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                           }
                       });


               // mAuth.getInstance().signOut();
               // Intent i = new Intent(getActivity(), MainActivity.class);
               // startActivity(i);
            }
        });

//        btn_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), ChangePasswordLecturer.class);
//                startActivity(i);
//            }
//        });


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                getActivity().getIntent().removeExtra("Firstname_lecturer");
                getActivity().getIntent().removeExtra("Lastname_lecturer");
                getActivity().getIntent().removeExtra("email_lecturer");
                getActivity().getIntent().removeExtra("Faculty_lecturer");
                getActivity().getIntent().removeExtra("Section_lecturer");
                getActivity().getIntent().removeExtra("nik_lecturer");
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        return view;

    }

}
