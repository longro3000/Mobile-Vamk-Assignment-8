package com.example.assignment_8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    public static final String KEY_ROW_ID = "id";
    public static final String KEY_LAST_NAME = "last";
    public static final String KEY_FIRST_NAME = "first";
    public static final String KEY_EDUCATION = "education";
    public static final String KEY_HOBBIES = "hobbies";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_DATE = "date";

    private static final String TAG = "DBAdapter";

    private static final String DATABASE_NAME = "phoneContactDB.db";
    private static final String TABLE_NAME = "Contacts";

    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CREATE = "create table " +
                                                TABLE_NAME +
                                                " (" + KEY_ROW_ID + " integer primary key, "
                                                    + KEY_FIRST_NAME + " text not null, "
                                                    + KEY_LAST_NAME + " text not null, "
                                                    + KEY_PHONE + " text not null, "
                                                    + KEY_EDUCATION + " text not null, "
                                                    + KEY_HOBBIES + " text not null, "
                                                    + KEY_DATE + " text not null)";

    private static final String TABLE_DELETE = "drop table if exists " + TABLE_NAME;

    private Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlLiteDb;

    public DBAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(context);

    }

    // Here we define the DatabaseHelper class
    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(TABLE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ". All old data will be deleted! ");

// Here we remove the table
            db.execSQL(TABLE_DELETE);

// Here we create the table again
            onCreate(db);

        }
    }

    // This method will open the database
    public DBAdapter open() {
        sqlLiteDb = dbHelper.getWritableDatabase();
        return this;
    }

    // This method will close the database
    public void close() {
        dbHelper.close();
    }

    // Here we add a customer to the database
    public long addContact(int id, String firstName, String lastName, String phone, String education, String hobbies, String date) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROW_ID, id);
        initialValues.put(KEY_FIRST_NAME, firstName);
        initialValues.put(KEY_LAST_NAME, lastName);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_EDUCATION, education);
        initialValues.put(KEY_HOBBIES, hobbies);
        initialValues.put(KEY_DATE, date);

        return sqlLiteDb.insert(TABLE_NAME, null, initialValues);
    }

    public boolean deleteContact(long rowID) {

        return (sqlLiteDb.delete(TABLE_NAME, KEY_ROW_ID + "=" + rowID, null) > 0);
    }

    // This method will retrieve all customers
    public Cursor getAllContacts() {

        return sqlLiteDb.query(TABLE_NAME, new String[] { KEY_ROW_ID, KEY_LAST_NAME, KEY_FIRST_NAME, KEY_PHONE, KEY_EDUCATION, KEY_HOBBIES, KEY_DATE}, null, null, null, null, null);
    }

// This method will retrieve a particular customer

    public Cursor getContact(long rowID) {

        Cursor cursor = sqlLiteDb.query(true, TABLE_NAME, null/*new String[] {
                        KEY_ROW_ID, KEY_LAST_NAME, KEY_FIRST_NAME, KEY_PHONE, KEY_EDUCATION, KEY_HOBBIES, KEY_DATE }*/, KEY_ROW_ID + "=" + rowID,
                null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public Cursor getContactByLastName(String lastName) {
        String[] query = new String[1];
        query[0] = lastName;
        Cursor cursor = sqlLiteDb.query(true, TABLE_NAME, new String[] {
                        KEY_ROW_ID, KEY_LAST_NAME, KEY_FIRST_NAME, KEY_PHONE, KEY_EDUCATION, KEY_HOBBIES, KEY_DATE }, KEY_LAST_NAME+"=?",
                query, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    // This method will update a customer
    public boolean updateContact(long rowID, String firstName, String lastName, String phone, String education, String hobbies, String date) {

        ContentValues values = new ContentValues();
        values.put(KEY_FIRST_NAME, firstName);
        values.put(KEY_LAST_NAME, lastName);
        values.put(KEY_PHONE, phone);
        values.put(KEY_EDUCATION, education);
        values.put(KEY_HOBBIES, hobbies);
        values.put(KEY_DATE, date);

        return (sqlLiteDb.update(TABLE_NAME, values, KEY_ROW_ID + "=" + rowID,
                null) > 0);
    }

}
