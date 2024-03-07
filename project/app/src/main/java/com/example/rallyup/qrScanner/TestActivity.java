package com.example.rallyup.qrScanner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rallyup.R;
/**
 * This class contains a test activity for QR scanning after it scans
 * @author Reimark Ronabio
 */
public class TestActivity extends AppCompatActivity {

    TextView scannedText;

    /**
     * Initializes the test activity when it is launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
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
