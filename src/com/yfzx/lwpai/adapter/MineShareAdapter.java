package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.HomeBillShareDetailsActivity;
import com.yfzx.lwpai.activity.MineOrderShareActivity;
import com.yfzx.lwpai.entity.GetOrderShareListResult;

/**
 * 最新界面适配器
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015年6月27日
 */
public class MineShareAdapter extends BaseListAdapter<GetOrderShareListResult> {
	private Activity act;

	public MineShareAdapter(Activity context, List<GetOrderShareListResult> list) {
		super(context, list);
		this.act = context;
	}

	@Override
	public View bindView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_my_show_meun, null);
		}
		// 图片
		ImageView ivThumbnailUrl100 = ViewHolder.get(convertView,
				R.id.ivThumbnailUrl100);
		// 标题
		TextView tvProductName = ViewHolder
				.get(convertView, R.id.tvProductName);
		// 订单号
		TextView tvOrderId = ViewHolder.get(convertView, R.id.tvOrderId);
		// 时间
		TextView tvLotteryDate = ViewHolder
				.get(convertView, R.id.tvLotteryDate);
		// 查看
		TextView tvSee = ViewHolder.get(convertView, R.id.tvSee);
		// 编辑
		TextView tvEdit = ViewHolder.get(convertView, R.id.tvEdit);
		// 布局
		LinearLayout llytMineShare = ViewHolder.get(convertView,
				R.id.llytMineShare);
		final GetOrderShareListResult GetOrderShareList = list.get(position);
		ImageLoaderUtil.getByUrl(GetOrderShareList.getThumbnailUrl100(),
				ivThumbnailUrl100);
		tvProductName.setText(GetOrderShareList.getProductName());
		tvOrderId.setText(GetOrderShareList.getOrderId());
		tvLotteryDate.setText(GetOrderShareList.getLotteryDate());
		switch (GetOrderShareList.getIsAudit()) {
		case -1:// -1审核未过
			tvSee.setVisibility(View.GONE);
			tvEdit.setVisibility(View.VISIBLE);
			break;
		case 0:// 0审核中。
			tvSee.setText("审核中");
			break;
		case 1:// 1审核通过
			tvSee.setText("查看");
			break;

		default:
			break;
		}
		llytMineShare.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				switch (GetOrderShareList.getIsAudit()) {
				case -1:// -1审核未过
					intent.setClass(act, MineOrderShareActivity.class);
					bundle.putString("id", list.get(position).getId() + "");
					intent.putExtras(bundle);
					bundle.putString("thumbnailUrl100", list.get(position)
							.getThumbnailUrl100());
					// 产品
					bundle.putString("productName", list.get(position)
							.getProductName());
					// 订单时间
					bundle.putString("orderDate", list.get(position)
							.getCreateTime());
					// 订单号
					bundle.putString("orderId", list.get(position).getOrderId());
					intent.putExtras(bundle);
					act.startActivity(intent);
					break;
				case 0:// 0审核中。
					break;
				case 1:// 1审核通过
					intent.setClass(act, HomeBillShareDetailsActivity.class);
					bundle.putString("id", list.get(position).getId() + "");
					intent.putExtras(bundle);
					act.startActivityForResult(intent, 0);
					break;
				}
			}
		});
		return convertView;
	}

}
