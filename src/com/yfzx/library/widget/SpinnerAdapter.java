package com.yfzx.library.widget;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.yfzx.library.data.widget.Option;

/**
 * 下拉框适配器
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-4
 */
public class SpinnerAdapter extends ArrayAdapter<Option> {

	public SpinnerAdapter(Context context, int resource, List<Option> objects) {
		super(context, resource, objects);
	}

	public SpinnerAdapter(Context context, int resource,
			int textViewResourceId, List<Option> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	public SpinnerAdapter(Context context, int resource, Option[] objects) {
		super(context, resource, objects);
	}

	public SpinnerAdapter(Context context, int resource,
			int textViewResourceId, Option[] objects) {
		super(context, resource, textViewResourceId, objects);
	}
}
