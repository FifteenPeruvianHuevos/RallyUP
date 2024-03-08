package com.example.rallyup.uiReference.attendees;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rallyup.FirestoreCallbackListener;
import com.example.rallyup.FirestoreController;
import com.example.rallyup.LocalStorageController;
import com.example.rallyup.R;
import com.example.rallyup.firestoreObjects.Event;
import com.example.rallyup.uiReference.EventAdapter;
import com.example.rallyup.uiReference.ListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * This class contains an activity that allows an attendee to browse events
 * @author Kaye Maranan
 */
public class AttendeeBrowseEventsActivity extends AppCompatActivity implements FirestoreCallbackListener {
    ListView listView;
    ArrayList<String> eventIDS = new ArrayList<>();

    List<Event> events;
    ImageButton attBrowseEventsBackBtn;
    FirestoreController controller;
    EventAdapter eventAdapter;



    @Override
    public void onGetEventsAndIDS(List<Event> events, ArrayList<String> eventIDs){
        eventAdapter = new EventAdapter(AttendeeBrowseEventsActivity.this, events);
        listView.setAdapter(eventAdapter);
        this.events = events;
        this.eventIDS = eventIDs;

    }

    /**
     * Initializes the attendee browse event activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_browse_events);

        attBrowseEventsBackBtn = findViewById(R.id.browse_events_back_button);
        listView = findViewById(R.id.att_browse_events_list);

        /*arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);
        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);
        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);*/

        //ListAdapter listAdapter = new ListAdapter(AttendeeBrowseEventsActivity.this, arrayList);
        //listView.setAdapter(listAdapter);

        // real list
        LocalStorageController ls = LocalStorageController.getInstance();
        controller = FirestoreController.getInstance();

        // getting the current date to use it to query the firebase to find all events that have not yet passed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        int year = Integer.parseInt(currentDateandTime.substring(0,3));
        int month = Integer.parseInt(currentDateandTime.substring(4,5));
        int day = Integer.parseInt(currentDateandTime.substring(6,7));
        controller.getEventsByDate(year, month, day, this);

        // back button
        attBrowseEventsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeHomepageActivity.class);
                startActivity(intent);
            }
        });

        // list listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Integer poster = (Integer) adapterView.getItemAtPosition(i);
                Event selectedEvent = eventAdapter.getItem(i);

                String eventID = selectedEvent.getEventID();
                Intent intent = new Intent(AttendeeBrowseEventsActivity.this, AttendeeEventDetails.class);
                intent.putExtra("key", eventID);
                startActivity(intent);
            }
        });
    }
}