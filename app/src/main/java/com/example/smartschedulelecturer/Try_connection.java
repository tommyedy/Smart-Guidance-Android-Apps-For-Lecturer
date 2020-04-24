package com.example.smartschedulelecturer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.smartschedulelecturer.GlobalFunction.Connection;

public class Try_connection extends AppCompatActivity {
    Connection cd;
    private Button BtnTryConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_connection);
        cd = new Connection(getApplicationContext());

         BtnTryConnection = findViewById(R.id.try_connection);
         BtnTryConnection.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if (cd.hasInternetConnection()){
                    Intent i = new Intent(Try_connection.this, MainActivity.class);
                    startActivity(i);
                }
             }
         });

    }
}
