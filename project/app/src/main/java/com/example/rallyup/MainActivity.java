package com.example.rallyup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rallyup.progressBar.ProgressBarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button qrScannerButton = findViewById(R.id.QRScannerButton);
        Button qrGeneratorButton = findViewById(R.id.QRGeneratorButton);
        Button attendeeListButton = findViewById(R.id.AttListButton);
        Button progressButton = findViewById(R.id.ProgressBarButton);
        Button uiLayoutButton = findViewById(R.id.UILayoutButton);

        qrScannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(com.example.rallyup.MainActivity.this,
                                com.example.rallyup.qrScanner.MainActivity.class);
                startActivity(intent);
            }
        });
        qrGeneratorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(com.example.rallyup.MainActivity.this,
                                com.example.rallyup.qrGeneration.MainActivity.class);
                startActivity(intent);
            }
        });
        attendeeListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(com.example.rallyup.MainActivity.this,
                                com.example.rallyup.attendeeList.MainActivity.class);
                startActivity(intent);
            }
        });
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
                                com.example.rallyup.uiReference.MainActivity.class);
                startActivity(intent);
            }
        });
    }
}