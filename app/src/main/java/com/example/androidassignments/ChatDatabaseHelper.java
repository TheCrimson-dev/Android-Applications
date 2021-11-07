package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "Message.db";
    static int VERSION_NUM = 7;
    final static String KEY_ID = "KEY_ID";
    final static String KEY_MESSAGE = "KEY_MESSAGE";
    final static String TABLE_NAME = "MESSAGES";
    final static String DATABASE_CREATE = "create table " + TABLE_NAME +
            " ( " + KEY_ID + " integer primary key autoincrement, " +
            KEY_MESSAGE + " text not null);";



    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        db.execSQL(DATABASE_CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVer + " newVersion=" + newVer);
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

}
