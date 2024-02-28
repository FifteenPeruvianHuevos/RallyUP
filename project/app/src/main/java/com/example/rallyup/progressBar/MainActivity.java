package com.example.rallyup.progressBar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rallyup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_progress_bar);

        Button toProgressBar = findViewById(R.id.buttonToProgressBarXML);

        toProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProgressBarIntent =
                        new Intent(com.example.rallyup.progressBar.MainActivity.this, ProgressBarActivity.class);
                startActivity(toProgressBarIntent);
            }
        });

    }
}