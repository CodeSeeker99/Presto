package com.gmail.rishabh29b.project_sdpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    Button uploadButton;
    Button logoutbutton;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        uploadButton = findViewById(R.id.buttonUpload);
        logoutbutton = findViewById(R.id.button2);

        uploadButton.setOnClickListener(this);
        logoutbutton.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonUpload){
            Intent intent = new Intent(this, Genre.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.button2){
            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
