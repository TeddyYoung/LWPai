package com.yfzx.lwpai.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.core.ViewHolder;
import com.yfzx.library.tools.ToolPicture;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.library.widget.dialog.ShareDialog;
import com.yfzx.lwpai.R;

/**
 * 二维码
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年6月30日
 */
@ContentView(R.layout.activity_two_code)
public class TwoCodeActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 详情
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;
	// 头像
	@ViewInject(R.id.ivHeadPortrait)
	private ImageView ivHeadPortrait;
	// 标题
	@ViewInject(R.id.tvName)
	private TextView tvName;
	/**
	 * 名称
	 */
	private String nickname;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
		initDate();
	}

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {
	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		tvCenter.setText("我的二维码图片");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
		ivLeft.setImageResource(R.drawable.top_back_black);
		ivRight.setImageResource(R.drawable.personal_28);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String faceImage = bundle.getString("faceImage");
			nickname = bundle.getString("nickname");
			// 头像
			ImageLoaderUtil.getRoundImage(faceImage, ivHeadPortrait);
			// 名称
			tvName.setText(nickname);
		}
	}

	/**
	 * 点击事件
	 * 
	 * @author: yizhong.xu
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.ivRight })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.ivRight:
			// 分享
			final String url = "http://www.lwpai.com/weixin/";
			String titleString = "我的二维码图片";
			ShareDialog.getInstance().show(act, url, titleString, nickname,
					ToolPicture.takeScreenShot(act));

			break;
		default:
			break;
		}
	}
}
