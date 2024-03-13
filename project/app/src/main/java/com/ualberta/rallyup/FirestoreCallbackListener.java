package com.ualberta.rallyup;

import android.graphics.Bitmap;

import com.ualberta.rallyup.firestoreObjects.Attendance;
import com.ualberta.rallyup.firestoreObjects.Event;
import com.ualberta.rallyup.firestoreObjects.QrCode;
import com.ualberta.rallyup.firestoreObjects.User;

import java.util.List;

public interface FirestoreCallbackListener {
    default void onGetEvent(Event event) {
    }

    default void onGetEventID(String eventID) {
    }

    default void onGetEvents(List<Event> eventList) {
    }

    default void onGetImage(Bitmap bm) {
    }

    default void onGetImage(Bitmap bm, String jobId) {
    }

    default void onGetAttendants(List<Attendance> attendantList) {
    }

    default void onGetUser(User user) {
    }

    default void onGetQrCode(QrCode qrCode, String jobId) {
    }

    default void onCreateEvent(Event event) {

    }
}
