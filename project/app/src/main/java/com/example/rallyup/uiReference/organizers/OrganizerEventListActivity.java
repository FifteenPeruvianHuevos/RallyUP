package com.example.rallyup.uiReference.organizers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rallyup.R;
import com.example.rallyup.uiReference.ListAdapter;
import com.example.rallyup.uiReference.splashScreen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * This class contains the activity for an organizer's event list, allowing them to manage their events
 * @author Kaye Maranan
 */
public class OrganizerEventListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> arrayList = new ArrayList<>();
    ImageButton orgEventListBackBtn;
    FloatingActionButton createEventButton;

    /**
     * Initializes the organizer's event list activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer_event_list);

        listView = findViewById(R.id.org_events_list);

        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);
        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);
        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);

        ListAdapter listAdapter = new ListAdapter(OrganizerEventListActivity.this, arrayList);
        listView.setAdapter(listAdapter);

        orgEventListBackBtn = findViewById(R.id.organizer_events_back_button);
        createEventButton = findViewById(R.id.createEventButton);
        orgEventListBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), splashScreen.class);
                startActivity(intent);
            }
        });

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddEventActivity.class);
                startActivity(intent);
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Integer poster = (Integer) adapterView.getItemAtPosition(i);

                Intent appInfo = new Intent(getBaseContext(), OrganizerEventDetailsActivity.class);
//                appInfo.putExtra("poster", poster);
                startActivity(appInfo);
            }
        });
    }
}