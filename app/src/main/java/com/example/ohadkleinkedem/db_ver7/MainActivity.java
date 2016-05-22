package com.example.ohadkleinkedem.db_ver7;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Events> EventList;
    private ListView listView;
    private TextView EventName, EventDate, EventType;
    private Button add;
    private EventsAdapter adapter;
    EventsHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.contactlist);

        db = new EventsHandler(this);

        EventList = db.getAllContacts();

        adapter = new EventsAdapter(this, EventList);
        listView.setAdapter(adapter);
    }

    public void onClick(View view){

        EventName = (EditText) findViewById(R.id.name);
        EventDate = (EditText) findViewById(R.id.number);
        EventType = (EditText) findViewById(R.id.type);

        String eName = EventName.getText().toString();
        String num = EventDate.getText().toString();
        String eType = EventType.getText().toString();

        int id = db.addContact(new Events(eName, num, eType));
        EventList.add(new Events(id, eName, num, eType));
        adapter.notifyDataSetChanged();
    }
}
