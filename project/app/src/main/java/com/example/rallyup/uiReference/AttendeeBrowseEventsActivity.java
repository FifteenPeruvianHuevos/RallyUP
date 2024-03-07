package com.example.rallyup.uiReference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.rallyup.R;

public class AttendeeBrowseEventsActivity extends AppCompatActivity {


    ImageButton attBrowseEventsBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_browse_events);


        attBrowseEventsBackBtn = findViewById(R.id.browse_events_back_button);

        attBrowseEventsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeHomepageActivity.class);
                startActivity(intent);
            }
        });
        // browse_events_back_button
    }
}