package com.example.rallyup;

import android.graphics.Bitmap;

import com.example.rallyup.firestoreObjects.Attendance;
import com.example.rallyup.firestoreObjects.Event;
import com.example.rallyup.firestoreObjects.QrCode;
import com.example.rallyup.firestoreObjects.User;

import java.util.ArrayList;
import java.util.List;

public interface FirestoreCallbackListener {
    default void onGetEvent(Event event) {
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
    };

    default void onGetQrCode(QrCode qrCode, String jobId) {
    };
}
