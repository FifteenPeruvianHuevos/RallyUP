package com.example.rallyup.uiReference.attendees;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.rallyup.R;
import com.example.rallyup.qrScanner.QRBaseActivity;
import com.example.rallyup.qrScanner.ScannerActivity;
import com.example.rallyup.qrScanner.TestActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class AttendeeMyEventsActivity extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                // If nothing was scanned
                if(result.getContents() == null) {
                    Toast.makeText(AttendeeMyEventsActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    // function calls for when an id has been scanned go here
                    Toast.makeText(AttendeeMyEventsActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    Intent eventAct = new Intent(AttendeeMyEventsActivity.this, AttendeeEventDetails.class); // temporary activity, replace with event activity
                    eventAct.putExtra("scannedText", result.getContents() ); // sending string
                    startActivity(eventAct); // replace
                }
            });
    ImageButton attMyEventsBackBtn;
    FloatingActionButton QRCodeScannerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_my_events);

        attMyEventsBackBtn = findViewById(R.id.browse_events_back_button);
        QRCodeScannerBtn = findViewById(R.id.QRScannerButton);

        attMyEventsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeHomepageActivity.class);
                startActivity(intent);
            }
        });

        QRCodeScannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // options for the scanner
                ScanOptions options = new ScanOptions();
                options.setOrientationLocked(false);
                options.setBeepEnabled(false);
                options.setCaptureActivity(ScannerActivity.class);
                barcodeLauncher.launch(options);
            }
        });
    }
}