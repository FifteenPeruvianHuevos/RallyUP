package com.example.rallyup.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.rallyup.MainActivity;
import com.example.rallyup.R;

/**
 * Class that creates notifications and notification channels
 * */
public class NotificationObject{

    private Context context;

    public NotificationObject(Context context){
        this.context = context;
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
     * @param channelName The CharSequence of name of the
     *                  notification that will show on the app settings
     * @param channelDescription  The String of the description of the notification
     *                          that will show on the app settings
     * @param importance  An int for how important the notification is,
     *                    which will vary how it displays on the lock screen
     *                    use: NotificationManager.IMPORTANCE_*
     * */
    public void createNotificationChannel(String channelID,
                                          CharSequence channelName,
                                          String channelDescription,
                                          int importance){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, importance);
            channel.setDescription(channelDescription);

            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Creates a notification and shows shows it on the app
     * @param classObject A class object (usually Activity.class) of where you want to go when
     *                    clicking the notification
     * @param contentTitle A string of what the title of the notification would show as in the notification popup
     * @param contentText A string of what to show as the body of text of the notification
     * @param notifID An int for what the notification ID should be
     * @param visibility use NotificationCompat.VISIBILITY_* to determine how visible you
     *                   want the notifications
     * @param priority use NotificationCompat.PRIORITY_* to determine the initial priority of notifcations,
     *                 whether it makes sounds and shows or not, etc.
     * */
    public void createNotification(boolean onlySmallNotification,
                                   Class classObject,
                                   String channelID,
                                   String contentTitle,
                                   String contentText,
                                   int smallIcon,
                                   int notifID,
                                   int visibility,
                                   int priority,
                                   boolean autoCancel) {
        // Setting up the intent on where we want to go for the notification tap
        Intent intent = new Intent(context, classObject);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(context,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setContentIntent(pendingIntent) // Set the intent that fires when the user taps the notification.
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSmallIcon(smallIcon)
                .setVisibility(visibility)
                .setPriority(priority)
                .setAutoCancel(autoCancel);

        //notify(notificationID, builder.build())

        // This is to show the notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //manager.notify("TEST", notifID, builder.build());
        manager.notify(notifID, builder.build());
    }

}