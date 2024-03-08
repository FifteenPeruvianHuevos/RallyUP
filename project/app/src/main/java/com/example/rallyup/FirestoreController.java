package com.example.rallyup;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.rallyup.firestoreObjects.Event;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.example.rallyup.firestoreObjects.User;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicMarkableReference;

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

    /**
     * Adds a new Event to the event collection in firebase
     *
     * @param event The new event to be added.
     */
    public void addEvent(Event event) {
        // Add the event to the Firestore collection
        HashMap<String, Object> data = new HashMap<>();
        data.put("eventName", event.getEventName());
        data.put("eventLocation", event.getEventLocation());
        data.put("eventDescription", event.getEventDescription());
        data.put("eventDate", event.getEventDate());
        data.put("eventTime", event.getEventTime());
        data.put("signUpLimit", event.getSignUpLimit());
        data.put("signUpLimitBool", event.getSignUpLimitBool());
        data.put("geolocation", event.getGeolocation());
        data.put("reUseQR", event.getReUseQR());
        data.put("newQR", event.getNewQR());
        data.put("posterRef", event.getPosterRef());
        data.put("shareQRRef", event.getShareQRRef());
        data.put("checkInQRRef", event.getCheckInQRRef());

        // event.getName should be replaced with unique event ID
        eventsRef.document(event.getEventName()).set(data);
    }

    /**
     * Generates a new User ID in the Firestore.
     *
     * @@return the new User ID.
     */
    // Create a new user in the Firestore and return its userID
    public void createUserID(final OnCompleteListener<DocumentReference> onCompleteListener) {
        usersRef.add(new User())
                .addOnCompleteListener(onCompleteListener);
    }

    /**
     *
     */
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

    /**
     * Uploads a Uri image to the firebase icloud storage.
     *
     * @param image The Uri image to be uploaded.
     * @param reference The storage reference in which the image will be stored
     */
    public void uploadImage(Uri image, StorageReference reference) {
        //final StorageReference posters = reference.child("images/" + "eventPosters/" + reference);

        reference.putFile(image)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
        })
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                        // ...
                    }
                });
    }

    /**
     * Uploads a bitmap image to the firebase icloud storage.
     *
     * @param image The bitmap image to be uploaded.
     * @param sReference The storage reference in which the image will be stored
     */
    public void uploadImageBitmap(ImageView image, StorageReference sReference) {
        // Get the data from an ImageView as bytes
        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = sReference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });
    }
}


