package com.example.smartschedulelecturer.Lecturer.Schedule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Build;
import android.os.Bundle;
//import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.smartschedulelecturer.GlobalFunction.AlertReceiver;
import com.example.smartschedulelecturer.GlobalFunction.TimePickerFragment;
import com.example.smartschedulelecturer.Model.classData;
import com.example.smartschedulelecturer.Model.schedule;
import com.example.smartschedulelecturer.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddScheduleLecturer extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    //Variabel Umum
    //Activity Atribute
    private CalendarView cal;
    private Button btnAddSchedule;
    private ImageView BtnCancel;
    private SearchableSpinner spinnerClass;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePick;

    List<classData> facultiss;

    //Identity Variable
    private EditText
            Et_class_code,
            Et_DatePicker,
            Et_TimePicker,
            Et_Place,
            Et_title;

    private DatabaseReference mClass, mSchedule;

    private TextInputLayout
            classSpinner_error,
            datePicker_error,
            timePicker_error,
            place_error,
            title_error;

    private static boolean
            isClassSpinnerValid,
            isDatePickerValid,
            isTimePickerValid,
            isPlaceValid,
            isTitleValid;

    private static String
            classSpinner,
            datePicker,
            timePicker,
            place,
            title;

    private static String
            timeText,
            classCode,
            firstname_lecturer,
            lastname_lecturer,
            email_lecturer,
            Faculty_lecturer,
            Section_lecturer,
            nik_lecturer;

    private ProgressBar RegistrationProcess;
    //  private ProgressDialog RegistrationProcess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_lecturer);
        classCode          = getIntent().getExtras().getString("classCode");
        firstname_lecturer = getIntent().getExtras().getString("Firstname_lecturer");
        lastname_lecturer  = getIntent().getExtras().getString("Lastname_lecturer");
        email_lecturer     = getIntent().getExtras().getString("email_lecturer");
        Faculty_lecturer   = getIntent().getExtras().getString("Faculty_lecturer");
        Section_lecturer   = getIntent().getExtras().getString("Section_lecturer");
        nik_lecturer       = getIntent().getExtras().getString("nik_lecturer");



        Et_class_code    = findViewById(R.id.classScheduleLecturer_id);
        Et_class_code.setEnabled(false);
        Et_class_code.setText(classCode);

        Et_DatePicker   = findViewById(R.id.dateLecturer_id);
        Et_TimePicker   = findViewById(R.id.timeLecturer_id);
        Et_title        = findViewById(R.id.titleLecturer_id);
        Et_Place        = findViewById(R.id.placeLecturer_id);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Et_DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();

            }
        });

        Et_TimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showTimeDialog();
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });
        btnAddSchedule = findViewById(R.id.btnAddSchedule_id);

        //Error Value From XML
    //    classSpinner_error       = findViewById(R.id.classSpinnerErrorLecturer_id);
        datePicker_error         = findViewById(R.id.dateErrorLecturer_id);
        timePicker_error         = findViewById(R.id.timeErrorLecturer_id);
        place_error              = findViewById(R.id.placeErrorLecturer_id);
        title_error              = findViewById(R.id.timeErrorLecturer_id);


        btnAddSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                classSpinner = Et_class_code.getText().toString().trim();
                datePicker   = Et_DatePicker.getText().toString().trim();
                timePicker   = Et_TimePicker.getText().toString().trim();
                place        = Et_Place.getText().toString().trim();
                title        = Et_title.getText().toString().trim();
                if(SetValidation()){
                    mSchedule  = FirebaseDatabase.getInstance().getReference("schedule");
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    Date date = timestamp;
                    schedule Schedule = new schedule(
                        classSpinner,
                        datePicker,
                        place,
                        timePicker,
                        title,
                        nik_lecturer,
                        nik_lecturer+"_"+"0"+"_"+classSpinner,
                        date.toString()
                    );
                    mSchedule.push().setValue(Schedule);
                    Toast.makeText(getApplicationContext(), "Data Successfully Added", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

        BtnCancel = findViewById(R.id.btnCancel);
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  System.out.println(classSpinner);
              //  classSpinner = Et_class_code.getText().toString().trim()
                datePicker   = Et_DatePicker.getText().toString().trim();
                timePicker   = Et_TimePicker.getText().toString().trim();
                place        = Et_Place.getText().toString().trim();
                title        = Et_title.getText().toString().trim();

                if (validation()){
//
                    //Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddScheduleLecturer.this);

                    // Set a title for alert dialog
                    builder.setTitle("");

                    // Ask the final question
                    builder.setMessage("Save Your Schedule?");

                    // Set the alert dialog yes button click listener
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when user clicked the Yes button
                            // Set the TextView visibility GONE
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
                }else {
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void save(){
        // Build an AlertDialog


    }

    private boolean SetValidation() {

         // Check for a valid last name.
        if (Et_DatePicker.getText().toString().isEmpty()) {
            datePicker_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isDatePickerValid = false;
        } else  {
            isDatePickerValid = true;
            datePicker_error.setErrorEnabled(false);
        }

       // Check for a valid password.
        if (Et_TimePicker.getText().toString().isEmpty()) {
            timePicker_error.setError(getResources().getString(R.string.passwordLecturerError));
            isTimePickerValid = false;
        } else  {
            isTimePickerValid = true;
            timePicker_error.setErrorEnabled(false);
        }

        if (Et_Place.getText().toString().isEmpty()) {
            place_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isPlaceValid = false;
        } else  {
            isPlaceValid = true;
            place_error.setErrorEnabled(false);
        }

        if (Et_title.getText().toString().isEmpty()) {
            title_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isTitleValid = false;
        } else  {
            isTitleValid = true;
            title_error.setErrorEnabled(false);
        }


        if (isDatePickerValid && isTimePickerValid && isPlaceValid && isTitleValid) {
            //Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }

    }


    private boolean validation() {

        // Check for a valid last name.
        if (!Et_DatePicker.getText().toString().isEmpty()) {
        //    datePicker_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isDatePickerValid = true;
        } /*else  {
            isDatePickerValid = true;
            datePicker_error.setErrorEnabled(false);
        }*/

        // Check for a valid password.
        if (!Et_TimePicker.getText().toString().isEmpty()) {
      //      timePicker_error.setError(getResources().getString(R.string.passwordLecturerError));
            isTimePickerValid = true;
        } /*else  {
            isTimePickerValid = true;
            timePicker_error.setErrorEnabled(false);
        }*/

        if (!Et_Place.getText().toString().isEmpty()) {
     //       place_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isPlaceValid = true;
        } /*else  {
            isPlaceValid = true;
            place_error.setErrorEnabled(false);
        }*/

        if (!Et_title.getText().toString().isEmpty()) {
       //     title_error.setError(getResources().getString(R.string.lastnameLecturerError));
            isTitleValid = true;
        } /*else  {
            isTitleValid = true;
            title_error.setErrorEnabled(false);
        }*/


        if (isDatePickerValid && isTimePickerValid && isPlaceValid && isTitleValid) {
            //Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }

    }


//    private void showTimeDialog() {
//
//        /**
//         * Calendar untuk mendapatkan waktu saat ini
//         */
//        Calendar calendar = Calendar.getInstance();
//
//        /**
//         * Initialize TimePicker Dialog
//         */
//        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Et_TimePicker.setText("" + hourOfDay + ":" + minute);
//            }
//        },
//
//                /**
//                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
//                 */
//                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),
//
//                /**
//                 * Cek apakah format waktu menggunakan 24-hour format
//                 */
//                DateFormat.is24HourFormat(this));
//
//        timePickerDialog.show();
//    }

    private void showDateDialog(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePick = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Et_DatePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, year, month, day);
        datePick.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, i);
        c.set(Calendar.MINUTE, i1);
        c.set(Calendar.SECOND,0);

        updateTime(c);
        startAlarm(c);
    }

    private void updateTime(Calendar c) {
        //timeText = "Alarm set for: ";

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//
//            timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
//
//
//        }
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Et_TimePicker.setText(formatter.format(c.getTime()));



    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(AddScheduleLecturer.this, AlertReceiver.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this, 1, i, 0);
        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(AddScheduleLecturer.this, AlertReceiver.class);
        PendingIntent pendingIntent =  PendingIntent.getBroadcast(this, 1, i, 0);

        alarmManager.cancel(pendingIntent);

    }
}
