package com.yfzx.lwpai.adapter;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.AddAddressActivity;
import com.yfzx.lwpai.entity.Address;
import com.yfzx.lwpai.entity.OrderOperationEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 收货地址适配器
 * 
 * @author: yizhong.xu Gy
 * @version Revision: 0.0.1 -@Date: 2015年6月30日 2015-7-8
 */
public class AddressAdapter extends BaseListAdapter<Address> {
	public AddressAdapter(Activity context, List<Address> list) {
		super(context, list);
	}

	/**
	 * 判断是设置订单收货地址界面
	 */
	private int flag;
	/**
	 * 订单ID
	 */
	private String orderId;

	/**
	 * 判断是否显示
	 */
	private Dialog mDialog;

	/**
	 * 判断是否显示
	 */
	private OrderAllAdapter orderAllAdapter;

	public OrderAllAdapter getOrderAllAdapter() {
		return orderAllAdapter;
	}

	public void setOrderAllAdapter(OrderAllAdapter orderAllAdapter) {
		this.orderAllAdapter = orderAllAdapter;
	}

	public Dialog getmDialog() {
		return mDialog;
	}

	public void setmDialog(Dialog mDialog) {
		this.mDialog = mDialog;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			// 设置订单收货地址
			if (flag == 1) {
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_address1, null);
			} else {// 我的帐号的收货地址
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.item_address, null);
			}

		}
		TextView tvName = ViewHolder.get(convertView, R.id.tvAddressName);
		TextView tvPhone = ViewHolder.get(convertView, R.id.tvAddressphone);
		TextView tvAddress = ViewHolder.get(convertView, R.id.tvAddressAddress);
		TextView tvEdit = ViewHolder.get(convertView, R.id.tvAddressEdit);
		TextView tvDelete = ViewHolder.get(convertView, R.id.tvDelete);
		RadioButton rdDefault = ViewHolder.get(convertView,
				R.id.btn_tab_bottom_square);
		LinearLayout llytSetDefault = ViewHolder.get(convertView,
				R.id.llytSetDefault);
		// 设置收货地址布局
		LinearLayout llytAddress = ViewHolder
				.get(convertView, R.id.llytAddress);

		// 获取对应的数据
		final Address address = list.get(position);

		// 设置对应的数据
		tvName.setText(address.getShipTo());
		tvAddress.setText(address.getRegionName().replace(" ", "")
				+ address.getAddress());
		String phone = address.getCellPhone();
		tvPhone.setText(phone.substring(0, 4) + "****"
				+ phone.substring(phone.length() - 4, phone.length()));

		if (flag == 1) {
			// 设置收货地址布局点击事件
			llytAddress.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					OrderOperation(getOrderId(), address.getId());
				}
			});
		} else {
			// 地址是否为默认
			if (address.getAddressDefault().equals("True")) {
				rdDefault.setChecked(true);
			} else {
				rdDefault.setChecked(false);
			}

			// 点击事件
			llytSetDefault.setOnClickListener(new OnClickListener() {// 设置默认地址
						@Override
						public void onClick(View v) {
							saveAddress(position);
						}
					});

			tvDelete.setOnClickListener(new OnClickListener() {// 删除

				@Override
				public void onClick(View v) {
					deleteAddress(position, address.getId());
				}
			});

			tvEdit.setOnClickListener(new OnClickListener() {// 点击编辑传递数据，跳转页面到添加地址

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putSerializable("address", (Serializable) address);// 地址对象
					bundle.putString("detailAddress", "");// 地址详情
					intent.putExtras(bundle);
					intent.setClass(mContext, AddAddressActivity.class);
					mContext.startActivity(intent);
				}
			});
		}

		return convertView;
	}

	/**
	 * 通过id获取对应的省市
	 * 
	 * @author: bangwei.yang
	 */
	private void deleteAddress(final int pos, String id) {
		xHttpClient httpClient = new xHttpClient(mContext);
		httpClient.url.append("api/members/DelAddress/" + id);// 方法
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(mContext,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					ToolToast.showShort(response.getMessage());
					list.remove(pos);
					notifyDataSetChanged();
				}
			}
		});
	}

	/**
	 * 保存收货地址
	 */
	private void saveAddress(final int pos) {
		xHttpClient httpClient = new xHttpClient(mContext);
		httpClient.url.append("api/members/SetDefaultAddress/");
		httpClient.url.append(list.get(pos).getId());// id
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(mContext,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					ToolToast.showShort(response.getMessage());
					for (Address address : list) {
						address.setAddressDefault("False");
					}
					list.get(pos).setAddressDefault("True");
					notifyDataSetChanged();
				}
			}
		});
	}

	/**
	 * 设置订单地址
	 * 
	 * @author: bangwei.yang
	 */
	private void OrderOperation(String orderId, String phoneCode) {
		xHttpClient httpClient = new xHttpClient(mContext);
		httpClient.url.append("api/members/OrderOperation");// 方法
		httpClient.url.append("/" + orderId);// orderId：订单编号
		httpClient.url.append("/" + 2);// stauts：（1为首次中拍，2为确认收货地址，3完成订单
		httpClient.url.append("/" + phoneCode);// phoneCode：手机验证码/地址Id
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				OrderOperationEntity response = JsonUtil.parseObject(mContext,
						responseInfo.result, OrderOperationEntity.class);
				if (response != null) {
					ToolToast.showLong(response.getMessage());
					mDialog.dismiss();
					getOrderAllAdapter().notifyDataSetChanged();
				}
			}
		});
	}
}
