package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.Address;
import com.yfzx.lwpai.util.ValidateHelper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 添加收货地址
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@ContentView(R.layout.activity_add_address)
public class AddAddressActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 收货人
	@ViewInject(R.id.etConsignee)
	private EditText etConsignee;
	// 联系方式
	@ViewInject(R.id.etAddAddressphone)
	private EditText etPhone;
	// 选择省市区
	@ViewInject(R.id.tvAddAddressChoose)
	private TextView tvChoose;
	// 详细地址
	@ViewInject(R.id.etAddAddressDetailSite)
	private EditText etDetailSite;
	// 是否设为默认
	@ViewInject(R.id.cbAddAddress)
	private CheckBox checkBox;
	// 保存
	@ViewInject(R.id.btnAddAddressSave)
	private Button btnSave;

	// 存放地址信息
	private Address address;
	// id
	private String addressKey;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("添加实物收货地址");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {

		// 为编辑收货地址状态
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			address = (Address) bundle.getSerializable("address");
			etConsignee.setText(address.getShipTo());
			etPhone.setText(address.getCellPhone());
			etDetailSite.setText(address.getAddress());
			tvChoose.setText(address.getRegionName().replace(" ", ""));
			addressKey = address.getRegionId();

			if (address.getAddressDefault().equals("True")) {
				checkBox.setChecked(true);
			} else {
				checkBox.setChecked(false);
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case 1:// 返回省市区
				if (data != null) {
					Bundle extras = data.getExtras();
					if (extras != null) {
						addressKey = extras.getString("addressKey");
						String addressDetails = extras
								.getString("addressDetails");
						tvChoose.setText(addressDetails);
					}
				}
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 保存收货地址
	 */
	private void saveAddress() {
		String consignee = etConsignee.getText().toString();
		String phone = etPhone.getText().toString();
		String detailaddress = etDetailSite.getText().toString();
		boolean isdefault = checkBox.isChecked();
		String IsDefault = "0";
		if (isdefault) {
			IsDefault = "1";
		}

		if (TextUtils.isEmpty(consignee)) {
			ToolToast.showShort("请填写收货人");
			return;
		}
		if (TextUtils.isEmpty(phone) || !ValidateHelper.isMobilePhone(phone)) {
			ToolToast.showShort("请填写手机号码");
			return;
		}
		if (TextUtils.isEmpty(addressKey)) {
			ToolToast.showShort("请填写省市区");
			return;
		}
		if (detailaddress.trim().length() < 3
				|| detailaddress.trim().length() > 60) {
			ToolToast.showShort("详细地址长度在3-60个字之间");
			return;
		}

		// http访问获取地址
		xHttpClient httpClient = new xHttpClient(act);
		if (address != null) {// 编辑功能
			httpClient.url.append("api/members/EditAddress/");
		} else {// 添加功能
			httpClient.url.append("api/members/AddAddress/");
		}
		httpClient.url.append(consignee + "/");// 发货人
		httpClient.url.append(detailaddress + "/");// 详细地址
		httpClient.url.append(addressKey + "/");// 地址
		httpClient.url.append(phone + "/");// 电话
		httpClient.url.append(IsDefault);// 是否默认
		if (address != null) {// 编辑功能
			httpClient.url.append("/" + address.getId());
		}
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					ToolToast.showShort(response.getMessage());
					finish();
				} else {
					ToolToast.showShort(response.getMessage());
				}
			}
		});
	}

	/**
	 * 点击事件
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.tvAddAddressChoose, R.id.btnAddAddressSave })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.tvAddAddressChoose:// 选择省市区
			Intent intent = new Intent();
			intent.setClass(act, ChooseProvinceActivity.class);
			startActivityForResult(intent, 1);
			break;
		case R.id.btnAddAddressSave:// 保存
			saveAddress();
		default:
			break;
		}
	}

}
