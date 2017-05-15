package com.yfzx.lwpai.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库帮助类
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-6
 */
public class SQLiteHelper extends SQLiteOpenHelper  {

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public SQLiteHelper(Context context) {
		super(context, "temp", null, 1);
	}

	/**
	 *创建表
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table GoodsID(id integer primary key autoincrement, time varchar(50),goodid varchar(50))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
