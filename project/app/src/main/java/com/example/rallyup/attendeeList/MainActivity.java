package com.example.rallyup.attendeeList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.rallyup.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<AttendeeStatsClass> dataList;
    private ListView attlist;      // the view that everything will be shown on
    private AttListArrayAdapter attListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_attendee_list);

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
        attlist = findViewById(R.id.attnList);        // the view that displays all the books

        // connecting the view to the adapter that will be updating its appearance as changes occur in app
        attListAdapter = new AttListArrayAdapter(this, dataList);
        attlist.setAdapter(attListAdapter);
    }
}
