package com.example.quiz2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_V = 1;
    private static final String DB_N = "MyDB";


    public DbHelper(Context context) {
        super(context, DB_N, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CR_T);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final String CR_T = "CREATE TABLE users (id INTEGER NOT NULL, customId TEXT, title TEXT, userId TEXT, completed TEXT)";
}
