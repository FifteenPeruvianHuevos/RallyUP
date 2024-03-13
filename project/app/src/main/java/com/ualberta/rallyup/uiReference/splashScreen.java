package com.ualberta.rallyup.uiReference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ualberta.rallyup.R;
import com.ualberta.rallyup.uiReference.attendees.AttendeeHomepageActivity;
import com.ualberta.rallyup.uiReference.organizers.OrganizerEventListActivity;

/**
 * This class contains the activity for the main menu splash screen
 * @author Isla Medina
 */
public class splashScreen extends AppCompatActivity {


    Button attendeeBtn;
    Button organizerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        organizerBtn = findViewById(R.id.organizer_button);
        attendeeBtn = findViewById(R.id.attendee_button);

        organizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OrganizerEventListActivity.class);
                startActivity(intent);
            }
        });

        attendeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeHomepageActivity.class);  // placeholder for attendee opener
                startActivity(intent);
            }
        });
    }



}