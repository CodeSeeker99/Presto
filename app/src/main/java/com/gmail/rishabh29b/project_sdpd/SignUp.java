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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button regbutton;
    private EditText editTextEmail;
    private EditText editTextPassword;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        regbutton = (Button) findViewById(R.id.buttonSignUp);
        editTextEmail = (EditText) findViewById(R.id.editTextRegUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        regbutton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == regbutton){
            registerUser();
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            //stopping execution
            return;
        }

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();
            //stopping execution
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    finish();
                    intent = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(SignUp.this, "Registration Unsuccessful", Toast.LENGTH_LONG).show();
                }
                progressDialog.hide();
            }
        });
    }
}
