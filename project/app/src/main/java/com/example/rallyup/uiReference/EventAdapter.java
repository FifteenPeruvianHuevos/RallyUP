package com.example.rallyup.uiReference;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rallyup.FirestoreController;
import com.example.rallyup.R;

import com.example.rallyup.firestoreObjects.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class EventAdapter extends BaseAdapter {
    private Context context;
    private List<Event> eventList;
    private LayoutInflater inflater;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Event getItem(int position) {
        Log.d("EVENTADAPTER", "onItemClick:" + eventList.get(position).getEventName());
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.event_list, parent, false);
        }

        Event event = eventList.get(position);

        FirestoreController fController = new FirestoreController();

        // Set event details to respective TextViews
        TextView nameTextView = convertView.findViewById(R.id.event_list_name);
        TextView locationTextView = convertView.findViewById(R.id.event_list_location_name);
        TextView dateTextView = convertView.findViewById(R.id.event_list_date_name);
        ImageView posterImageView = convertView.findViewById(R.id.events_poster);

        nameTextView.setText(event.getEventName());
        locationTextView.setText(event.getEventLocation());
        String unformattedDate = event.getEventDate();
        String formattedDate = getProperDateFormatting(unformattedDate);
        String unformattedTime = event.getEventTime();
        String formattedTime = unformattedTime.substring(0,2) + ":" + unformattedTime.substring(2,4);
        dateTextView.setText(formattedDate + " At " + formattedTime);
        fController.getPosterByEventID(event.getPosterRef(), context, posterImageView);


        return convertView;
    }

    public String getProperDateFormatting(String date) {
        String year = date.substring(0,4);
        String month = "";
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        int monthnum=Integer.parseInt(date.substring(4,6));
        cal.set(Calendar.MONTH,monthnum);
        month = month_date.format(cal.getTime());
        String day = date.substring(6,8);
        String complete = month + " " + day + ", " + year;
        return complete;
    }

}