package com.example.smartschedulelecturer.Lecturer.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.smartschedulelecturer.Lecturer.AccountSettings.Fragment_account_settings_lecturer;
import com.example.smartschedulelecturer.Lecturer.Schedule.Fragment_schedule_lecturer;
import com.example.smartschedulelecturer.Lecturer.Note.Fragment_note_lecturer;
import com.example.smartschedulelecturer.Lecturer.FriendRequest.Fragment_people_request_lecturer;
import com.example.smartschedulelecturer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_lecturer);


        BottomNavigationView bottomNav = findViewById(R.id.BottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.frame_container, new Fragment_home_lecturer());
        tx.commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;

            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    selectedFragment = new Fragment_home_lecturer();
                break;
                case R.id.navigation_alarm:
                    selectedFragment = new Fragment_schedule_lecturer();
                break;
                case R.id.navigation_note:
                    selectedFragment = new Fragment_note_lecturer();
                break;
                case R.id.navigation_account:
                    selectedFragment = new Fragment_account_settings_lecturer();
                break;
                case R.id.navigation_student_request:
                    selectedFragment = new Fragment_people_request_lecturer();
                break;
//                default:
//                    selectedFragment = new Fragment_home_lecturer();
//                break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectedFragment).commit();
            return true;
        }
    };
}
