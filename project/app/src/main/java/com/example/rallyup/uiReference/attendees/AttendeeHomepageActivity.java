package com.example.rallyup.uiReference.attendees;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rallyup.FirestoreCallbackListener;
import com.example.rallyup.FirestoreController;
import com.example.rallyup.LocalStorageController;
import com.example.rallyup.R;
import com.example.rallyup.firestoreObjects.User;
import com.example.rallyup.uiReference.splashScreen;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

/**
 * This class contains the home page activity for attendees
 * @author Kaye Maranan
 */
public class AttendeeHomepageActivity extends AppCompatActivity implements FirestoreCallbackListener {

    Button attMyEventsBtn;
    Button attBrowseEventsBtn;
    FloatingActionButton editProfileBtn;
    ImageButton attHomepageBackBtn;

    TextView firstNameView;
    TextView lastNameView;
    TextView usernameView;

    @Override
    public void onGetUser(User user) {
//        Log.d("HomepageActivity", user.getFirstName());
        firstNameView.setText(user.getFirstName());
        lastNameView.setText(user.getLastName());
        usernameView.setText(user.getId());
    }

    // String attFirstName = findViewById(R.id.att_first_name)
    // String attLastName = findViewById(R.id.att_last_name)
    FloatingActionButton QRCodeScannerBtn;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                // If nothing was scanned
                if(result.getContents() == null) {
                    Toast.makeText(AttendeeHomepageActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    // function calls for when an id has been scanned go here
                    Toast.makeText(AttendeeHomepageActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    Intent eventAct = new Intent(AttendeeHomepageActivity.this, AttendeeEventDetails.class); // temporary activity, replace with event activity
                    eventAct.putExtra("scannedText", result.getContents() ); // sending string
                    startActivity(eventAct); // replace
                }
            });

    /**
     * Initializes the attendee homepage activity when it is first launched
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendee_homepage);

        FirestoreController fc = FirestoreController.getInstance();
        LocalStorageController ls = LocalStorageController.getInstance();
        fc.getUserByID(ls.getUserID(this), this);

        // Text views
        firstNameView = findViewById(R.id.att_first_name);
        lastNameView = findViewById(R.id.att_last_name);
        usernameView = findViewById(R.id.att_homepage_user);

        // buttons
        attMyEventsBtn = findViewById(R.id.attendee_my_events_button);
        attBrowseEventsBtn = findViewById(R.id.attendee_browse_events_button);
        attHomepageBackBtn = findViewById(R.id.attendee_update_back_button);
        QRCodeScannerBtn = findViewById(R.id.QRScannerButton);
        editProfileBtn = findViewById(R.id.edit_profile_button);

        // send to my events
        attMyEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeMyEventsActivity.class);
                startActivity(intent);
            }
        });

        // send to browse events
        attBrowseEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AttendeeBrowseEventsActivity.class);  // placeholder for attendee opener
                startActivity(intent);
            }
        });

        // back button to return to previous page
        attHomepageBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), splashScreen.class);  // placeholder for attendee opener
                startActivity(intent);
            }
        });


        // scan a qr code
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

        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AttendeeUpdateActivity.class);  // placeholder for attendee opener
                startActivity(intent);
            }
        });

    }
}