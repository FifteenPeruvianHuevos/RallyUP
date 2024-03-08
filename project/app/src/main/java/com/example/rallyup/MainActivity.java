package com.example.rallyup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rallyup.progressBar.ProgressBarActivity;
import com.example.rallyup.uiReference.attendees.QRBaseActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button progressButton = findViewById(R.id.ProgressBarButton);
        Button uiLayoutButton = findViewById(R.id.UILayoutButton);

        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(com.example.rallyup.MainActivity.this,
                                ProgressBarActivity.class);
                startActivity(intent);
            }
        });
        uiLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(com.example.rallyup.MainActivity.this,
                                com.example.rallyup.uiReference.splashScreen.class);
                startActivity(intent);
            }
        });
    }
}