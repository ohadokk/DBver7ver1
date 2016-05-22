package com.example.ohadkleinkedem.db_ver7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLiteHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "EventsManager";

    protected static final String TABLE_EVENTS = "events";

    public static final String EVENT_ID = "id";
    public static final String EVENT_NAME = "EventName";
    public static final String EVENT_NUMBER = "event_number";
    public static final String EVENT_TYPE = "event_type";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + EVENT_ID + " INTEGER PRIMARY KEY," + EVENT_NAME + " TEXT,"
                + EVENT_NUMBER + " TEXT, " + EVENT_TYPE + " TEXT)";
        db.execSQL(CREATE_EVENTS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }
}
