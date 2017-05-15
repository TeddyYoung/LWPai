package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.OrderShareResult;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 最新界面适配器
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015年6月27日
 */
public class ShareNewestAdapter extends BaseListAdapter<OrderShareResult> {
	private Activity act;

	public ShareNewestAdapter(Activity context, List<OrderShareResult> list) {
		super(context, list);
		this.act = context;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		Log.i("zzposition", position + "");
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_share, null);
		}
		// 头像
		ImageView ivHead = ViewHolder.get(convertView, R.id.ivHead);
		// 电话号码
		TextView tvPhone = ViewHolder.get(convertView, R.id.tvPhone);
		final TextView tvPraise = ViewHolder.get(convertView, R.id.tvPraise);
		ImageView ivWinImg = ViewHolder.get(convertView, R.id.ivWinImg);
		TextView tvWinTitle = ViewHolder.get(convertView, R.id.tvWinTitle);
		TextView tvWinSpeak = ViewHolder.get(convertView, R.id.tvWinSpeak);
		LinearLayout llytPraise = ViewHolder.get(convertView, R.id.llytPraise);
		TextView tvDate = ViewHolder.get(convertView, R.id.tvDate);
		final OrderShareResult orderShare = list.get(position);
		ImageLoaderUtil.getByUrl(orderShare.getFaceImage(), ivHead);
		tvPhone.setText(orderShare.getUserName());
		tvPraise.setText(orderShare.getZan() + "");
		ImageLoaderUtil.getByUrl(orderShare.getImageUrl1(), ivWinImg);
		tvWinTitle.setText(orderShare.getTitle());
		tvWinSpeak.setText(orderShare.getContent());
		tvDate.setText(orderShare.getCreateTime());
		llytPraise.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				OrderShareZan(position, tvPraise, orderShare);
			}
		});
		return convertView;
	}

	/**
	 * 点赞
	 * 
	 * @author: songbing.zhou
	 */
	public void OrderShareZan(int position, final TextView tvPraise,
			final OrderShareResult orderShare) {

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/OrderShare/OrderShareZan");// 点赞
		httpClient.url.append("/" + list.get(position).getId());// 分享ID
		Log.i("zzposition1", position + "");
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {
						orderShare.setZan(orderShare.getZan() + 1);
						tvPraise.setText(orderShare.getZan() + "");
					}
					ToolToast.showShort(response.getMessage());
				}
			}
		});

	}

}
