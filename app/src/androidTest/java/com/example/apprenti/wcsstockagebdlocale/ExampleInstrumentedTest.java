package com.example.apprenti.wcsstockagebdlocale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void dbTest0() throws Exception {
        //Context of the under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.apprenti.wcsstockagebdlocale", appContext.getPackageName());
    }

    @Test
    public void createUserOrgnaisationBelongTweetTest() throws Exception {
        //Context of the under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        TweetDBHelper mDBHelper = new TweetDBHelper(appContext);
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        ContentValues user = new ContentValues();
        user.put(DatabaseContract.UserEntry.COLUMN_NAME_NAME, "User name");
        user.put(DatabaseContract.UserEntry.COLUMN_NAME_EMAIL, "User email");
        long newUserId = db.insert(DatabaseContract.UserEntry.TABLE_NAME, null, user);
        assertNotEquals(-1, newUserId);


        ContentValues organization = new ContentValues();
        organization.put(DatabaseContract.OrganizationEntry.COLUMN_NAME_NAME, "Organization name");
        organization.put(DatabaseContract.OrganizationEntry.COLUMN_NAME_EMAIL, "Organization email");
        long newOrganizationId = db.insert(DatabaseContract.OrganizationEntry.TABLE_NAME, null, organization);
        assertNotEquals(-1, newOrganizationId);


        ContentValues belong = new ContentValues();
        belong.put(DatabaseContract.BelongEntry.COLUMN_NAME_ID_USER, newUserId);
        belong.put(DatabaseContract.BelongEntry.COLUMN_NAME_ID_ORGANIZATION, newOrganizationId);
        long newBelongId = db.insert(DatabaseContract.BelongEntry.TABLE_NAME, null, belong);
        assertNotEquals(-1, newBelongId);


        for (int i = 0; i < 10; i++) {
            ContentValues tweet = new ContentValues();
            tweet.put(DatabaseContract.TweetEntry.COLUMN_NAME_CONTENT, "Tweet content " + i);
            tweet.put(DatabaseContract.TweetEntry.COLUMN_NAME_USER_ID, newUserId);
            long newTweetId = db.insert(DatabaseContract.TweetEntry.TABLE_NAME, null, tweet);
            assertNotEquals(-1, newTweetId);
        }

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                DatabaseContract.TweetEntry.COLUMN_NAME_CONTENT
        };

// Filter results WHERE "title" = 'My Title'
        String selection = DatabaseContract.TweetEntry.COLUMN_NAME_USER_ID + " = ?";
        String[] selectionArgs = { String.valueOf(newUserId) };

        Cursor cursor = db.query(
                DatabaseContract.TweetEntry.TABLE_NAME,   // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        while(cursor.moveToNext()) {
            String content = cursor.getString(
                    cursor.getColumnIndexOrThrow(DatabaseContract.TweetEntry.COLUMN_NAME_CONTENT));
            assertNotEquals(null, content);
            Log.i(TAG, "testReadTweet: " + content);
        }
        cursor.close();
    }
}