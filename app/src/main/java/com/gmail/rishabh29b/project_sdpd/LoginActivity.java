package com.gmail.rishabh29b.project_sdpd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView forgotPassText;
    Button signUpButton;
    Button loginButton;
    EditText enteremail;
    EditText enterpasswod;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        //Intent intent;
        if (firebaseAuth.getCurrentUser() != null){
            intent = new Intent(this, MainPage.class);
            startActivity(intent);
        }

        forgotPassText = findViewById(R.id.textViewForgotPass);
        signUpButton = findViewById(R.id.buttonSignUp);
        loginButton = findViewById(R.id.buttonLogin);
        enteremail = findViewById(R.id.editTextUsername);
        enterpasswod = findViewById(R.id.editTextPassword);

        progressDialog = new ProgressDialog(this);

        forgotPassText.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

        private void userLogin() {
            String email = enteremail.getText().toString().trim();
            String password = enterpasswod.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                //email is empty
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                //stopping execution
                return;
            }

            if (TextUtils.isEmpty(password)) {
                //password is empty
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                //stopping execution
                return;
            }

            progressDialog.setMessage("Registering User...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        finish();
    //                    Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_LONG).show();
                        intent = new Intent(getApplicationContext(), MainPage.class);
                        startActivity(intent);
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Check Username or Password", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.buttonSignUp:
                intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;
            case R.id.buttonLogin:
                //intent = new Intent(this, MainPage.class);
                //startActivity(intent);
                userLogin();
                break;
            default:
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
