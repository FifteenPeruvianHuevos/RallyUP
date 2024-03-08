package com.example.rallyup.uiReference.attendees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rallyup.R;

/**
 * This class contains the event activity for an attendee's registered events
 * @author Isla Medina
 */
public class AttendeeRegisteredEvent extends AppCompatActivity {


    private View backgroundOverlay;
    ListView announcementsList;
    ImageButton backBtn;

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
        backgroundOverlay = findViewById(R.id.backgroundOverlay);
        backBtn = findViewById(R.id.att_registered_back_btn);
        announcementsList = findViewById(R.id.announcements_list);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeMyEventsActivity.class);
                startActivity(intent);
            }
        });

    }
    public void showPopupFragment() {
        AnnouncementPopupFragment popupFragment = new AnnouncementPopupFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainLayoutRegistered, popupFragment)
                .addToBackStack(null)
                .commit();

        backgroundOverlay.setVisibility(View.VISIBLE);
    }

    public void hidePopupFragment() {
        getSupportFragmentManager().popBackStack();
        backgroundOverlay.setVisibility(View.GONE);
    }


}