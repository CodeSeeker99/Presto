package com.gmail.rishabh29b.project_sdpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomePage extends AppCompatActivity implements View.OnClickListener{

    Button nextButton;
    private FirebaseAuth firebaseAuth;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        nextButton = findViewById(R.id.button);
        nextButton.setOnClickListener(this);

        //FirebaseUser user = firebaseAuth.getCurrentUser();
    }


    @Override
    public void onClick(View v) {
        firebaseAuth = FirebaseAuth.getInstance();
        if(v.getId() == R.id.button) {
            if (firebaseAuth.getCurrentUser() != null){
                intent = new Intent(this, MainPage.class);
                startActivity(intent);
            }
            else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }
}
