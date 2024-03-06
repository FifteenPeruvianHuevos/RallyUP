package com.example.rallyup.qrGeneration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.rallyup.LocalStorageController;
import com.example.rallyup.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    EditText editInput;
    Button btGenerate;
    ImageView qrView;

    // only for navigating between pages for now
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qrgeneration);

        b1 = findViewById(R.id.pageSwitcher);
        editInput = findViewById(R.id.inputText);
        btGenerate = findViewById(R.id.generateQRButton);
        qrView = findViewById(R.id.QRImageView);


        btGenerate.setOnClickListener(v -> {
            generateQR();
        });

        b1.setOnClickListener(v -> {
            // for clicking the button to go back to the QR page
            Intent a = new Intent(com.example.rallyup.qrGeneration.MainActivity.this, AddEvent.class);
            a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(a);
        });

        LocalStorageController lc = LocalStorageController.getInstance();
        Log.d("MainActivity", lc.getUserID(this));
    }

    private void generateQR() {
        String text = editInput.getText().toString();
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix matrix;
        try {
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
        BarcodeEncoder encoder = new BarcodeEncoder();
        Bitmap bitmap = encoder.createBitmap(matrix);
        qrView.setImageBitmap(bitmap);
    }

}