package com.example.smartschedulelecturer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ExActivity extends AppCompatActivity {
    TextView text1, text2, text3, text4;

    Button btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

        text1=findViewById(R.id.textView7);
        text2=findViewById(R.id.textView8);
       // text3=findViewById(R.id.textView3);
       // text4=findViewById(R.id.textView4);


//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
        //GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Ex.this);
//        if (acct != null) {
//            String personName = acct.getDisplayName();
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personEmail = acct.getEmail();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//
//            text1.setText(personName);
//            text2.setText(personEmail);
//            text3.setText(personId);
//        }

        try{
            String value = getIntent().getExtras().getString("USERNAME");
            String value1 =  getIntent().getExtras().getString("EMAIL");
            text1.setText(value);
            text2.setText(value1);
//            String value;
//            Bundle bundle = getIntent().getExtras();
//            String subName = getIntent().getStringExtra("USERNAME");
//            text1.setText();
        }
        catch (Exception ex) {
            String asdf = ex.getMessage().trim();
        }
        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }
    public void signOut(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
