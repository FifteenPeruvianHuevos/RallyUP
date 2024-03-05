package com.example.rallyup.attendeeUpdateInfo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rallyup.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AttendeeUpdateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendeeupdateinfo);

        // We need to access our database and get the information as needed

        // Edit image section
        ImageView profilePicture = findViewById(R.id.attendeeUpdateInfoImageViewXML);
        FloatingActionButton editImageButton = findViewById(R.id.attendeeUpdateInfoPictureFABXML);

        // Edit personal info section
        EditText editFirstName = findViewById(R.id.editFirstNameXML);
        EditText editLastName = findViewById(R.id.editLastNameXML);
        EditText editEmail = findViewById(R.id.editEmailAddressXML);
        EditText editPhoneNumber = findViewById(R.id.editPhoneNumberXML);
        CheckBox geolocationCheck = findViewById(R.id.checkBoxGeolocXML);
        Button confirmEditButton = findViewById(R.id.attendeeUpdateInfoConfirmXML);

        editFirstName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        editLastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        editPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });

        geolocationCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                return;
            }
        });


        confirmEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This is where we change the data into the input inside all the editable views

                // Need to check if they had anything in their data
                // If there was nothing, then accept the new changes
                // If the changes are different than the pre-existing data,
                // then accept the new changes.
                // IF the data is still the same as before, then DO NOT CHANGE THE DATA

                // This is assuming we have a user object that I have access to, and has
                // proper setters and getters for its data

                // "user.firstName" = editFirstName.getText().toString();
                // "user.lastName" = editLastName.getText().toString();
                // "user.email" = editEmail.getText().toString();
                // "user.phoneNumber" = editPhoneNumber.getText().toString();
                // "user.geolocationOn" = geolocationCheck.isChecked();

            }
        });

    }



}
