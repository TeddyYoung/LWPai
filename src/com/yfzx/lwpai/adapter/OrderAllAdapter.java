package com.yfzx.lwpai.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.library.widget.dialog.CustomDialog;
import com.yfzx.lwpai.MApplication;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.AddAddressActivity;
import com.yfzx.lwpai.activity.GoodsLuckDetailActivity;
import com.yfzx.lwpai.activity.HomeBillShareDetailsActivity;
import com.yfzx.lwpai.activity.MineLogisticsActivity;
import com.yfzx.lwpai.activity.MineOrderShareActivity;
import com.yfzx.lwpai.entity.Address;
import com.yfzx.lwpai.entity.MyAddress;
import com.yfzx.lwpai.entity.OrderOperationEntity;
import com.yfzx.lwpai.entity.UserOrderResult;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 全部订单界面适配器
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015年6月27日
 */
public class OrderAllAdapter extends BaseListAdapter<UserOrderResult> {

	private Activity act;
	private UserOrderResult userOrder;
	/**
	 * 验证码输入框
	 */
	private EditText edtTxtRegCode;

	public OrderAllAdapter(Activity context, List<UserOrderResult> list) {
		super(context, list);
		act = context;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_order_all, null);
		}
		// 订单号
		TextView tvOrderId = ViewHolder.get(convertView, R.id.tvOrderId);
		// 订单状态
		TextView tvOrderState = ViewHolder.get(convertView, R.id.tvOrderState);
		// 图片
		ImageView ivOrderHead = ViewHolder.get(convertView, R.id.ivOrderHead);
		// 订单名称
		TextView tvRedEnvelope = ViewHolder
				.get(convertView, R.id.tvRedEnvelope);
		// 订单时间
		TextView tvOrderDate = ViewHolder.get(convertView, R.id.tvOrderDate);
		// 添加收货
		TextView tvAddress = ViewHolder.get(convertView, R.id.tvAddress);
		// 确认订单
		final TextView tvDetermine = ViewHolder.get(convertView,
				R.id.tvDetermine);
		// 首次中拍
		final TextView tvFirstShot = ViewHolder.get(convertView,
				R.id.tvFirstShot);
		// 待晒单
		final TextView tvWaitShare = ViewHolder.get(convertView,
				R.id.tvWaitShare);
		// 查看物流
		TextView tvLogistics = ViewHolder.get(convertView, R.id.tvLogistics);
		// 布局物流晒单
		RelativeLayout rlytWL = ViewHolder.get(convertView, R.id.rlytWL);
		// 布局添加地址
		RelativeLayout rlytAdd = ViewHolder.get(convertView, R.id.rlytAdd);
		// 布局确认订单
		RelativeLayout rlytDetermine = ViewHolder.get(convertView,
				R.id.rlytDetermine);
		// 布局首次中拍
		RelativeLayout rlytFirstShot = ViewHolder.get(convertView,
				R.id.rlytFirstShot);

		userOrder = list.get(position);

		switch (Integer.parseInt(userOrder.getOrderStatus())) {
		case 2:// （已付款,等待发货）不需要操作
			rlytWL.setVisibility(View.GONE);
			rlytAdd.setVisibility(View.GONE);
			rlytFirstShot.setVisibility(View.GONE);
			rlytDetermine.setVisibility(View.GONE);
			break;
		case 3:// 待收货：可以“确定订单”，“查看物流”
			rlytWL.setVisibility(View.VISIBLE);
			rlytDetermine.setVisibility(View.VISIBLE);
			tvWaitShare.setVisibility(View.GONE);
			rlytFirstShot.setVisibility(View.GONE);
			break;
		case 4:// 无效订单
			rlytWL.setVisibility(View.GONE);
			rlytAdd.setVisibility(View.GONE);
			rlytDetermine.setVisibility(View.GONE);
			rlytFirstShot.setVisibility(View.GONE);
			break;
		case 5:// 订单完成：可以“查看物流”，当OrderShareId=0时“分享晒单”，OrderShareId>0时显示晒单详情
			rlytWL.setVisibility(View.VISIBLE);
			if (userOrder.getOrderShareId().equals("0")) {
				tvWaitShare.setText("分享晒单");
			} else {
				tvWaitShare.setText("晒单详情");
			}
			rlytDetermine.setVisibility(View.GONE);
			rlytFirstShot.setVisibility(View.GONE);
			break;
		/*
		 * 无收货地址：显示“选择收货地址”，调用用户的收货地址信息， 如果没有则提供跳转到收货地址的按钮，
		 * 如果有收货地址则使用接口把地址Id当成phoneCode传递，
		 */
		case 14:
			rlytAdd.setVisibility(View.VISIBLE);
			rlytDetermine.setVisibility(View.GONE);
			rlytWL.setVisibility(View.GONE);
			rlytFirstShot.setVisibility(View.GONE);
			break;
		case 15:// 15首次中拍：需填写验证码
			// 物流和分享
			rlytWL.setVisibility(View.GONE);
			// 地址
			rlytAdd.setVisibility(View.GONE);
			// 确定按钮
			rlytDetermine.setVisibility(View.GONE);
			// 首次中拍
			rlytFirstShot.setVisibility(View.VISIBLE);
			break;

		}
		// 订单号
		tvOrderId.setText(userOrder.getOrderId());
		// 订单状态
		tvOrderState.setText(userOrder.getOrderStatusName());
		// 图片
		ImageLoaderUtil.getByUrl(userOrder.getThumbnailUrl100(),
				userOrder.getThumbnailUrl440(), ivOrderHead);
		//
		String categoryName = userOrder.getCategoryName();
		String productName = userOrder.getProductName();
		tvRedEnvelope.setText(Html.fromHtml("<font color='"
				+ act.getResources().getColor(R.color.btn_orange) + "'>"
				+ categoryName + "-</font>" + productName));
		tvOrderDate.setText("订单日期：" + userOrder.getOrderDate());

		// 确认订单
		tvDetermine.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GetUserAddressList(list.get(position).getOrderId(), 3);
			}
		});

		// 首次中拍
		tvFirstShot.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GetUserAddressList(list.get(position).getOrderId(), 15);
			}
		});

		// 查看物流
		tvLogistics.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(act, MineLogisticsActivity.class);
				Bundle bundle = new Bundle();
				// 订单号
				bundle.putString("orderId", list.get(position).getOrderId());
				intent.putExtras(bundle);
				act.startActivity(intent);
			}
		});
		// 分享晒单
		tvWaitShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (tvWaitShare.getText().toString().equals("分享晒单")) {
					Intent intent = new Intent(act,
							MineOrderShareActivity.class);
					Bundle bundle = new Bundle();
					// 图片
					bundle.putString("thumbnailUrl100", list.get(position)
							.getThumbnailUrl100());
					bundle.putString("thumbnailUrl440", list.get(position)
							.getThumbnailUrl440());
					// 产品
					bundle.putString("productName", list.get(position)
							.getProductName());
					// 订单时间
					bundle.putString("orderDate", list.get(position)
							.getOrderDate());
					// 订单号
					bundle.putString("orderId", list.get(position).getOrderId());
					intent.putExtras(bundle);
					act.startActivity(intent);
				} else {
					Intent intent = new Intent(act,
							HomeBillShareDetailsActivity.class);
					Bundle bundle = new Bundle();
					bundle.putString("id", list.get(position)
							.getOrderTypeProductId() + "");
					intent.putExtras(bundle);
					act.startActivity(intent);
				}

			}
		});
		// 添加收货
		tvAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GetUserAddressList(list.get(position).getOrderId(), 14);
			}
		});
		// 布局
		LinearLayout llytOrder = ViewHolder.get(convertView, R.id.llytOrder);
		llytOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int type;
				if (list.get(position).getCategoryName().equals("幸运拍")) {
					type = 55;

				} else if (list.get(position).getCategoryName().equals("红包区")) {
					type = 56;
				} else {
					type = 3;
				}
				Intent intent = new Intent(act, GoodsLuckDetailActivity.class);
				Bundle bundle = new Bundle();
				// 状态 0进行 1即将开始 2结束
				bundle.putInt("category", 2);
				// 区分幸运拍 红包区一元拍
				bundle.putInt("type", type);
				// 商品ID
				bundle.putString("goodsId", list.get(position)
						.getOrderTypeProductId() + "");
				if (type == 3) {
					bundle.putString("goodsId", list.get(position)
							.getOrderTypeProductId() + "");
					// 0 暂时代替一元拍ptid
					bundle.putString("goodsPTId", "0");
				}
				intent.putExtras(bundle);
				act.startActivityForResult(intent, 0);

			}
		});

		return convertView;
	}

	/**
	 * 获取收货地址的信息
	 * 
	 * @author: songbing.zhou
	 * @return
	 */
	private void GetUserAddressList(final String orderId, final int orderStatus) {

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserAddressList");// 方法
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				MyAddress response = JsonUtil.parseObject(act,
						responseInfo.result, MyAddress.class);
				if (response != null) {
					List<Address> addresses = response.getResult();
					switch (orderStatus) {
					// 3待收货：可以“确定订单”，“查看物流”
					case 3: {
						for (Address address : addresses) {
							if (address.getAddressDefault().equals("True")) {
								OrderOperation(orderId, 3, address.getId());
							}
						}
					}
						break;
					/*
					 * 无收货地址：显示“选择收货地址”，调用用户的收货地址信息， 如果没有则提供跳转到收货地址的按钮，
					 * 如果有收货地址则使用接口把地址Id当成phoneCode传递， 15首次中拍：需填写验证码
					 */
					case 14: {
						CustomDialog mCustomDialog = new CustomDialog(act,
								R.layout.dialog_address, MApplication.self
										.getWidth() - 50,
								ViewGroup.LayoutParams.WRAP_CONTENT);
						// 标题
						TextView tvCenter = (TextView) mCustomDialog
								.findViewById(R.id.tvCenter);
						// 添加地址
						ListView listview = (ListView) mCustomDialog
								.findViewById(R.id.listview);
						tvCenter.setText("设置收货地址");
						if (addresses.size() != 0) {// 如果长度为0，隐藏提示框，否贼显示地址数据
							// 保留地址长度，判断是否大于3个
							AddressAdapter adapter = new AddressAdapter(act,
									addresses);
							// 订单ID
							adapter.setOrderId(orderId);
							// 判断是设置收货地址
							adapter.setFlag(1);
							OrderAllAdapter orderAllAdapter = new OrderAllAdapter(
									act, new ArrayList<UserOrderResult>());
							adapter.setOrderAllAdapter(orderAllAdapter);
							adapter.setmDialog(mCustomDialog);
							listview.setAdapter(adapter);
						}
						mCustomDialog.show();
					}
						break;
					case 15: {// 首次中拍
						final CustomDialog firstShotDialog = new CustomDialog(
								act, R.layout.dialog_firstshot,
								MApplication.self.getWidth() - 50,
								ViewGroup.LayoutParams.WRAP_CONTENT);
						// 标题
						TextView tvCenter = (TextView) firstShotDialog
								.findViewById(R.id.tvCenter);
						// 标题
						ImageView ivLeft = (ImageView) firstShotDialog
								.findViewById(R.id.ivLeft);
						// 验证码
						edtTxtRegCode = (EditText) firstShotDialog
								.findViewById(R.id.edtTxtRegCode);
						// 首次中拍确定按钮
						Button btnDetermine = (Button) firstShotDialog
								.findViewById(R.id.btnDetermine);

						tvCenter.setText("首次中拍");
						// 返回图片
						ivLeft.setImageResource(R.drawable.top_back_black);
						// 返回图片
						ivLeft.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								firstShotDialog.dismiss();
							}
						});
						// 确定按钮
						btnDetermine.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								if (TextUtils.isEmpty(edtTxtRegCode.getText()
										.toString())) {
									ToolToast.showShort("请输入验证码");
									return;
								}
								OrderOperation(orderId, 1, edtTxtRegCode
										.getText().toString());
								firstShotDialog.dismiss();
							}
						});
						firstShotDialog.show();
					}

						break;
					default:
						break;
					}
				} else {
					Intent intent = new Intent(act, AddAddressActivity.class);
					act.startActivity(intent);
				}

			}
		});
	}

	/**
	 * 
	 * 设置订单地址
	 * 
	 * @author: songbing.zhou
	 * @param orderId
	 * @param stauts
	 * @param phoneCode
	 */
	private void OrderOperation(String orderId, int stauts, String phoneCode) {
		xHttpClient httpClient = new xHttpClient(mContext);
		httpClient.url.append("api/members/OrderOperation");// 方法
		httpClient.url.append("/" + orderId);// orderId：订单编号
		httpClient.url.append("/" + stauts);// stauts：（1为首次中拍，2为确认收货地址，3完成订单
		httpClient.url.append("/" + phoneCode);// phoneCode：手机验证码/地址Id
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				OrderOperationEntity response = JsonUtil.parseObject(mContext,
						responseInfo.result, OrderOperationEntity.class);
				if (response != null) {
					ToolToast.showLong(response.getMessage());
					notifyDataSetChanged();
				}
			}
		});
	}

}
