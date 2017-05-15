package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xAllResopnse;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.ChooseAddressAdapter;
import com.yfzx.lwpai.entity.ChooseAddressKeyValue;
import com.yfzx.lwpai.entity.ProvinceCity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 选择省
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-15
 */
@ContentView(R.layout.activity_chooseaddress)
public class ChooseProvinceActivity extends BaseActivity {

	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	@ViewInject(R.id.lvChooseAddress)
	private ListView lvChooseAddress;

	private ChooseAddressAdapter chooseAddressAdapter;
	protected List<ChooseAddressKeyValue> keys;
	private String addressKey;
	private String addressDetails = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 */
	private void initWidget() {
		tvCenter.setText("收货地址管理");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 */
	private void initDate() {
		chooseAddressAdapter = new ChooseAddressAdapter(act,
				new ArrayList<ChooseAddressKeyValue>());
		lvChooseAddress.setAdapter(chooseAddressAdapter);

		getProvince();
	}

	/**
	 * 选择省份
	 * 
	 * @author: bangwei.yang
	 */
	private void getProvince() {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/region/getregionlist");// 方法
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				ProvinceCity provinceCity = JsonUtil.parseObject(act,
						responseInfo.result, ProvinceCity.class);
				String result = provinceCity.getResult();
				try {
					JSONObject jsonObject = new JSONObject(result);
					result = jsonObject.getString("KeyValuePair`2");
					LogUtils.d(result);
					keys = JSON.parseArray(result, ChooseAddressKeyValue.class);
					chooseAddressAdapter = new ChooseAddressAdapter(act, keys);
					lvChooseAddress.setAdapter(chooseAddressAdapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 选择城市
	 * 
	 * @author: bangwei.yang
	 */
	private void getAfter(String key) {
		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/region/GetRegions/" + key);// 方法
		httpClient.post(new xAllResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				ProvinceCity provinceCity = JsonUtil.parseObject(act,
						responseInfo.result, ProvinceCity.class);
				if (provinceCity != null) {
					String result = provinceCity.getResult();
					try {
						JSONObject jsonObject = new JSONObject(result);
						result = jsonObject.getString("KeyValuePair`2");
						keys = JSON.parseArray(result,
								ChooseAddressKeyValue.class);
						chooseAddressAdapter.setList(keys);
						lvChooseAddress.setAdapter(chooseAddressAdapter);
						chooseAddressAdapter.notifyDataSetChanged();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					Intent intent = new Intent();
					Bundle extras = new Bundle();
					extras.putString("addressKey", addressKey);
					extras.putString("addressDetails", addressDetails);
					intent.putExtras(extras);
					act.setResult(RESULT_OK, intent);
					finish();
				}
			}

			@Override
			public void onStart() {
				ProgressHelper.getInstance().show(act, true);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				ProgressHelper.getInstance().cancel();
				Intent intent = new Intent();
				Bundle extras = new Bundle();
				extras.putString("addressKey", addressKey);
				extras.putString("addressDetails", addressDetails);
				intent.putExtras(extras);
				act.setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param view
	 */
	@OnClick({ R.id.ivLeft })
	public void OnClick(View view) {
		switch (view.getId()) {
		case R.id.ivLeft:

			finish();
			break;
		default:
			break;
		}
	}

	/**
	 * listView点击事件
	 * 
	 * @author: bangwei.yang
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@OnItemClick({ R.id.lvChooseAddress })
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		addressKey = keys.get(position).getKey();
		addressDetails = addressDetails + keys.get(position).getValue();
		getAfter(addressKey);
	}

}
