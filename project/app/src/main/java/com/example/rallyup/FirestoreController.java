package com.example.rallyup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.rallyup.firestoreObjects.Attendance;
import com.example.rallyup.firestoreObjects.Event;

import com.example.rallyup.firestoreObjects.QrCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.example.rallyup.firestoreObjects.User;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirestoreController {
    private static final FirestoreController instance = new FirestoreController();

    private final FirebaseFirestore dbRef;
    private final CollectionReference usersRef;
    private final CollectionReference eventsRef;
    private final CollectionReference eventAttendanceRef;
    private final CollectionReference qrRef;


    public FirestoreController() {
        dbRef = FirebaseFirestore.getInstance();
        usersRef = dbRef.collection("users");
        eventsRef = dbRef.collection("events");
        eventAttendanceRef = dbRef.collection("eventAttendance");
        qrRef = dbRef.collection("qrCodes");
    }

    public static FirestoreController getInstance() {
        return instance;
    }

    public void updateEvent(Event event, FirestoreCallbackListener callbackListener) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("ownerID", event.getOwnerID());
        qrRef.document(event.getEventID()).set(data);
    }

    public void createEvent(FirestoreCallbackListener callbackListener) {

        Event event = new Event();
        eventsRef.add(event).addOnSuccessListener(documentReference -> {
            event.setEventID(documentReference.getId());
            callbackListener.onCreateEvent(event);
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));
    }

    public void updateQrCode(QrCode qrCode, Bitmap bm) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("eventID", qrCode.getEventID());
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference storageReference = storage.getReference();
        StorageReference qrImgRef = storageReference.child("images/QR/"+ qrCode.getQrId());
        uploadImageBitmap(bm, qrImgRef);

        data.put("image", qrImgRef.getPath());
        data.put("checkIn", qrCode.isCheckIn());
        data.put("qrID", qrCode.getQrId());
        qrRef.document(qrCode.getQrId()).set(data);
    }

    public void createQRCode(String jobId, FirestoreCallbackListener callbackListener) {

        QrCode newQr = new QrCode();
        qrRef.add(newQr).addOnSuccessListener(documentReference -> {
            newQr.setQrId(documentReference.getId());
            callbackListener.onGetQrCode(newQr, jobId);
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));
    }

    public void getEventsByOwnerID(String userID, FirestoreCallbackListener callbackListener) {
        Query query = eventsRef.whereEqualTo("userID", userID);
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {

            List<Event> eventList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Event thisEvent;
                thisEvent = documentSnapshot.toObject(Event.class);
                eventList.add(thisEvent);
            }
            callbackListener.onGetEvents(eventList);

        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));

    }
    public void getUserByID(String userID, FirestoreCallbackListener callbackListener) {
        DocumentReference docRef = usersRef.document(userID);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            User user = new User();
            user.setId(documentSnapshot.getId());
            user.setEmail(documentSnapshot.getString("email"));
            user.setFirstName(documentSnapshot.getString("firstName"));
            user.setLastName(documentSnapshot.getString("lastName"));

            callbackListener.onGetUser(user);
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting document: " + e));
    }


    public void getPosterByEvent(Event event, FirestoreCallbackListener callbackListener) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(event.getPosterRef());

        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch(Exception e) {
            Log.e("FirestoreController", "Error getting picture:" + e);
        }
        File finalLocalFile = localFile;
        storageReference.getFile(finalLocalFile).addOnSuccessListener(taskSnapshot -> {
            String path = finalLocalFile.getAbsolutePath();
            callbackListener.onGetImage(BitmapFactory.decodeFile(path));
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting picture: " + e));
    }

    public void getEventByQRID(String eventQRID, FirestoreCallbackListener callbackListener) {
        Query query = qrRef.whereEqualTo("qrID", eventQRID);
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                QrCode thisQR;
                thisQR = documentSnapshot.toObject(QrCode.class);
                String eventID = thisQR.getEventID();
                callbackListener.onGetEventID(eventID);
            }
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));
    }

    public void getEventAttendantsByEventID(String eventID, FirestoreCallbackListener callbackListener) {
        Query query = eventAttendanceRef.whereEqualTo("eventID", eventID);
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Attendance> attendanceList = new ArrayList<>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                attendanceList.add(new Attendance(documentSnapshot));
            }
            callbackListener.onGetAttendants(attendanceList);
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));
    }

    public void getEventsByDate(int year, int month, int day, FirestoreCallbackListener callbackListener) {
        Query query = eventsRef.whereGreaterThan("signUpLimit", -1);
        query.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Event> EventList = new ArrayList<>();
            ArrayList<String> eventIDS = new ArrayList<String>();
            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                Event thisEvent;
                thisEvent = documentSnapshot.toObject(Event.class);
                String eDate = thisEvent.getEventDate();
                if(eDate != null){
                    if((Integer.parseInt(eDate.substring(0, 4)) >= year) &&
                            (Integer.parseInt(eDate.substring(4, 6)) >= month) &&
                            (Integer.parseInt(eDate.substring(6, 8)) >= day)) {
                        EventList.add(thisEvent);
                        eventIDS.remove(thisEvent.getEventID());
                    }
                }
            }
            callbackListener.onGetEvents(EventList);
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));
    }

    public void getEventByID(String eventID, FirestoreCallbackListener callbackListener) {

        DocumentReference docRef = eventsRef.document(eventID);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            Event thisEvent;
            thisEvent = documentSnapshot.toObject(Event.class);
            callbackListener.onGetEvent(thisEvent);
        }).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting document: " + e));
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
        data.put("userID", event.getOwnerID());
        data.put("eventID", event.getEventID());

        // event.getName should be replaced with unique event ID
        eventsRef.document(event.getEventID()).set(data);
    }

    /**
     * Generates a new User ID in the Firestore.
     *
     */
    // Create a new user in the Firestore and return its userID
    public void createUserID(final OnCompleteListener<DocumentReference> onCompleteListener) {
        usersRef.add(new User())
                .addOnCompleteListener(onCompleteListener);
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
                .addOnFailureListener(exception -> {
                    // Handle unsuccessful uploads
                })
                .addOnSuccessListener(taskSnapshot -> {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
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
        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
            // ...
        });
    }

    public void uploadImageBitmap(Bitmap bitmap, StorageReference sReference) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = sReference.putBytes(data);
        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {
            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
            // ...
        });
    }

    @Deprecated
    public void getEventAttendantsByEventID(String eventID, final OnSuccessListener<QuerySnapshot> onSuccessListener) {
        Query query = eventAttendanceRef.whereEqualTo("eventID", eventID);
        query.get().addOnSuccessListener(onSuccessListener).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting documents: " + e));
    }

    @Deprecated
    public void getEventByID(String eventID, final OnSuccessListener<DocumentSnapshot> onSuccessListener) {
        DocumentReference docRef = eventsRef.document(eventID);
        docRef.get().addOnSuccessListener(onSuccessListener).addOnFailureListener(e -> Log.e("FirestoreController", "Error getting document: " + e));
    }

    public void getPosterByEventID(String posterPath, Context context, ImageView poster) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference(posterPath);
        Glide.with(context)
                .load(storageReference)
                .into(poster);
    }
}



