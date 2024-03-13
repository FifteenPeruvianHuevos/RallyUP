package com.ualberta.rallyup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.ualberta.rallyup.notification.NotificationObject;
import com.ualberta.rallyup.progressBar.ProgressBarActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create your notification channels AS SOON as the App begins
        // Doesn't hurt if you keep recreating new ones
        NotificationObject notificationObject = new NotificationObject(this);
        notificationObject.createNotificationChannel(getString(R.string.notification_channel_ID_milestone),
                getString(R.string.notification_channel_description_milestone),
                getString(R.string.notification_channel_description_milestone),
                NotificationCompat.PRIORITY_DEFAULT);

        Button progressButton = findViewById(R.id.ProgressBarButton);
        Button uiLayoutButton = findViewById(R.id.UILayoutButton);

//        Button attendeeUpdateInfoButton = findViewById(R.id.AttendeeUpdateInfoButton);


        progressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this,
                                ProgressBarActivity.class);
                startActivity(intent);
            }
        });
        uiLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this,
                                com.ualberta.rallyup.uiReference.splashScreen.class);
                startActivity(intent);
            }
        });
      
        /*
        attendeeUpdateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent(MainActivity.this, AttendeeUpdateActivity.class);
                startActivity(intent);
            }
        });
    }*/
    }
}