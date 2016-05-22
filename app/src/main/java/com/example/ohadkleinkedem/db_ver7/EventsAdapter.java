package com.example.ohadkleinkedem.db_ver7;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class EventsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Events> EventList;

    public EventsAdapter(Activity activity, List<Events> contacts){

        this.activity = activity;
        this.EventList = contacts;
    }

    @Override
    public int getCount() {
        return EventList.size();
    }

    @Override
    public Object getItem(int position) {
        return EventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.contact_entry, null);

        TextView name= (TextView) convertView.findViewById(R.id.contactName);
        TextView contact = (TextView) convertView.findViewById(R.id.contact);
        TextView type = (TextView) convertView.findViewById(R.id.contactType);
        Button delete = (Button) convertView.findViewById(R.id.delete);

        Events list = EventList.get(position);

        name.setText(list.getName());
        contact.setText(list.getNumber());
        type.setText(list.getType());
        delete.setOnClickListener(new ListItemClickListener(position, list));

        return convertView;
    }


    private class ListItemClickListener implements View.OnClickListener {

        int position;
        Events list;

        public ListItemClickListener(int position, Events list){
            this.position = position;
            this.list = list;
        }

        @Override
        public void onClick(View v) {

            EventsHandler db = new EventsHandler(activity);
            db.deleteContact(list);
            EventList.remove(position);
            notifyDataSetChanged();
        }
    }
}
