package com.yfzx.lwpai.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {

	public MyViewPager(Context context) {
		super(context);
	}

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);
		// 这句话就是解决问题的关键。意思是自己处理触摸事件不需要父布局（listview）的相同事件覆盖
		return super.dispatchTouchEvent(ev);
	}
}