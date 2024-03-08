package com.example.rallyup.uiReference.attendees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rallyup.FirestoreCallbackListener;
import com.example.rallyup.FirestoreController;
import com.example.rallyup.R;
import com.example.rallyup.firestoreObjects.Event;

/**
 * This class contains the event activity for an attendee when they browse and click on an event
 * @author Kaye Maranan
 */
public class AttendeeEventDetails extends AppCompatActivity {

    ImageButton attEventBackButton;
    ImageView poster;
    TextView eventName, eventDate, eventLocation, eventDetails;

    Event displayEvent;

    FirestoreController controller = new FirestoreController();

    FirestoreCallbackListener listener = new FirestoreCallbackListener() {
        /**
         * @param event
         */
        @Override
        public void onGetEvent(Event event) {
            FirestoreCallbackListener.super.onGetEvent(event);
            displayEvent = event;
        }
    };

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
        Bundle extras = getIntent().getExtras();
        String eventID = extras.getString("key");

        controller.getEventByID(eventID, listener);

        attEventBackButton = findViewById(R.id.attendee_event_back_button);
        poster = findViewById(R.id.imageView);
        eventName = findViewById(R.id.att_registered_event_name);
        eventDate = findViewById(R.id.att_register_event_date);
        eventLocation = findViewById(R.id.att_register_event_location);
        eventDetails = findViewById(R.id.att_register_event_details);

        controller.getPosterByEventID(displayEvent.getPosterRef(), this, poster);
        eventName.setText(displayEvent.getEventName());
        String uneditedDate = displayEvent.getEventDate();
        String editedDate = uneditedDate.substring(0,3) + "-" + uneditedDate.substring(4,5) + "-" + uneditedDate.substring(6,7);
        String uneditedTime = displayEvent.getEventTime();
        String editedTime = uneditedTime.substring(0,1) + ":" + uneditedDate.substring(2,3);
        String fullDateText = editedDate + "At" + editedTime;
        eventDate.setText(fullDateText);
        eventLocation.setText(displayEvent.getEventLocation());
        eventDetails.setText(displayEvent.getEventDescription());

        attEventBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeBrowseEventsActivity.class);
                startActivity(intent);
            }
        });
    }
}