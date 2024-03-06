package com.example.rallyup;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorageController {
    private static final LocalStorageController instance = new LocalStorageController();

    private static final String PREF_NAME = "RallyUpPreferences";
    private static final String KEY_USER_ID = "userID";
    private boolean idInitialized = false;

    public static LocalStorageController getInstance() {
        return instance;
    }

    public void initialization(Context context) {
        if (existsUserID(context)) {
            idInitialized = true;
        } else {
            createNewUserID(context);
        }
    }

    // Method to retrieve userID from SharedPreferences
    public String getUserID(Context context) {
        assert idInitialized;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    // Method to create userID and save it to SharedPreferences
    public void createNewUserID(Context context) {
        FirestoreController fc = FirestoreController.getInstance();
        String newID = fc.createUserID();

        {
            // Firestore ID generation completed
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USER_ID, newID);
            editor.apply();
            idInitialized = true;
        }
    }

    // Method to check if userID exists in SharedPreferences
    public boolean existsUserID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_USER_ID);
    }
}
