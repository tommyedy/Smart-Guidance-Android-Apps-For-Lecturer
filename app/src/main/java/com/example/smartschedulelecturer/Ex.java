package com.example.smartschedulelecturer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Ex extends AppCompatActivity {
    TextView text1, text2, text3, text4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);

        text1=findViewById(R.id.textView8);
        text2=findViewById(R.id.textView7);
        //text3=findViewById(R.id.textView3);
        //text4=findViewById(R.id.textView4);


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
            String value;
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                value = bundle.getString("USERNAME");
            }
//            text1.setText(intent.getStringExtra("USERNAME"));
//            text2.setText(intent.getStringExtra("EMAIL"));
//            text3.setText(intent.getStringExtra("ID"));
        }
        catch (Exception ex){}

    }
}
