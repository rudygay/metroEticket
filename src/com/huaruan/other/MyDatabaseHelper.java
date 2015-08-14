package com.huaruan.other;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	final String CREATE_TABLE_SQL = 
			"create table e_ticket(_id integer primary key autoincrement,str VARCHAR(30),end VARCHAR(30))";
	public MyDatabaseHelper(Context context,String name,int version){
		super(context, name, null,version);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
