package com.example.rallyup.progressBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rallyup.R;

/**
 * Class that is an Activity to show your ProgressBar
 * @author Chih-Hung Wu
 * @version 1.0.0
 * */
public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set your CONTENT VIEWS
        setContentView(R.layout.activity_progressbar);

        // Initialize your XML items here
        ProgressBar progressBar = findViewById(R.id.progressBar);
        EditText progressEditText = findViewById(R.id.editProgressNumberXML);
        Button backToMain = findViewById(R.id.backToMainButtonXML);
        Button confirmButton = findViewById(R.id.confirmNumberButtonXML);

        for (int i = 0; i < progressBar.getMax(); i++){
            // Below should THEORETICALLY be the equivalent of progressBar.progress = currentProgress + i;
            progressBar.setProgress(progressBar.getProgress() + i);
            // Show a toast message of what our progress is
            Toast toasty = Toast.makeText
                    (ProgressBarActivity.this,
                            String.format("progress max at %d",progressBar.getMax())
                            ,Toast.LENGTH_SHORT);
            toasty.show();
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = Integer.parseInt(progressEditText.getText().toString());
                progressBar.setProgress(progress);
            }
        });

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainIntent =
                        new Intent(ProgressBarActivity.this, com.example.rallyup.progressBar.MainActivity.class);
                startActivity(backToMainIntent);
            }
        });

    }
}
