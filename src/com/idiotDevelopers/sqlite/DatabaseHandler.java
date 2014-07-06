package com.idiotDevelopers.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// Contacts table name
	private static final String TABLE_NOTI = "noti";
	private static final String TABLE_NEWSLATTER="newslatter";

	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";
	private static final String KEY_DESC = "desc";
	private static final String KEY_LINK = "link";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_NOTI_TABLE = "CREATE TABLE " + TABLE_NOTI + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_TITLE + " TEXT,"
				+ KEY_DESC + " TEXT,"
				+ KEY_LINK + " TEXT"+ ")";
		db.execSQL(CREATE_NOTI_TABLE);
		String CREATE_NEWSLATTER_TABLE = "CREATE TABLE " + TABLE_NEWSLATTER + "("
			+ KEY_ID + " INTEGER PRIMARY KEY,"
			+ KEY_TITLE + " TEXT,"
			+ KEY_DESC + " TEXT,"
			+ KEY_LINK + " TEXT"+ ")";
		db.execSQL(CREATE_NEWSLATTER_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTI);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWSLATTER);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	void addNoti(Noti contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, contact.getTitle()); // Contact title
		values.put(KEY_DESC, contact.getDesc()); // Contact desc
		values.put(KEY_LINK, contact.getLink()); // Contact link

		// Inserting Row
		db.insert(TABLE_NOTI, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	Noti getNoti(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NOTI, new String[] { KEY_ID,
				KEY_TITLE, KEY_DESC, KEY_LINK }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Noti contact = new Noti(Integer.parseInt(cursor.getString(0)),
			cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return contact
		return contact;
	}
	
	// Getting All Contacts
	public List<Noti> getAllNoti() {
		List<Noti> contactList = new ArrayList<Noti>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_NOTI;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Noti contact = new Noti();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setTitle((cursor.getString(1)));
				contact.setDesc((cursor.getString(2)));
				contact.setLink((cursor.getString(3)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Updating single contact
	public int updateNoti(Noti contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, contact.getTitle());
		values.put(KEY_DESC, contact.getDesc());
		values.put(KEY_LINK, contact.getLink());

		// updating row
		return db.update(TABLE_NOTI, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteNoti(Noti contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NOTI, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getNotiCount() {
		String countQuery = "SELECT  * FROM " + TABLE_NOTI;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	public void addNewslatter(Newslatter contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, contact.getID());
		values.put(KEY_TITLE, contact.getTitle()); // Contact title
		values.put(KEY_DESC, contact.getDesc()); // Contact desc
		values.put(KEY_LINK, contact.getLink()); // Contact author

		// Inserting Row
		db.insert(TABLE_NEWSLATTER, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	public Newslatter getNewslatter(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NEWSLATTER, new String[] { KEY_ID,
									 KEY_TITLE, KEY_DESC, KEY_LINK }, KEY_ID + "=?",
								 new String[] { String.valueOf(id) }, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Newslatter contact = new Newslatter(Integer.parseInt(cursor.getString(0)),
									  cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return contact
		return contact;
	}

	// Getting All Contacts
	public List<Newslatter> getAllNewslatter() {
		List<Newslatter> contactList = new ArrayList<Newslatter>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_NEWSLATTER;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Newslatter contact = new Newslatter();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setTitle((cursor.getString(1)));
				contact.setDesc((cursor.getString(2)));
				contact.setLink((cursor.getString(3)));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Updating single contact
	public int updateNewslatter(Newslatter contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, contact.getTitle());
		values.put(KEY_DESC, contact.getDesc());
		values.put(KEY_LINK, contact.getLink());

		// updating row
		return db.update(TABLE_NEWSLATTER, values, KEY_ID + " = ?",
						 new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteNewslatter(Newslatter contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NEWSLATTER, KEY_ID + " = ?",
				  new String[] { String.valueOf(contact.getID()) });
		db.close();
	}


	// Getting contacts Count
	public int getNewslatterCount() {
		String countQuery = "SELECT  * FROM " + TABLE_NEWSLATTER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int re =cursor.getCount();
		cursor.close();
		
		// return count
		return re;
	}
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}
}
