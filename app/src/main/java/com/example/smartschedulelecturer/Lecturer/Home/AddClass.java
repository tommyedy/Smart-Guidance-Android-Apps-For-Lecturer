package com.example.smartschedulelecturer.Lecturer.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddClass extends AppCompatActivity {
    private Button btnaddClass;

    private ImageView imgCancel;

    private EditText
            Et_codeActivation,
            Et_codeClass;

    private TextInputLayout
            codeClass_error;

    private static boolean isCodeClassValid;

    private DatabaseReference mClass;

    private static String
            firstname_lecturer,
            lastname_lecturer,
            email_lecturer,
            Faculty_lecturer,
            Section_lecturer,
            nik_lecturer,
            codeClass,
            codeActivation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_lecturer);

        firstname_lecturer = getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer  = getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer     = getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer   = getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer   = getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer       = getIntent().getExtras().getString("nik_lecturer");

        //Get Value XML
        Et_codeActivation  = findViewById(R.id.codeActivation_id);
        Et_codeClass       = findViewById(R.id.codeClass_id);
        btnaddClass        = findViewById(R.id.create_class_id);

        codeClass_error    = findViewById(R.id.codeClassError_id);
        imgCancel          = findViewById(R.id.imgCancel_id);

        Et_codeActivation.setEnabled(false);
        Et_codeActivation.setText(CodeActivation(nik_lecturer, codeClass));

//        if (codeClass.isEmpty() || codeClass.equals(null)){
//            codeClass = "01";
//            Et_codeClass.setText("");
//
//        }else{
//            codeClass          = Et_codeClass.getText().toString().trim();
//            Et_codeClass.setText(codeClass);
//            Et_codeActivation.setText(CodeActivation(nik_lecturer, codeClass));
//        }
            btnaddClass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (SetValidation()) {
                        codeClass          = Et_codeClass.getText().toString().trim();
                        mClass = FirebaseDatabase.getInstance().getReference("class");
                        String status ="0";
                        codeActivation     = Et_codeActivation.getText().toString().trim();
                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                        classData kelas = new classData(
                                codeClass,
                                codeActivation,
                                status,
                                Faculty_lecturer,
                                Section_lecturer,
                                nik_lecturer,
                                nik_lecturer+"_"+status,
                                timestamp.toString()
                        );
                        mClass.push().setValue(kelas).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Class Successfully Added", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                    }
                }
            });

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private String CodeActivation(String nik_lecturer, String codeClass){
        String subsnik_lecturer = nik_lecturer.substring(0,2);
        codeClass = "01";
        String subsclass_code = codeClass.substring(0,2);
        SimpleDateFormat formatter = new SimpleDateFormat("ss");
        Date date = new Date();
        String subsdatetime = formatter.format(date);
       // String subsdatetime = datetime.substring(0,2);


        return subsnik_lecturer.concat(subsclass_code).concat(subsdatetime);
    }

    private boolean SetValidation() {
        // Check for a valid identity number.
        // Check for a valid email address.
        if (Et_codeClass.getText().toString().isEmpty()) {
            codeClass_error.setError(getResources().getString(R.string.emailLecturerError));
            isCodeClassValid = false;

        } else  {
            isCodeClassValid = true;
            codeClass_error.setErrorEnabled(false);
        }

        if (isCodeClassValid) {
            //Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }

    }

}
