package com.example.smartschedulelecturer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartschedulelecturer.Lecturer.Home.MainMenu;
import com.example.smartschedulelecturer.GlobalFunction.Connection;
import com.example.smartschedulelecturer.Model.userLecturer;
import com.example.smartschedulelecturer.Model.userDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    private Button
            btnSignInEmail;

    private EditText
            Et_email,
            Et_password;

    private TextView Signupwithemail;

    private FirebaseAuth mAuth;

    private FirebaseDatabase mLecturer;

    Connection cd;
   // private Boolean exit = false;
   // GoogleSignInClient gsc;

    private TextInputLayout
            email_lecturer_error,
            password_lecturer_error;

    private static boolean
            isEmailValid,
            isPasswordValid;

    private static String
            email,
            password;

   // private List<userDetails> listUserDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        cd = new Connection(getApplicationContext());
        if (cd.hasInternetConnection()){
            setContentView(R.layout.activity_main);
            mAuth = FirebaseAuth.getInstance();
           // mUserDetails = FirebaseDatabase.getInstance();
            //mUserDetails = DatabaseRefe
            mLecturer = FirebaseDatabase.getInstance();
            Signupwithemail= findViewById(R.id.signUpWithEmail_id);
            btnSignInEmail=  findViewById(R.id.btnlogin_id);
            Et_email = findViewById(R.id.emailLecturer_id);
            Et_password = findViewById(R.id.passwordLecturer_id);
            email = Et_email.getText().toString().trim();
            password = Et_password.getText().toString().trim();
            mAuth.signOut();

            //Error Value From XML

            email_lecturer_error     = findViewById(R.id.emailErrorLecturer_id);
            password_lecturer_error  = findViewById(R.id.passwordErrorLecturer_id);



            btnSignInEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (SetValidation()) {
                        Et_email = findViewById(R.id.emailLecturer_id);
                        Et_password = findViewById(R.id.passwordLecturer_id);
                        email = Et_email.getText().toString().trim();
                        password = Et_password.getText().toString().trim();
                       // System.out.println(email);
                       // System.out.println(password);
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()){
                                            AuthResult result = task.getResult();
                                            //Get UID
                                            String idUser = result.getUser().getUid();
                                           Query userDetail =  FirebaseDatabase.getInstance().getReference("users_detail");
                                           userDetail.orderByChild("uid")
                                                   .equalTo(idUser)
                                                   .addChildEventListener(new ChildEventListener() {
                                                       @Override
                                                       public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                          // System.out.println(dataSnapshot.getValue().toString());
                                                           System.out.println(dataSnapshot.getKey());
                                                           userDetails detail = dataSnapshot.getValue(userDetails.class);
                                                          // System.out.println(detail.getNik_lecturer());
                                                           String nik = detail.getIdentity_number();
                                                           Query Lecturer = FirebaseDatabase.getInstance().getReference("lecturer");//.child(nik);
                                                           Lecturer.orderByChild("nik")
                                                                   .equalTo(nik)
                                                           .addChildEventListener(new ChildEventListener() {
                                                               @Override
                                                               public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                                                //   System.out.println(dataSnapshot.getKey());
                                                                   userLecturer lecturer = dataSnapshot.getValue(userLecturer.class);
                                                                   String Firstname    = lecturer.getFirstname();
                                                                   String lastname     = lecturer.getLastname();
                                                                   String email        = lecturer.getEmail();
                                                                   String faculty      = lecturer.getFaculty();
                                                                   String section      = lecturer.getSection();
                                                                   String nik_lecturer = lecturer.getNik();
                                                                  // System.out.println(email);
                                                                   Intent i = new Intent(MainActivity.this, MainMenu.class);

                                                                   i.putExtra("Firstname_lecturer", Firstname);
                                                                   i.putExtra("Lastname_lecturer", lastname);
                                                                   i.putExtra("email_lecturer", email);
                                                                   i.putExtra("Faculty_lecturer", faculty);
                                                                   i.putExtra("Section_lecturer", section);
                                                                   i.putExtra("nik_lecturer", nik_lecturer);

                                                                   startActivity(i);
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
                                    }
                                }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            });

            Signupwithemail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // mUserDetails    = FirebaseDatabase.getInstance().getReference("class");
                   // mUserDetails.removeValue();
                    Intent i = new Intent(com.example.smartschedulelecturer.MainActivity.this, SignUpLecturer.class);
                    startActivity(i);
                }
            });
        }else{
            Intent i = new Intent(MainActivity.this, Try_connection.class);
            startActivity(i);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean SetValidation() {
        // Check for a valid identity number.
        // Check for a valid email address.
        if (Et_email.getText().toString().isEmpty()) {
            email_lecturer_error.setError(getResources().getString(R.string.emailLecturerError));
            isEmailValid = false;
        } else if ((!Patterns.EMAIL_ADDRESS.matcher(Et_email.getText().toString()).matches()) && (!Et_email.getText().toString().contains("@unai.edu"))) {
            email_lecturer_error.setError(getResources().getString(R.string.emailLecturerInvalid));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            email_lecturer_error.setErrorEnabled(false);
        }


        // Check for a valid password.
        if (Et_password.getText().toString().isEmpty()) {
            password_lecturer_error.setError(getResources().getString(R.string.passwordLecturerError));
            isPasswordValid = false;
        } else if (Et_password.getText().length() < 6) {
            password_lecturer_error.setError("Password Less than 6");
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
            password_lecturer_error.setErrorEnabled(false);
        }

        if (isEmailValid  && isPasswordValid) {
            //Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }

    }
}
