package com.example.rallyup.uiReference.organizers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rallyup.R;
import com.example.rallyup.uiReference.testingClasses.AttListArrayAdapter;
import com.example.rallyup.uiReference.testingClasses.AttendeeStatsClass;

import java.util.ArrayList;

/**
 * This class contains the activity for the attendee's info in an event
 * @author Kaye Maranan
 */
public class EventAttendeesInfoActivity extends AppCompatActivity {

    ImageButton eventAttBackButton;
    ArrayList<AttendeeStatsClass> dataList;
    private ListView attlist;      // the view that everything will be shown on
    private AttListArrayAdapter attListAdapter;

    /**
     * Initializes an event's attendees info activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendees_info);

        eventAttBackButton = findViewById(R.id.event_attendees_back_button);
        eventAttBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), OrganizerEventDetailsActivity.class);
                startActivity(intent);
            }
        });

        // array of strings?
        String[] users = {
                "Edmonton", "Vancouver", "Toronto"
        };
        Integer[] countedCheckIns = {
                2, 5, 8
        };

        dataList = new ArrayList<>();
        // creating a new array list with objects of City
        for (int i = 0; i < users.length; i++) {
            dataList.add(new AttendeeStatsClass(users[i], countedCheckIns[i]));
        }

        // add adapter for the attendees list
        attlist = findViewById(R.id.attnCheckInList);        // the view that displays all the books

        // connecting the view to the adapter that will be updating its appearance as changes occur in app
        attListAdapter = new AttListArrayAdapter(this, dataList);
        attlist.setAdapter(attListAdapter);
    }


}