package com.example.rallyup.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rallyup.R;

public class NotificationActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // REMEMBER TO SET YOUR CONTENT VIEW ON A LAYOUT
        //setContentView();

        // create our Milestone notification channel, that will show up in the App Settings -> Notifications
        createNotificationChannel(getString(R.string.notification_channel_ID_milestone),
                getString(R.string.notification_name_milestone),
                getString(R.string.notification_description_milestone),
                NotificationManager.IMPORTANCE_DEFAULT);
    }

    /**
     * Creates a notification channel for your app, with a unique channelID.
     * Should be called as soon as the app starts.
     * Because you must create the notification channel before posting any notifications
     * on Android 8.0 and later, execute this code as soon as your app starts.
     * It's safe to call this repeatedly,
     * because creating an existing notification channel performs no operation.
     * @param channelID A String for the ID of the channel you're going to create,
     *                  not displayed on the app.
     *                  Cannot be "miscellaneous" since that's reserved already.
     * @param notifName The CharSequence of name of the
     *                  notification that will show on the app settings
     * @param notifDescription  The String of the description of the notification
     *                          that will show on the app settings
     * @param importance  An int for how important the notification is,
     *                    which will vary how it displays on the lock screen
     *                    use: NotificationManager.IMPORTANCE_*
     * */
    public void createNotificationChannel(String channelID, CharSequence notifName, String notifDescription, int importance){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            // Create the new NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(channelID, notifName, importance);
            notificationChannel.setDescription(notifDescription);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }


}