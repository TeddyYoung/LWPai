package com.yfzx.lwpai.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yfzx.lwpai.entity.GoodsIdEntity;

/**
 * 系统用户
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-6-7
 */
public class GoodsIDHelper {

	Context mContext;
	SQLiteHelper sqLiteHelper;
	SQLiteDatabase db;

	public GoodsIDHelper(Context c) {
		mContext = c;
	}

	/**
	 * 打开数据库
	 * 
	 * @author: songbing.zhou
	 */
	public void open() {
		sqLiteHelper = new SQLiteHelper(mContext);
		db = sqLiteHelper.getWritableDatabase();
	}

	/**
	 * 关闭数据库
	 * 
	 * @author: songbing.zhou
	 */
	public void close() {
		sqLiteHelper.close();
		db.close();
	}

	/**
	 * 插入
	 * 
	 * @author: songbing.zhou
	 * @param user
	 * @param pwd
	 * @param role
	 * @return
	 */
	public int insert(String time, String goodid) {
		open();
		db.execSQL("insert into GoodsID(time,goodid) values(?,?)",
				new String[] { time, goodid });
		close();
		return 1;
	}

	/**
	 * 更新
	 * 
	 * @author: songbing.zhou
	 */
	public int update(String time, String goodid) {
		open();
		db.execSQL("update GoodsID set time=? where goodid=?", new String[] {
				time, goodid });
		close();
		return 1;
	}

	/**
	 * 登录
	 * 
	 * @author: songbing.zhou
	 * @param user
	 * @param pwd
	 * @param role
	 * @return
	 */
	public int select(String time, String goodid) {
		open();
		Cursor cursor = db.rawQuery(
				"select time,goodid from GoodsID where time=? and goodid=?",
				new String[] { time, goodid });
		while (cursor.moveToNext()) {
			return 1;
		}
		close();
		return 0;
	}

	public int select(String goodid) {
		open();
		Cursor cursor = db.rawQuery(
				"select goodid from GoodsID where goodid=?",
				new String[] { goodid });
		while (cursor.moveToNext()) {
			return 1;
		}
		close();
		return 0;
	}

	/**
	 * 删除
	 * 
	 * @author: songbing.zhou
	 * @param name
	 * @return
	 */
	public int delete(String goodid) {
		open();
		db.execSQL("delete from GoodsID where goodid=?",
				new String[] { goodid });
		close();
		return 1;
	}

	/**
	 * 查询
	 * 
	 * @author: songbing.zhou
	 * @return
	 */
	public List<GoodsIdEntity> GoodsIdEntity() {
		open();
		List<GoodsIdEntity> data = new ArrayList<GoodsIdEntity>();
		Cursor cursor = db.rawQuery("select * from GoodsID", null);
		if (cursor.moveToNext()) {
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String goodid = cursor.getString(cursor.getColumnIndex("goodid"));
			data.add(new GoodsIdEntity(time, goodid));
		}
		close();
		return data;
	}

}
