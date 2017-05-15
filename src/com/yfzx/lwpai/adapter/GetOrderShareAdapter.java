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
import com.yfzx.lwpai.entity.GetOrderShareEntity.ResultEntity;


/**
 * 晒单分享界面
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月16日
 */
public class GetOrderShareAdapter extends BaseListAdapter<ResultEntity> {

	public GetOrderShareAdapter(Activity context, List<ResultEntity> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_bill_share, null);
		}
		ImageView ivFaceImage = ViewHolder.get(convertView, R.id.ivFaceImage);//中拍者头像
		TextView tvUserName = ViewHolder.get(convertView, R.id.tvUserName);//中拍者账号
		//TextView tvLuckyProvince =  ViewHolder.get(convertView, R.id.tvLuckyProvince);//中拍者地址
		TextView tvTime = ViewHolder.get(convertView, R.id.tvTime);//中拍時間
		TextView tvTitle = ViewHolder.get(convertView, R.id.tvTitle);//感言标题
		TextView tvContent = ViewHolder.get(convertView, R.id.tvContent);//獲獎感言
		ImageView ivgoodsImage = ViewHolder.get(convertView, R.id.ivgoodsImage);//商品图片
		
		ResultEntity date = list.get(position);
		// tvName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
		ImageLoaderUtil.getByUrl(date.getFaceImage(), ivFaceImage);
	//	tvLuckyProvince.setText(date.getTitle());
		tvUserName.setText(date.getUserName());
		tvTime.setText(date.getCreateTime());
		tvTitle.setText(date.getTitle());
		tvContent.setText(date.getContent());
		ImageLoaderUtil.getByUrl(date.getThumbnailUrl100(),date.getThumbnailUrl440(), ivgoodsImage);
		
		
		return convertView;
	}

}
