package com.example.rallyup.qrGeneration;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.rallyup.FirestoreController;
import com.example.rallyup.R;
import com.example.rallyup.firestoreObjects.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;



public class imageLoadTestActivity extends AppCompatActivity {
    private ImageView poster;
    private Button back;
    private FirestoreController controller = new FirestoreController();
    private Event thisEvent;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef;
    FirebaseAuth mAuth;

    String posterPath;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference posterRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_load_test);
        poster = findViewById(R.id.imageView2);
        back = findViewById(R.id.backButton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }

        docRef = db.collection("events").document("eventname");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                thisEvent = documentSnapshot.toObject(Event.class);
                getEventPoster();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchPage();
            }
        });

    }

    private void switchPage() {
        // SWITCHING THE PAGE TO THE CREATE EVENT PAGE
        Intent a = new Intent(imageLoadTestActivity.this, com.example.rallyup.qrGeneration.AddEvent.class);
        a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(a);
    }

    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // do your stuff
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }

    private void getEventPoster() {
        Context context = this;
        posterRef = thisEvent.getPosterRef();
        posterPath = posterRef.getPath();

        // Reference to an image file in Cloud Storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        // ImageView in your Activity
        // Download directly from StorageReference using Glide// (See MyAppGlideModule for Loader registration)
        Glide.with(context)
                .load(storageReference)
                .into(poster);
    }
}
