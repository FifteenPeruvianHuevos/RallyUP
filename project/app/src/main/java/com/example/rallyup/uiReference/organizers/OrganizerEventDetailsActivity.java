package com.example.rallyup.uiReference.organizers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.rallyup.R;


/**
 * OrganizerEventDetailsActivity displays detailed information about a specific event that an organizer is organizing, including event poster, event details, sending announcements, viewing milestones.
 * This activity allows the organizer to view the list of attendees for the event by clicking a button and to navigate back to the event list.
 */
public class OrganizerEventDetailsActivity extends AppCompatActivity {

    Button viewEventAttendeesList; // Button to view the list of attendees for the event
    ImageButton orgEventDetailsBackBtn; // ImageButton to navigate back to the event list

    /**
     * Called when the activity is starting. This is where most initialization should go. Shows the organizer event details xml and displays for the user
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_event_details);

        orgEventDetailsBackBtn = findViewById(R.id.organizer_details_back_button); // Initializing back button
        viewEventAttendeesList = findViewById(R.id.event_attendees_button); // Initializing button to view attendees list

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
    }
}