package com.example.rallyup;

import android.util.Log;

import com.example.rallyup.firestoreObjects.Event;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class FirestoreController {
    private static final FirestoreController instance = new FirestoreController();

    private final FirebaseFirestore dbRef;
    private final CollectionReference usersRef;
    private final CollectionReference eventsRef;
    private final CollectionReference eventAttendanceRef;

    public FirestoreController() {
        dbRef = FirebaseFirestore.getInstance();
        usersRef = dbRef.collection("users");
        eventsRef = dbRef.collection("events");
        eventAttendanceRef = dbRef.collection("eventAttendance");
    }

    public static FirestoreController getInstance() {
        return instance;
    }

    public void addEvent(Event event) {
        // Add the event to the Firestore collection
        HashMap<String, String> data = new HashMap<>();
        data.put("title", event.getEventName());
        data.put("location", event.getEventLocation());
        data.put("description", event.getEventDescription());
        eventsRef.document("TEST EVENT NOT ALL PARAMETERS").set(data);
    }

    // Create a new user in the Firestore and return its userID
    public String createUserID() {
        // TODO
        return "hulaballoo";
    }

    public void examplePrintAllAttendance() {
        dbRef.collection("eventAttendance").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    // Access document data
                    String eventID = document.getString("eventID");
                    String userID = document.getString("userID");
                    Boolean verified = document.getBoolean("attendeeVerified");
                    Log.d("FirestoreController", eventID + ' ' + userID);
                }
            } else {
                Log.d("FirestoreController", "Error getting documents: " + task.getException());
            }
        });
    }
}


