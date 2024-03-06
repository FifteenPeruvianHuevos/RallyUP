package com.example.rallyup.qrScanner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rallyup.R;

public class TestActivity extends AppCompatActivity {

    TextView scannedText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_test_activity);

        scannedText = findViewById(R.id.qr_test_text);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("scannedText");
            //The key argument here must match that used in the other activity
            scannedText.setText(value);
        }
    }
}
