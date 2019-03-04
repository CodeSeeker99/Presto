package com.gmail.rishabh29b.project_sdpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView forgotPassText;
    Button signUpButton;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        forgotPassText = findViewById(R.id.textViewForgotPass);
        signUpButton = findViewById(R.id.buttonSignUp);
        loginButton = findViewById(R.id.buttonLogin);

        forgotPassText.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.textViewForgotPass:
                intent = new Intent(this, ForgotPassword.class);
                startActivity(intent);
                break;
            case R.id.buttonSignUp:
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;
            case R.id.buttonLogin:
                intent = new Intent(this, MainPage.class);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
