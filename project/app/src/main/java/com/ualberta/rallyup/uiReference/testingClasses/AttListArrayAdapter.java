package com.ualberta.rallyup.uiReference.testingClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ualberta.rallyup.R;

import java.util.ArrayList;

public class AttListArrayAdapter extends ArrayAdapter<AttendeeStatsClass> {

    public AttListArrayAdapter(Context context, ArrayList<AttendeeStatsClass> attStats) {
        super(context, 0, attStats);
    }

    @NonNull
    @Override
    // custom view is created. this is the posting displayed for each book in the arraylist of books on main activity
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(super.getContext()).inflate(R.layout.attlist_entry, parent, false);
        } else {
            view = convertView;
        }
        // says where the item is at that position and returns the item at that position.
        AttendeeStatsClass stats = super.getItem(position);

        TextView username = view.findViewById(R.id.username);
        TextView att_count = view.findViewById(R.id.count_status);

        username.setText(stats.getAttName());
        att_count.setText(stats.getCheckInCount().toString());

        return view;
    }
}

