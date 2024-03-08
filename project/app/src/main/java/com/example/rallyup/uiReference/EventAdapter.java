package com.example.rallyup.uiReference;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.rallyup.R;

import com.example.rallyup.firestoreObjects.Event;

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
    public Object getItem(int position) {
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

        // Set event details to respective TextViews
        TextView nameTextView = convertView.findViewById(R.id.event_list_name);
        TextView locationTextView = convertView.findViewById(R.id.event_list_location_name);

        nameTextView.setText(event.getEventName());
        locationTextView.setText(event.getEventLocation());
        // Set other TextViews as needed

        return convertView;
    }
}