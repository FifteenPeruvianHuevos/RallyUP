package com.ualberta.rallyup.progressBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.ualberta.rallyup.FirestoreCallbackListener;
import com.ualberta.rallyup.FirestoreController;
import com.ualberta.rallyup.MainActivity;
import com.ualberta.rallyup.R;
import com.ualberta.rallyup.firestoreObjects.Attendance;
import com.ualberta.rallyup.firestoreObjects.Event;
import com.ualberta.rallyup.notification.NotificationObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

/**
 * Class that is an Activity to show your ProgressBar
 * @author Chih-Hung Wu
 * @version 1.0.0
 * */
public class ProgressBarActivity extends AppCompatActivity implements FirestoreCallbackListener {

    NotificationObject notificationObject = new NotificationObject(this);

    @Override
    public void onGetEvent(Event event) {
        TextView eventView = findViewById(R.id.ProgressBarEventNameTextView);
        TextView eventTime = findViewById(R.id.ProgressBarEventDateView);
        TextView eventLocation = findViewById(R.id.ProgressBarEventLocationView);
        TextView eventDescription = findViewById(R.id.ProgressBarEventDescriptionView);

        eventView.setText(event.getEventName());
        eventTime.setText(event.getEventTime());
        eventLocation.setText(event.getEventLocation());
        eventDescription.setText(event.getEventDescription());
        ImageView eventPoster = findViewById(R.id.ProgressBarEventPosterView);


        // Load in poster for this event
        // Load in poster for this event
        FirestoreController fc = FirestoreController.getInstance();
        fc.getPosterByEventID(event.getPosterRef(), this, eventPoster);
    }

    @Override
    public void onGetAttendants(List<Attendance> attendantList) {
        TextView eventVerifiedAttendeesView = findViewById(R.id.ProgressBarEventAttendeesNumberView);
        TextView eventTotalAttendees = findViewById(R.id.ProgressBarEventTotalAttendeesView);

        eventTotalAttendees.setText(attendantList.size() + " total attendees");

        int count = 0;
        for (Attendance attendance : attendantList) {
            if (attendance.isAttendeeVerified()) count++;
        }
        eventVerifiedAttendeesView.setText(count + " verified attendees");
    }

    /*
    @Override
    public void onGetImage(Bitmap bm) {
        ImageView eventPoster = findViewById(R.id.ProgressBarEventPosterView);
        eventPoster.setImageBitmap(bm);
    }*/

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
        // To stay for final product
        // Top of ProgressBarActivity
        ImageView backToMain = findViewById(R.id.backToMainButtonXML);
        TextView eventView = findViewById(R.id.ProgressBarEventNameTextView);
        ImageView eventPoster = findViewById(R.id.ProgressBarEventPosterView);
        FloatingActionButton editPosterButton = findViewById(R.id.ProgressBarEditEventPosterButton);
        // Event Details
        TextView eventTime = findViewById(R.id.ProgressBarEventDateView);
        TextView eventLocation = findViewById(R.id.ProgressBarEventLocationView);
        TextView eventVerifiedAttendeesView = findViewById(R.id.ProgressBarEventAttendeesNumberView);
        TextView eventTotalAttendees = findViewById(R.id.ProgressBarEventTotalAttendeesView);
        TextView eventDescription = findViewById(R.id.ProgressBarEventDescriptionView);
        TextView eventViewAttendees = findViewById(R.id.ProgressBarEventViewAttendeesView);
        ImageView shareEventImageView = findViewById(R.id.ProgressBarEventShareEventImage);
        Button checkInQRCodeButton = findViewById(R.id.ProgressBarEventCheckInQRCodeButton);
        // Milestones
        ImageView editMilestonesDialogButton = findViewById(R.id.ProgressBarMilestonesEditButton);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        // Announcements
        EditText editAnnouncementTitle = findViewById(R.id.ProgressBarAnnouncementEditTitle);
        EditText editAnnouncementBody = findViewById(R.id.ProgressBarAnnouncementBody);
        Button sendAnnouncementButton = findViewById(R.id.ProgressBarAnnouncementSendButton);

        FirestoreController fc = FirestoreController.getInstance();
        fc.getEventByID("Actual last test before pushing lol", this);
        fc.getEventAttendantsByEventID("Actual last test before pushing lol", this);

        eventViewAttendees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Send the Organizer to U.S. 01.02.1/01.09.1/01.08.01

            }
        });

        checkInQRCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Takes us to the Event Check-In QR Code Dialog/Fragment
            }
        });


        // Here we would get the Firebase controller to do the following:
        // I want to receive the current number of attendees of this event
        // I want to get the Max Number Of Attendees or the Goal Of Attendees set by the
        // Organizers.
        // I'm not too sure how we're going to keep track or update the progressBar

        //setProgressOfEvent(progressBar, //int of current number of attendees, int of MaxAttendeesOrGoal);

        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainIntent =
                        new Intent(ProgressBarActivity.this, MainActivity.class);
                startActivity(backToMainIntent);
            }
        });

        editPosterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Something Something
            }
        });

        shareEventImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start the share event QR code dialog U.S. - 01.06.01
            }
        });



        editMilestonesDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageMilestoneDialog manageMilestoneDialog = new ManageMilestoneDialog();
                manageMilestoneDialog.show(getSupportFragmentManager(), "ManageMilestonesDialog");
            }
        });

        sendAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editAnnouncementBody.getText().toString().equals("") && !editAnnouncementTitle.getText().toString().equals("")){
                    // Create a new notification/announcement in the Firebase
                    // Which then if we go to Attendees side of the activities, they should be able
                    // to detect a new notification create for their specific event
                } else {
                    Toast toasty = Toast.makeText(ProgressBarActivity.this, "Missing title and/or body text.", Toast.LENGTH_SHORT);
                    toasty.show();
                }
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
