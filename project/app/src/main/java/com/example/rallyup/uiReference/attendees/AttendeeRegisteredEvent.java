package com.example.rallyup.uiReference.attendees;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.rallyup.R;

/**
 * This class contains the event activity for an attendee's registered events
 * @author Isla Medina
 */
public class AttendeeRegisteredEvent extends AppCompatActivity {
    /**
     * Initializes the registered event details activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee_registered_event);
    }
}