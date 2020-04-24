package com.example.smartschedulelecturer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.Model.faculty;
import com.example.smartschedulelecturer.Model.Section;
import com.example.smartschedulelecturer.Model.userLecturer;
import com.example.smartschedulelecturer.GlobalFunction.iFirebaseLoadDone;
import com.example.smartschedulelecturer.Model.userDetails;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignUpLecturer extends AppCompatActivity implements iFirebaseLoadDone {
    //Variabel Umum
    //Activity Atribute
    private Button BtnSignUp;

    private ImageView BtnCancel;

    private SearchableSpinner
            spinnerJurusan,
            spinnerSection;

    //Identity Variable
    private EditText
            Et_email_lecturer,
            Et_nik_lecturer,
            Et_firstname_lecturer,
            Et_lastname_lecturer,
            Et_password_lecturer;

    private DatabaseReference mUsers,
            mLecturer,
            mFaculty,
            mSection;

    private FirebaseAuth mAuth;

    private TextInputLayout
            nik_lecturer_error,
            firstName_lecturer_error,
            lastName_lecturer_error,
            email_lecturer_error,
            password_lecturer_error,
            faculty_lecturer_error,
            section_lecturer_error;
    private static boolean
            isNikValid,
            isFirstnameValid,
            isLastnameValid,
            isEmailValid,
            isPasswordValid,
            isFacultyValid,
            isSectionValid;

    private static String
            encrypted_password,
            nik_lecturer,
            firstName_lecturer,
            lastName_lecturer,
            email_lecturer,
            password_lecturer,
            faculty_lecturer,
            section_lecturer;

    private ProgressBar RegistrationProcess;
  //  private ProgressDialog RegistrationProcess;
    iFirebaseLoadDone IFirebaseLoadDone;

    List<faculty> facultiss;

    List<Section> sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_lecturer);

        //Get Value From XML
        Et_nik_lecturer       = findViewById(R.id.nikLecturer_id);
        Et_firstname_lecturer = findViewById(R.id.firstnameLecturer_id);
        Et_lastname_lecturer  = findViewById(R.id.lastnameLecturer_id);
        Et_email_lecturer     = findViewById(R.id.emailLecturer_id);
        Et_password_lecturer  = findViewById(R.id.passwordLecturer_id);
        spinnerJurusan        = findViewById(R.id.facultyLecturer_id);
        spinnerSection        = findViewById(R.id.sectionLecturer_id);
        RegistrationProcess   = findViewById(R.id.progressBar);

        spinnerJurusan.setPositiveButton("Choose faculty");

        //Button
        BtnCancel             = findViewById(R.id.btnCancel);
        BtnSignUp             = findViewById(R.id.btnSignUpWithEmailLecturer_id);
        IFirebaseLoadDone     = (iFirebaseLoadDone) this;

        spinnerSection.setEnabled(false);


        mAuth     = FirebaseAuth.getInstance();
        mFaculty  = FirebaseDatabase.getInstance().getReference("faculty");
        mFaculty.addListenerForSingleValueEvent(new ValueEventListener() {
            List<faculty> facultyList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Default = "Choose faculty";

                for (DataSnapshot Faculty:dataSnapshot.getChildren()){
                    facultyList.add(Faculty.getValue(faculty.class));
                }
                IFirebaseLoadDone.onFirebaseLoadSuccess(facultyList);
                spinnerSection.setEnabled(true);

                mSection = FirebaseDatabase.getInstance().getReference("section");
                //mSection.child("faculty_code").equalTo("001");
                mSection.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Section> sectionList = new ArrayList<>();

                        for (DataSnapshot Section:dataSnapshot.getChildren()){
                            sectionList.add(Section.getValue(Section.class));
                        }
                        IFirebaseLoadDone.onFirebaseLoadSuccess1(sectionList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        IFirebaseLoadDone.onFirebaseLoadFailed1(databaseError.getMessage());
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                IFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });



        //Error Value From XML
        nik_lecturer_error       = findViewById(R.id.nikErrorLecturer_id);
        firstName_lecturer_error = findViewById(R.id.firstnameErrorLecturer_id);
        lastName_lecturer_error  = findViewById(R.id.lastnameLecturer_error);
        email_lecturer_error     = findViewById(R.id.emailErrorLecturer_id);
        faculty_lecturer_error   = findViewById(R.id.facultyErrorLecturer_id);
        section_lecturer_error   = findViewById(R.id.sectionErrorLecturer_id);
        password_lecturer_error  = findViewById(R.id.passwordErrorLecturer_id);


        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // mAuth.signOut();
                //Validasi
                // encrypted_password = passwordEncrypt.encrypt("asd");
                if (SetValidation()) {
                    email_lecturer     = Et_email_lecturer.getText().toString().trim();
                    System.out.println(email_lecturer);

                    //Conversion Data Types
                    nik_lecturer       = Et_nik_lecturer.getText().toString().trim();
                    firstName_lecturer = Et_firstname_lecturer.getText().toString().trim();
                    lastName_lecturer  = Et_lastname_lecturer.getText().toString().trim();
                    email_lecturer     = Et_email_lecturer.getText().toString().trim();
                    password_lecturer  = Et_password_lecturer.getText().toString().trim();
                    System.out.println(password_lecturer);
                    faculty_lecturer   = spinnerJurusan.getSelectedItem().toString();
                    section_lecturer   = spinnerSection.getSelectedItem().toString();
                    mLecturer          = FirebaseDatabase.getInstance().getReference("lecturer");
                    mUsers             = FirebaseDatabase.getInstance().getReference("users_detail");
                    mAuth.createUserWithEmailAndPassword(email_lecturer, password_lecturer).addOnCompleteListener(task-> {
                    //    RegistrationProcess.setVisibility(View.VISIBLE);

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        Date date = timestamp;
                        if (task.isSuccessful()) {
                            String users_id = mAuth.getCurrentUser().getUid();
                            userLecturer lecturer = new userLecturer(
                                    nik_lecturer,
                                    firstName_lecturer,
                                    lastName_lecturer,
                                    email_lecturer,
                                    faculty_lecturer,
                                    section_lecturer);
                            userDetails userDetails = new userDetails(
                                    users_id,
                                    nik_lecturer,
                                    date.toString()
                            );
                            mLecturer.child(nik_lecturer).setValue(lecturer);
                            mUsers.push().setValue(userDetails);
                            Toast.makeText(getApplicationContext(), "Data Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(SignUpLecturer.this, MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(), ""+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });

                }
            }
        });
    }

    private void getSection(String fakultas){

    }

    private void getFaculty(){

    }



    private boolean SetValidation() {
        // Check for a valid identity number.
        if (Et_nik_lecturer.getText().toString().isEmpty()) {
            nik_lecturer_error.setError(getResources().getString(R.string.nikLecturerError));
            isNikValid = false;
        } else if (Et_nik_lecturer.getText().toString().length()<7){
            nik_lecturer_error.setError(getResources().getString(R.string.nikLecturerError));
            isNikValid = false;
        } else{
            isNikValid = true;
            nik_lecturer_error.setErrorEnabled(false);
        }

        // Check for a valid first name.
        if (Et_firstname_lecturer.getText().toString().isEmpty()) {
            firstName_lecturer_error.setError(getResources().getString(R.string.firstnameLecturerError));
            isFirstnameValid = false;
        } else  {
            isFirstnameValid = true;
            firstName_lecturer_error.setErrorEnabled(false);
        }

        // Check for a valid last name.
        if (Et_lastname_lecturer.getText().toString().isEmpty()) {
            lastName_lecturer_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isLastnameValid = false;
        } else  {
            isLastnameValid = true;
            lastName_lecturer_error.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (Et_email_lecturer.getText().toString().isEmpty()) {
            email_lecturer_error.setError(getResources().getString(R.string.emailLecturerError));
            isEmailValid = false;
        } else if ((!Patterns.EMAIL_ADDRESS.matcher(Et_email_lecturer.getText().toString()).matches()) && (!Et_email_lecturer.getText().toString().contains("@unai.edu"))) {
            email_lecturer_error.setError(getResources().getString(R.string.emailLecturerInvalid));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            email_lecturer_error.setErrorEnabled(false);
        }

        // Check for a valid faculty name.
        if (spinnerJurusan.getSelectedItem().toString().isEmpty()) {
            faculty_lecturer_error.setError(getResources().getString(R.string.facultyLecturerError));
            isFacultyValid = false;
        } else  {
            isFacultyValid = true;
            faculty_lecturer_error.setErrorEnabled(false);
        }

        // Check for a valid section name.
        if (spinnerSection.getSelectedItem().toString().isEmpty()) {
            section_lecturer_error.setError(getResources().getString(R.string.sectionLecturerError));
            isSectionValid = false;
        } else  {
            isSectionValid = true;
            section_lecturer_error.setErrorEnabled(false);
        }


        // Check for a valid password.
        if (Et_password_lecturer.getText().toString().isEmpty()) {
            password_lecturer_error.setError(getResources().getString(R.string.passwordLecturerError));
            isPasswordValid = false;
        } else if (Et_password_lecturer.getText().length() < 6) {
            password_lecturer_error.setError("Password Less than 6");
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            password_lecturer_error.setErrorEnabled(false);
        }

        if (isNikValid && isEmailValid && isFirstnameValid && isFacultyValid && isSectionValid && isLastnameValid && isPasswordValid) {
            //Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }

    }

    @Override
    public void onFirebaseLoadSuccess(List<faculty> faculty) {
        facultiss = faculty;

        List<String> name_list = new ArrayList<>();
        for (com.example.smartschedulelecturer.Model.faculty facultisss:faculty)
                name_list.add(facultisss.getFaculty_name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name_list);
        int spinnerposition = adapter.getPosition("Choose faculty");
        //spinnerJurusan.setSelection(spinnerposition);
        spinnerJurusan.setAdapter(adapter);

    }

    @Override
    public void onFirebaseLoadSuccess1(List<Section> section) {

        sec = section;

        List<String> name_list = new ArrayList<>();
        for (Section secc:section)
            name_list.add(secc.getSection_name());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name_list);
        int spinnerposition = adapter.getPosition("Choose faculty");
       // spinnerJurusan.setSelection(spinnerposition);
        spinnerSection.setAdapter(adapter);

    }

    @Override
    public void onFirebaseLoadFailed(String message) {

    }

    @Override
    public void onFirebaseLoadFailed1(String message) {

    }

    @Override
    public void onFirebaseLoadSuccess2(List<classData> ClassData) {

    }

    @Override
    public void onFirebaseLoadSuccess3(List<Section> section) {

    }
}
