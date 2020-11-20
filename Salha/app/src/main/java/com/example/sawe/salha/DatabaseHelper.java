package com.example.sawe.salha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "InboxManager";
    private static final String TABLE_SMSES = "smses";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NUMBER = "phone";
    private static final String KEY_MESSAGES = "message";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SMS_TABLE = "CREATE TABLE " + TABLE_SMSES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PHONE_NUMBER + " TEXT" + ")";
        db.execSQL(CREATE_SMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMSES);

        onCreate(db);
    }

    void addSMS(SMSInbox smsInbox){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, smsInbox.getName());
        values.put(KEY_PHONE_NUMBER, smsInbox.getPhone_number());
        values.put(KEY_MESSAGES, smsInbox.getMessage());

        db.insert(TABLE_SMSES, null, values);
        db.close();
    }

// This method isn't used
   /* SMSInbox getSMSes(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SMSES, new String[]{KEY_ID, KEY_NAME, KEY_PHONE_NUMBER, KEY_MESSAGES}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        SMSInbox smsInbox = new SMSInbox(Integer.parseInt(cursor.getString(cursor.getColumnIndex("zero")), cursor.getString(cursor.getColumnIndex("one")), cursor.getString(cursor.getColumnIndex("two")), cursor.getString(cursor.getColumnIndex("three")));

        return smsInbox;
    }//*/

    public List<SMSInbox> getAllSMSes(){
        List<SMSInbox> smsInboxList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_SMSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                SMSInbox smsInbox /*= null; //*/ = new SMSInbox();//0, "one", "two", "three");
                smsInbox.setId(Integer.parseInt(cursor.getString(0)));
                smsInbox.setName(cursor.getString(1));
                smsInbox.setPhone_number(cursor.getString(2));
                smsInbox.setMessage(cursor.getString(3));//*/

                //smsInbox.setId(Integer.parseInt("zero"));
                /*smsInbox.setId(0);
                smsInbox.setName("one");
                smsInbox.setPhone_number("two");
                smsInbox.setMessage("three");*/


                smsInboxList.add(smsInbox);
            } while (cursor.moveToNext());
        }
        return smsInboxList;
    }

    public int updateSMSes(SMSInbox smsInbox){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, smsInbox.getName());
        values.put(KEY_PHONE_NUMBER, smsInbox.getName());
        values.put(KEY_MESSAGES, smsInbox.getMessage());

        return db.update(TABLE_SMSES, values, KEY_ID + "=?",
                new String[]{String.valueOf(smsInbox.getId())});
    }

    public void deleteSMS(SMSInbox smsInbox){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SMSES, KEY_ID + "=?",
                new String[]{String.valueOf(smsInbox.getId())});
        db.close();
    }

    public int getSMSesCount(){
        String countQuery = "SELECT * FROM " + TABLE_SMSES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
}
