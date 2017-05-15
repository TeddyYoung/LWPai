package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.adapter.RecordAdapter.RecordItem;
import com.yfzx.lwpai.entity.DetailsPriceResult;
import com.yfzx.lwpai.entity.DetailsTop3000ResultEntity;
import com.yfzx.lwpai.entity.DidResultEntity;

/**
 * 出价记录
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月23日
 */
public class RecordAdapter extends BaseListAdapter<RecordItem> {

	private int type;

	public RecordAdapter(Activity context, List<RecordItem> list, int type) {
		super(context, list);
		this.type = type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_bid_record, null);
		}

		ImageView ivFaceImage = ViewHolder.get(convertView, R.id.ivFaceImage);// 中拍者头像
		TextView tvUserName = ViewHolder.get(convertView, R.id.tvUserName);// 中拍者账号
		TextView tvSite = ViewHolder.get(convertView, R.id.tvSite);// 中拍者地址
		TextView tvTime = ViewHolder.get(convertView, R.id.tvTime);// 中拍時間
		TextView tvGroup = ViewHolder.get(convertView, R.id.tvGroup);// 组数
		// 幸运码
		TextView tvTitle = ViewHolder.get(convertView, R.id.tvTitle);

		RecordItem item = list.get(position);
		switch (item.recordType) {
		case RecordItem.TYPE_BIG_RECORD:
			DidResultEntity bigData = item.bigRecord;
			if (bigData != null) {
				tvTitle.setText("参拍组数：");
				tvTime.setVisibility(View.VISIBLE);
				// 头像
				ImageLoaderUtil.getByUrl(bigData.getFaceImage(), ivFaceImage);
				// 用户名
				tvUserName.setText(bigData.getUserName());
				if (type == 3) {
					tvGroup.setText(bigData.getOnePurchaseCount() + "组");
					tvTime.setText(bigData.getOnePurchaseTime());
					tvSite.setText("");
				} else {
					tvGroup.setText(bigData.getOfferNum() + "组");
					tvTime.setText(bigData.getOfferTime());
					tvSite.setText("(" + bigData.getLuckyProvince()
							+ bigData.getLuckyCity() + ")");
				}
			}
			break;
		case RecordItem.TYPE_WIN_RECORD:
			DetailsPriceResult winData = item.winRecord;
			if (winData != null) {
				tvTitle.setText("参拍组数：");
				tvTime.setVisibility(View.VISIBLE);
				// 头像
				ImageLoaderUtil.getByUrl(winData.getFaceImage(), ivFaceImage);
				// 用户名
				tvUserName.setText(winData.getUserName());
				if (type == 3) {
					tvGroup.setText(winData.getOnePurchaseCount() + "组");
					tvTime.setText(winData.getOnePurchaseTime());
					tvSite.setText("");
				} else {
					tvGroup.setText(winData.getOfferNum() + "组");
					tvTime.setText(winData.getOfferTime());
					tvSite.setText("(" + winData.getLuckyProvince()
							+ winData.getLuckyCity() + ")");
				}
			}
			break;
		case RecordItem.TYPE_TOP_RECORD:
			DetailsTop3000ResultEntity topData = item.topRecord;
			if (topData != null) {
				tvTitle.setText("幸运码：");
				ImageLoaderUtil.getByUrl(topData.getFaceImage(), ivFaceImage);
				tvUserName.setText(topData.getUserName());
				tvTime.setVisibility(View.GONE);
				tvGroup.setText(topData.getLotteryNumbers());
				tvSite.setText("(" + topData.getLuckyProvince()
						+ topData.getLuckyCity() + ")");
			}
			break;
		default:
			break;
		}
		return convertView;
	}

	public static class RecordItem {
		public static final int TYPE_BIG_RECORD = 0;
		public static final int TYPE_WIN_RECORD = 1;
		public static final int TYPE_TOP_RECORD = 2;
		public int recordType = TYPE_BIG_RECORD;
		public DidResultEntity bigRecord;
		public DetailsPriceResult winRecord;
		public DetailsTop3000ResultEntity topRecord;

		public RecordItem(int recordType) {
			this.recordType = recordType;
		}
	}
}
