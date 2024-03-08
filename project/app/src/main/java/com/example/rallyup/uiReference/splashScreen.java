package com.example.rallyup.uiReference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rallyup.R;
import com.example.rallyup.uiReference.attendees.AttendeeHomepageActivity;
import com.example.rallyup.uiReference.organizers.OrganizerEventListActivity;

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