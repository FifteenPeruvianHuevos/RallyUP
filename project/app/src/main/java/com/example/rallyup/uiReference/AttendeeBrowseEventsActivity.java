package com.example.rallyup.uiReference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.rallyup.R;

import java.util.ArrayList;

public class AttendeeBrowseEventsActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> arrayList = new ArrayList<>();

    ImageButton attBrowseEventsBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_browse_events);

        listView = findViewById(R.id.att_browse_events_list);

        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);
        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);
        arrayList.add(R.drawable.poster1);
        arrayList.add(R.drawable.poster2);

        ListAdapter listAdapter = new ListAdapter(AttendeeBrowseEventsActivity.this, arrayList);
        listView.setAdapter(listAdapter);

        attBrowseEventsBackBtn = findViewById(R.id.browse_events_back_button);

        attBrowseEventsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeHomepageActivity.class);
                startActivity(intent);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Integer poster = (Integer) adapterView.getItemAtPosition(i);

                Intent appInfo = new Intent(getBaseContext(), AttendeeEventDetails.class);
//                appInfo.putExtra("poster", poster);
                startActivity(appInfo);
            }
        });
    }
}