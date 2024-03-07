package com.example.rallyup.uiReference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.rallyup.R;

public class OrganizerEventDetailsActivity extends AppCompatActivity {

    Button viewEventAttendeesList;
    ImageButton orgEventDetailsBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_event_details);

        orgEventDetailsBackBtn = findViewById(R.id.organizer_details_back_button);
        viewEventAttendeesList = findViewById(R.id.event_attendees_button);
        orgEventDetailsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OrganizerEventListActivity.class);
                startActivity(intent);
            }
        });

        viewEventAttendeesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), EventAttendeesInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}