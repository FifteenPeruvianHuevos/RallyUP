package com.example.rallyup.uiReference.organizers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rallyup.FirestoreCallbackListener;
import com.example.rallyup.FirestoreController;
import com.example.rallyup.MainActivity;
import com.example.rallyup.R;
import com.example.rallyup.firestoreObjects.Attendance;
import com.example.rallyup.firestoreObjects.Event;
import com.example.rallyup.notification.NotificationObject;
import com.example.rallyup.progressBar.ManageMilestoneDialog;
import com.example.rallyup.progressBar.ProgressBarActivity;

import java.util.List;
import java.util.Locale;


/**
 * OrganizerEventDetailsActivity displays detailed information about a specific event that an organizer is organizing, including event poster, event details, sending announcements, viewing milestones.
 * This activity allows the organizer to view the list of attendees for the event by clicking a button and to navigate back to the event list.
 */
public class OrganizerEventDetailsActivity extends AppCompatActivity
    implements FirestoreCallbackListener {
    NotificationObject notificationObject = new NotificationObject(this);

    Button viewEventAttendeesList; // Button to view the list of attendees for the event
    Button viewCheckInQRCode;
    ImageButton orgEventDetailsBackBtn; // ImageButton to navigate back to the event list

    ImageButton milestoneEditButton;
    Button sendNotificationButton;
    EditText editNotificationTitle;
    EditText editNotificationBody;
    ProgressBar progressBar;



    @Override
    public void onGetEvent(Event event) {
        TextView eventView = findViewById(R.id.org_event_details_name);
        TextView eventTime = findViewById(R.id.org_event_details_date);
        TextView eventLocation = findViewById(R.id.org_event_details_location);
        TextView eventDescription = findViewById(R.id.org_register_event_details);

        eventView.setText(event.getEventName());
        eventTime.setText(event.getEventTime());
        eventLocation.setText(event.getEventLocation());
        eventDescription.setText(event.getEventDescription());

        // Load in poster for this event
        FirestoreController fc = FirestoreController.getInstance();
        fc.getPosterByEvent(event, this);
    }

    @Override
    public void onGetAttendants(List<Attendance> attendantList) {
        TextView eventVerifiedAttendeesView = findViewById(R.id.verifed_attendees);
        TextView eventTotalAttendees = findViewById(R.id.total_attendees);

        eventTotalAttendees.setText(attendantList.size() + " total attendees");

        int count = 0;
        for (Attendance attendance : attendantList) {
            if (attendance.isAttendeeVerified()) count++;
        }
        eventVerifiedAttendeesView.setText(count + " verified attendees");
    }

    @Override
    public void onGetImage(Bitmap bm) {
        // The UI XML doesn't have an image thing yet, will need to get the proper ID for it once set
        //ImageView eventPoster = findViewById(R.id.ProgressBarEventPosterView);
        //eventPoster.setImageBitmap(bm);
    }

    /**
     * Called when the activity is starting. This is where most initialization should go. Shows the organizer event details xml and displays for the user
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_event_details);

        String notification_channel_ID_milestone =
                getString(R.string.notification_channel_ID_milestone);
        String notification_channel_name_milestone =
                getString(R.string.notification_channel_name_milestone);
        String notification_channel_description_milestone =
                getString(R.string.notification_channel_description_milestone);


        notificationObject.createNotificationChannel(
                notification_channel_ID_milestone,
                notification_channel_name_milestone,
                notification_channel_description_milestone,
                NotificationCompat.PRIORITY_DEFAULT);

        orgEventDetailsBackBtn = findViewById(R.id.organizer_details_back_button); // Initializing back button
        viewEventAttendeesList = findViewById(R.id.event_attendees_button); // Initializing button to view attendees list
        viewCheckInQRCode = findViewById(R.id.view_qr_code_button);

        milestoneEditButton = findViewById(R.id.imageButton5);
        progressBar = findViewById(R.id.progressBar3);
        sendNotificationButton = findViewById(R.id.org_send_notification);

        editNotificationTitle = findViewById(R.id.notification_title);
        editNotificationBody = findViewById(R.id.notification_details);


        // FirestoreController here
        FirestoreController fc = FirestoreController.getInstance();
        fc.getEventByID("Actual last test before pushing lol", this);
        fc.getEventAttendantsByEventID("Actual last test before pushing lol", this);

        // Need to implement firebase to get the proper count of attendees here
        //setProgressOfEvent(progressBar,70, 100);
        progressBar.setProgress(70);

        // Setting onClickListener for the back button to navigate back to the event list
        orgEventDetailsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OrganizerEventListActivity.class);
                startActivity(intent);
            }
        });

        // Setting onClickListener for the button to view attendees list
        viewEventAttendeesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EventAttendeesInfoActivity.class);
                startActivity(intent);
            }
        });

        milestoneEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManageMilestoneDialog manageMilestoneDialog = new ManageMilestoneDialog();
                manageMilestoneDialog.show(getSupportFragmentManager(), "ManageMilestonesDialog");
            }
        });

        sendNotificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editNotificationBody.getText().toString().equals("") &&
                        !editNotificationTitle.getText().toString().equals("")){
                    // Create a new notification/announcement in the Firebase
                    // Which then if we go to Attendees side of the activities, they should be able
                    // to detect a new notification create for their specific event
                    // Test notification
                    notificationObject.createNotification(
                            MainActivity.class,
                            notification_channel_ID_milestone,
                            editNotificationTitle.getText().toString(),
                            editNotificationBody.getText().toString(),
                            (R.drawable.poster1),
                            0,
                            NotificationCompat.VISIBILITY_PUBLIC,
                            NotificationCompat.PRIORITY_DEFAULT,
                            true,
                            true,
                            null);
                } else {
                    Toast toasty = Toast.makeText(OrganizerEventDetailsActivity.this,
                            "Missing title and/or body text.", Toast.LENGTH_SHORT);
                    toasty.show();
                }
            }
        });

        if (progressBar.getProgress() >= 30) {
            notificationObject.createNotification(MainActivity.class,
                    notification_channel_ID_milestone,
                    "Milestone Achieved",
                    String.format(Locale.getDefault(), "We have %d participants!", progressBar.getProgress()),
                    (R.drawable.poster1),
                    0,
                    NotificationCompat.VISIBILITY_PUBLIC,
                    NotificationCompat.PRIORITY_DEFAULT,
                    true,
                    true,
                    null);
        }
    }

    private void setProgressOfEvent(ProgressBar progressBar, int currentAttendees, int goalOrMax){
        int maximum = progressBar.getMax();

        // (number of participants / Max or Goal) * maximum
        int percentageOfProgress = (currentAttendees / goalOrMax) * maximum;

        progressBar.setProgress(percentageOfProgress);
    }
}