package com.yfzx.library.core;

import com.yfzx.lwpai.MApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

	protected Activity act = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	public String getClassName() {
		String className = this.getClass().getSimpleName();
		return className;
	}
	
    public void skipActivity(Activity act, Class<?> cls) {
        showActivity(act, cls);
        act.finish();
    }

    public void skipActivity(Activity act, Intent it) {
        showActivity(act, it);
        act.finish();
    }

    public void skipActivity(Activity act, Class<?> cls, Bundle extras) {
        showActivity(act, cls, extras);
        act.finish();
    }

    public void showActivity(Activity act, Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(act, cls);
        act.startActivity(intent);
    }

    public void showActivity(Activity act, Intent it) {
        act.startActivity(it);
    }

    public void showActivity(Activity act, Class<?> cls, Bundle extras) {
        Intent intent = new Intent();
        intent.putExtras(extras);
        intent.setClass(act, cls);
        act.startActivity(intent);
    }

}
