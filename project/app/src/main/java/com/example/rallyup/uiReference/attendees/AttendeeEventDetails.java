package com.example.rallyup.uiReference.attendees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.rallyup.R;

public class AttendeeEventDetails extends AppCompatActivity {

    ImageButton attEventBackButton;
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