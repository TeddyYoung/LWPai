package com.yfzx.library.core;

import com.yfzx.lwpai.MApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFragment extends Fragment {
	protected FragmentActivity act;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.act = (FragmentActivity) activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public String getClassName() {
		return this.getClass().getSimpleName();
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
