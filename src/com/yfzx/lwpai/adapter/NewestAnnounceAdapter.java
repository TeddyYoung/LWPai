package com.yfzx.lwpai.adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.yfzx.library.core.BaseListAdapter;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.MApplication;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.NewestResult;
import com.yfzx.lwpai.util.ScreenHelper;

/**
 * 最新揭晓幸运拍界面适配器
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月29日
 */
public class NewestAnnounceAdapter extends BaseListAdapter<NewestResult> {


	public NewestAnnounceAdapter(Activity context, List<NewestResult> list) {
		super(context, list);
	}

	@Override
	public View bindView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_goods_luck_auction, null);
		}
		TextView tvWinningName = ViewHolder.get(convertView, R.id.tvWinningName);//商品名
		ImageView ivPhoto = ViewHolder.get(convertView, R.id.ivGoodsPrint);//商品图片
		TextView tvName = ViewHolder.get(convertView, R.id.tvNumber);//中拍者
		NewestResult date = list.get(position);
		// tvName.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
		tvName.setText(date.getWinnerName());
		tvWinningName.setText("[第"+date.getPeriodIndex()+"期]"+date.getProductName());
		LinearLayout.LayoutParams lParams = (android.widget.LinearLayout.LayoutParams) ivPhoto
				.getLayoutParams();
		ivPhoto.getLayoutParams();
		// 获取屏幕的宽
		lParams.width = (MApplication.self.getWidth())/ 2-80;
		lParams.height = lParams.width;
		ivPhoto.setLayoutParams(lParams);
		
		ImageLoaderUtil.getByUrl(date.getThumbnailUrl100(),date.getThumbnailUrl440(), ivPhoto);
		return convertView;
	}
}
