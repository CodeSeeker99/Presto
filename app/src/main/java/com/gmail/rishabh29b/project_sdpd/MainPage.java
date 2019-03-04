package com.gmail.rishabh29b.project_sdpd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPage extends AppCompatActivity implements View.OnClickListener {

    Button uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        uploadButton = findViewById(R.id.buttonUpload);
        uploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonUpload){
            Intent intent = new Intent(this, Genre.class);
            startActivity(intent);
        }
    }
}
