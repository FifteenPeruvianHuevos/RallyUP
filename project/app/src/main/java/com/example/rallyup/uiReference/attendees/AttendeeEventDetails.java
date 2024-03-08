package com.example.rallyup.uiReference.attendees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.rallyup.R;

/**
 * This class contains the event activity for an attendee when they browse and click on an event
 * @author Kaye Maranan
 */
public class AttendeeEventDetails extends AppCompatActivity {

    ImageButton attEventBackButton;

    /**
     * Initializes the attendee event details activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_event_details);

        attEventBackButton = findViewById(R.id.attendee_event_back_button);

        attEventBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeBrowseEventsActivity.class);
                startActivity(intent);
            }
        });
    }
}