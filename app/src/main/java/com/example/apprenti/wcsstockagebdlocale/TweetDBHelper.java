package com.example.apprenti.wcsstockagebdlocale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.apprenti.wcsstockagebdlocale.DatabaseContract.SQL_DELETE_USER;
import static com.example.apprenti.wcsstockagebdlocale.DatabaseContract.SQL_DELETE_ORGANIZATION;
import static com.example.apprenti.wcsstockagebdlocale.DatabaseContract.SQL_DELETE_TWEET;
import static com.example.apprenti.wcsstockagebdlocale.DatabaseContract.SQL_DELETE_USER_ORGANIZATION;

/**
 * Created by apprenti on 03/10/17.
 */

public class TweetDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Tweet.db";

    public static final String SQL_CREATE_USER =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.UserEntry.TABLE_NAME + " (" +
                    DatabaseContract.UserEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.UserEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    DatabaseContract.UserEntry.COLUMN_NAME_NAME + " TEXT);";

    public static final String SQL_CREATE_ORGANIZATION =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.OrganizationEntry.TABLE_NAME + " (" +
                    DatabaseContract.OrganizationEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.OrganizationEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    DatabaseContract.OrganizationEntry.COLUMN_NAME_NAME + " TEXT);";

    public static final String SQL_CREATE_TWEET =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.TweetEntry.TABLE_NAME + " (" +
                    DatabaseContract.TweetEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.TweetEntry.COLUMN_NAME_CONTENT + " TEXT," +
                    DatabaseContract.TweetEntry.COLUMN_NAME_USER_ID + " INTEGER);";

    public static final String SQL_CREATE_USER_ORGANIZATION =
            "CREATE TABLE IF NOT EXISTS " + DatabaseContract.BelongEntry.TABLE_NAME + " (" +
                    DatabaseContract.BelongEntry.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    DatabaseContract.BelongEntry.COLUMN_NAME_ID_USER + " INTEGER," +
                    DatabaseContract.BelongEntry.COLUMN_NAME_ID_ORGANIZATION + " INTEGER);";


    public TweetDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_ORGANIZATION);
        db.execSQL(SQL_CREATE_TWEET);
        db.execSQL(SQL_CREATE_USER_ORGANIZATION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_ORGANIZATION);
        db.execSQL(SQL_DELETE_TWEET);
        db.execSQL(SQL_DELETE_USER_ORGANIZATION);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}