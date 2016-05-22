package com.example.ohadkleinkedem.db_ver7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class EventsHandler {

    private SQLiteHelper dbHelper;

    public EventsHandler(Context context) {
        dbHelper = new SQLiteHelper(context);
    }


    int addContact(Events events) {

            SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbHelper.EVENT_NAME, events.getName());
        values.put(dbHelper.EVENT_NUMBER, events.getNumber());
        values.put(dbHelper.EVENT_TYPE, events.getType());

        long insertId = db.insert(dbHelper.TABLE_EVENTS, null, values);
        db.close();
        return (int)insertId;
    }


    Events getContact(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(dbHelper.TABLE_EVENTS, new String[] { dbHelper.EVENT_ID,
                        dbHelper.EVENT_NAME, dbHelper.EVENT_NUMBER, dbHelper.EVENT_TYPE}, dbHelper.EVENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Events events = new Events(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return events;
    }


    public List<Events> getAllContacts() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Events> eventsList = new ArrayList<Events>();
        String selectQuery = "SELECT  * FROM " + dbHelper.TABLE_EVENTS;

        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                Events events = new Events();
                events.setID(Integer.parseInt(cursor.getString(0)));
                events.setName(cursor.getString(1));
                events.setNumber(cursor.getString(2));
                events.setType(cursor.getString(3));

                eventsList.add(events);
            } while (cursor.moveToNext());
        }

        return eventsList;
    }


    public int updateContact(Events events) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbHelper.EVENT_NAME, events.getName());
        values.put(dbHelper.EVENT_NUMBER, events.getNumber());
        values.put(dbHelper.EVENT_TYPE, events.getType());


        return db.update(dbHelper.TABLE_EVENTS, values, dbHelper.EVENT_ID + " = ?",
                new String[] { String.valueOf(events.getID()) });
    }


    public void deleteContact(Events events) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(dbHelper.TABLE_EVENTS, dbHelper.EVENT_ID + " = ?",
                new String[] { String.valueOf(events.getID()) });
        db.close();
    }


    public int getContactsCount() {

        String countQuery = "SELECT  * FROM " + dbHelper.TABLE_EVENTS;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}