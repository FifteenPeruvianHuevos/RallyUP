package com.example.rallyup.uiReference.attendees;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rallyup.R;


import com.example.rallyup.uiReference.ListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

// import java.util.ArrayList;

/**
 * This class contains the activity for the attendee's registered events
 * @author Kaye Maranan
 */
public class AttendeeMyEventsActivity extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                // If nothing was scanned
                if(result.getContents() == null) {
                    Toast.makeText(AttendeeMyEventsActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    // function calls for when an id has been scanned go here
                    Toast.makeText(AttendeeMyEventsActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    Intent eventAct = new Intent(AttendeeMyEventsActivity.this, AttendeeEventDetails.class); // temporary activity, replace with event activity
                    eventAct.putExtra("scannedText", result.getContents() ); // sending string
                    startActivity(eventAct); // replace
                }
            });
    ImageButton attMyEventsBackBtn;
    FloatingActionButton QRCodeScannerBtn;

    ListView listView;
//     ArrayList<Integer> arrayList = new ArrayList<>();

    /**
     * Initializes the attendee's registered event list activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_my_events);

        attMyEventsBackBtn = findViewById(R.id.browse_events_back_button);
        QRCodeScannerBtn = findViewById(R.id.QRScannerButton);
      
        listView = findViewById(R.id.att_my_events_list);

        //temporary list for testing - isla

        

//         arrayList.add(R.drawable.poster1);
//         arrayList.add(R.drawable.poster2);
//         arrayList.add(R.drawable.poster1);
//         arrayList.add(R.drawable.poster2);
//         arrayList.add(R.drawable.poster1);
//         arrayList.add(R.drawable.poster2);

//         ListAdapter listAdapter = new ListAdapter(AttendeeMyEventsActivity.this, arrayList);
//         listView.setAdapter(listAdapter);

        // end temporary list

        attMyEventsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeHomepageActivity.class);
                startActivity(intent);
            }
        });

        QRCodeScannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // options for the scanner
                ScanOptions options = new ScanOptions();
                options.setOrientationLocked(false);
                options.setBeepEnabled(false);
                options.setCaptureActivity(ScannerActivity.class);
                barcodeLauncher.launch(options);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Integer poster = (Integer) adapterView.getItemAtPosition(i);

                Intent appInfo = new Intent(getBaseContext(), AttendeeRegisteredEvent.class);
//                appInfo.putExtra("poster", poster);
                startActivity(appInfo);
            }
        });
    }
}