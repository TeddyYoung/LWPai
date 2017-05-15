package com.yfzx.library.data.message;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.yfzx.library.core.SimpleXmlAccessor;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.MApplication;

public class CacheManage{

	private static final String NAME = "Cache";
	public static SimpleXmlAccessor accessor;
	
	public static SimpleXmlAccessor getAccessor(){
		if(accessor == null){
			accessor = new SimpleXmlAccessor(MApplication.self, NAME, Context.MODE_APPEND);
		}
		return accessor;
	}
	
	public static <T> void put(String key,List<T> datalist){
		
		if(datalist == null || datalist.size() == 0){
			return;
		}
		List<T> list = new ArrayList<T>();
		list.addAll(datalist);
		if(list.size()>10){
			for(int i = list.size()-1;i>=10;i--){
				list.remove(i);
			}
		}
		String json = JSON.toJSONString(list);
		if(json != null && !json.equals("")){
			getAccessor().remove(key);
		}
		getAccessor().putString(key, json);
	}
	
	public static <T> List<T> get(String key,Class<T> cls){
		List<T> list = null;
		String tmp = getAccessor().getString(key);
		if(tmp == null || tmp.equals("")){
			return new ArrayList<T>();
		}
		list = JSON.parseArray(tmp, cls);
		if(list == null){
			return new ArrayList<T>();
		}
		return list;
	}
	
	public static void remove(String key){
		getAccessor().remove(key);
		
	}
	
	public static void removeAll(){
		getAccessor().removeAll();
	}
	
	

}
