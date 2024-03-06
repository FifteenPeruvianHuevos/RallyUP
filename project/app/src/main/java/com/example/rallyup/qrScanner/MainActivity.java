package com.example.rallyup.qrScanner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rallyup.R;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {


    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    Intent testAct = new Intent(MainActivity.this, TestActivity.class);
                    testAct.putExtra("scannedText", result.getContents() );
                    startActivity(testAct);
                }
            });


    // Launch
//    public void onButtonClick(View view) {
//        barcodeLauncher.launch(new ScanOptions());
//    }
    Button scanButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qrscanner);

        scanButton = findViewById(R.id.qr_button);

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanOptions options = new ScanOptions();
                options.setOrientationLocked(false);
                options.setBeepEnabled(false);
                options.setCaptureActivity(ScannerActivity.class);
                barcodeLauncher.launch(options);
            }
        });
    }
}