package com.example.rallyup.progressBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        // To delete once not needed anymore
        EditText progressEditText = findViewById(R.id.editProgressNumberXML);
        Button confirmButton = findViewById(R.id.confirmNumberButtonXML);

        // To stay for final product
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ImageView backToMain = findViewById(R.id.backToMainButtonXML);
        TextView eventView = findViewById(R.id.ProgressBarEventNameTextView);
        EditText editAnnouncementTitle = findViewById(R.id.ProgressBarAnnouncementEditTitle);
        EditText editAnnouncementBody = findViewById(R.id.ProgressBarAnnouncementBody);
        Button sendAnnouncementButton = findViewById(R.id.ProgressBarAnnouncementSendButton);


        // Here we would get the Firebase controller to do the following:
        // I want to receive the current number of attendees of this event
        // I want to get the Max Number Of Attendees or the Goal Of Attendees set by the
        // Organizers.
        // I'm not too sure how we're going to keep track or update the progressBar

        //setProgressOfEvent(progressBar, //int of current number of attendees, int of MaxAttendeesOrGoal);

        // Confirm button can be removed once we're able to access the number of attendees
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

    private void setProgressOfEvent(ProgressBar progressBar, int currentAttendees, int goalOrMax){
        int maximum = progressBar.getMax();

        // (number of participants / Max or Goal) * maximum
        int percentageOfProgress = (currentAttendees / goalOrMax) * maximum;

        progressBar.setProgress(percentageOfProgress);
    }
}
