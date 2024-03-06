package com.example.rallyup.progressBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

// To access methods from different packages, need to import it like so
import com.example.rallyup.notification.NotificationObject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.rallyup.R;

import java.util.Locale;

/**
 * Class that is an Activity to show your ProgressBar
 * @author Chih-Hung Wu
 * @version 1.0.0
 * */
public class ProgressBarActivity extends AppCompatActivity {

    NotificationObject notificationObject = new NotificationObject(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set your CONTENT VIEWS
        setContentView(R.layout.activity_progressbar);

        // create the notification channel
        notificationObject.createNotificationChannel(
                getString(R.string.notification_channel_ID_milestone),
                getString(R.string.notification_channel_name_milestone),
                getString(R.string.notification_channel_description_milestone),
                NotificationCompat.PRIORITY_DEFAULT);

        // Initialize your XML items here
        ProgressBar progressBar = findViewById(R.id.progressBar);
        EditText progressEditText = findViewById(R.id.editProgressNumberXML);
        Button backToMain = findViewById(R.id.backToMainButtonXML);
        Button confirmButton = findViewById(R.id.confirmNumberButtonXML);

        /*for (int i = 0; i < progressBar.getMax(); i++){
            // Below should THEORETICALLY be the equivalent of progressBar.progress = currentProgress + i;
            progressBar.setProgress(progressBar.getProgress() + i);
            // Show a toast message of what our progress is
            Toast toasty = Toast.makeText
                    (ProgressBarActivity.this,
                            String.format("progress max at %d",progressBar.getMax())
                            ,Toast.LENGTH_SHORT);
            toasty.show();
        }
        */

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = Integer.parseInt(progressEditText.getText().toString());
                progressBar.setProgress(progress);


                String test_msg = String.format(
                        Locale.getDefault(),
                        "We have %d participants!",
                        progressBar.getProgress());
                // Locale.getDefault() allows us to set the string so that it converts the message
                // properly into the user's local language settings
                // for example: some languages i != I, since "capitalization" may actual
                // have different grammatical/semantic meanings

                notificationObject.createNotification(
                        ProgressBarActivity.class,
                        getString(R.string.notification_channel_ID_milestone),
                        getString(R.string.notification_title_milestone),
                        test_msg,
                        R.drawable.ic_launcher_foreground,
                        0,
                        NotificationCompat.VISIBILITY_PUBLIC,
                        NotificationCompat.PRIORITY_DEFAULT,
                        true,
                        false,
                        null);
            }
        });

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainIntent =
                        new Intent(ProgressBarActivity.this, com.example.rallyup.MainActivity.class);
                startActivity(backToMainIntent);
            }
        });

    }
}
