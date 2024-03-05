package com.example.rallyup;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorageController {
    private static final LocalStorageController instance = new LocalStorageController();

    private static final String PREF_NAME = "RallyUpPreferences";
    private static final String KEY_USER_ID = "userID";
    public static LocalStorageController getInstance() {
        return instance;
    }

    // Method to retrieve userID from SharedPreferences
    public static String getUserID(Context context) {
        if (existsUserID(context)) {
            // User has used the app before: return existing ID
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return sharedPreferences.getString(KEY_USER_ID, null);
        } else {
            // New user: create new ID for them
            return createNewUserID(context);
        }
    }

    // Method to create userID and save it to SharedPreferences
    public static String createNewUserID(Context context) {
        FirestoreController fc = FirestoreController.getInstance();
        String newID = fc.createUserID();

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, newID);
        editor.apply();
        return newID;
    }

    // Method to check if userID exists in SharedPreferences
    public static boolean existsUserID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.contains(KEY_USER_ID);
    }
}
