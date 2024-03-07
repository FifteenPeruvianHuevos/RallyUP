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
import androidx.lifecycle.ViewModelProvider;

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
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.IOException;


public class imageLoadTestActivity extends AppCompatActivity {
    private ImageView poster;
    private Button back;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    StorageReference storageRef = storage.getReference();
    private StorageReference posterRef;

    private String posterPath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_load_test);
        poster = findViewById(R.id.imageView2);
        back = findViewById(R.id.backButton);

        DocumentReference docRef = db.collection("events").document("checkign logcat");
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            Event event = documentSnapshot.toObject(Event.class);
            posterPath = event.getPosterRef();
            try {
                showPoster();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchPage();
            }
        });

    }

    private void showPoster() throws IOException {
        //StorageReference posterRef = storageRef.child("images/island.jpg");

        StorageReference storageReference = FirebaseStorage.getInstance().getReference(posterPath);
        // [START download_to_local_file]

        File localFile = File.createTempFile("images", "jpg");

        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                // Local temp file has been created
                String path = localFile.getAbsolutePath();
                poster.setImageBitmap(BitmapFactory.decodeFile(path));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

    }

    private void switchPage() {
        // SWITCHING THE PAGE TO THE CREATE EVENT PAGE
        Intent a = new Intent(imageLoadTestActivity.this, com.example.rallyup.qrGeneration.AddEvent.class);
        a.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(a);
    }



}
