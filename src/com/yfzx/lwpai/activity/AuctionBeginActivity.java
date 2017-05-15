package com.yfzx.lwpai.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.entity.NewestResult;
import com.yfzx.lwpai.fragment.HomeLuckAuctionFragment;
import com.yfzx.lwpai.util.Helper;

/**
 * 商品详情界面开始进行时结束
 * 
 * @author: yizhong.xu
 * @version Revision: 0.0.1
 * @Date: 2015年7月7日
 */
@ContentView(R.layout.activity_auction_begin_head)
public class AuctionBeginActivity extends BaseActivity {
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// 返回
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 首页
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;
	// // 商品图片
	@ViewInject(R.id.ivPhoto)
	private ImageView ivPhoto;
	// 商品名
	@ViewInject(R.id.tvProductName)
	private TextView tvProductName;
	// 中奖者
	@ViewInject(R.id.tvWinnerName)
	private TextView tvWinnerName;
	// 中奖码
	@ViewInject(R.id.tvWinnerNum)
	private TextView tvWinnerNum;
	// 揭晓时间
	@ViewInject(R.id.tvEndDate)
	private TextView tvEndDate;
	@ViewInject(R.id.llTime)
	private LinearLayout llTime;
	// 参与人数tvPeopleNumber
	@ViewInject(R.id.tvCurrentCount)
	private TextView tvCurrentCount;
	//
	@ViewInject(R.id.btn_Bidders)
	private TextView btn_Bidders;
	// 进度条布局
	@ViewInject(R.id.llProgressBar)
	private LinearLayout llProgressBar;

	// 头部
	private View headerView;

	// 传值
	private String goodSysId;
	private int flag = 0;
	private NewestResult goodDetailType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		ViewUtils.inject(this);

		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				// goodSysId = bundle.getString("goodflag");
				flag = bundle.getInt("category");
				goodDetailType = (NewestResult) bundle
						.getSerializable("goodDetailType");

			}
		}
		// Helper.changeFragment(this, R.id.flytContent, HomeLuckAuctionFragment
		// .newInstance(goodDetailType.getProductId()),
		// HomeLuckAuctionFragment.TAG, true);

		initWidget();
		initDate();

	}

	/**
	 * 初始化界面
	 * 
	 * @author: yizhong.xu
	 */
	private void initWidget() {
		// 头部内容
		tvCenter.setText("拍品详情");
		btn_Bidders.setText("竞拍结束");
		ivLeft.setImageResource(R.drawable.top_back_round);
		ivRight.setImageResource(R.drawable.top_home_page);

		// ImageLoaderUtil.getByUrl(goodDetailType.getThumbnailUrl100(),goodDetailType.getThumbnailUrl440(),
		// ivPhoto);
		tvProductName.setText("第" + goodDetailType.getPeriodIndex() + "期-"
				+ goodDetailType.getProductName());
		tvWinnerName.setText(goodDetailType.getWinnerName());
		tvWinnerNum.setText(goodDetailType.getWinnerNum());
		tvEndDate.setText(goodDetailType.getEndDate());
		// tvPeopleNumber.setText(goodDetailType.get);
		if (flag == 1) {
			llProgressBar.setVisibility(View.GONE);
		}
		if (flag == 2) {
			llProgressBar.setVisibility(View.GONE);
		}
		if (flag == 3) {
			llTime.setVisibility(View.GONE);
		}
		// HeadAd();// 头部广告
	}

	/**
	 * 
	 * @author: yizhong.xu
	 */
	// public void HeadAd() {
	// xHttpClient httpClient = new xHttpClient(act, false);
	// httpClient.url.append("api/Lucky/GetLuckyProductById");
	// httpClient.url.append("/" + goodDetailType.getId());
	// httpClient.post(new xResopnse() {
	// @Override
	// public void onSuccess(ResponseInfo<String> responseInfo) {
	// ProgressHelper.getInstance().cancel();
	// LuckyGetLuckyProduct response = JsonUtil.parseObject(act,
	// responseInfo.result, LuckyGetLuckyProduct.class);
	// if (response != null) {
	// SlideShowView slideShowView = (SlideShowView)
	// findViewById(R.id.slideShowView);
	// // slideShowView.init(response.getResult().getAdInfo());
	// slideShowView.inits(response.getPImgSrc());
	// }
	// }
	// });
	//
	// }

	/**
	 * 初始化数据
	 * 
	 * @author: yizhong.xu
	 */
	private void initDate() {

	}

	@OnClick({ R.id.ivLeft, R.id.ivRight, R.id.llProductDescription,
			R.id.llGoodsShare, R.id.llBygoneDays })
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.ivLeft: {
			finish();
			break;
		}
		case R.id.ivRight: {
			finish();
			((MainActivity)act).clickMenu(1);// 跳转到对应的页面
			break;
		}
		case R.id.llGoodsShare: {// 晒单分享
			if (flag == 1) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, NewestSingleShareActivity.class);
				bundle.putInt("goodsProductId", goodDetailType.getProductId());
				bundle.putInt("flag", flag);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
			if (flag == 2) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, NewestSingleShareActivity.class);
				bundle.putInt("goodsProductId", goodDetailType.getProductId());
				bundle.putInt("flag", flag);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
			if (flag == 3) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, NewestSingleShareActivity.class);
				bundle.putInt("goodsProductId", goodDetailType.getProductId());
				bundle.putInt("flag", flag);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
		}
		case R.id.llBygoneDays: {// 往期揭晓
			if (flag == 1) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act,
						NewestLaterAnnounceActivity.class);
				bundle.putInt("goodsProductId", goodDetailType.getProductId());
				bundle.putInt("flag", flag);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
			if (flag == 2) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act,
						NewestLaterAnnounceActivity.class);
				bundle.putInt("goodsProductId", goodDetailType.getProductId());
				bundle.putInt("flag", flag);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
			if (flag == 3) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act,
						NewestLaterAnnounceActivity.class);
				bundle.putInt("goodsProductId", goodDetailType.getProductId());
				bundle.putInt("flag", flag);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
		}
		case R.id.llProductDescription: {// 商品描述
			// String str=String.valueOf(flag);
			// ToolToast.showShort((str));
			// Intent intent = new Intent();
			// Bundle bundle = new Bundle();
			// intent.setClass(act, LuckyGoodDetailActivity.class);//
			// NewsDetailsActivity
			// bundle.putString("commodityid", goodDetailType.getId());
			// intent.putExtras(bundle);
			// act.startActivityForResult(intent, 0);
			if (flag == 1) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, GoodsDescriptionActivity.class);
				bundle.putString("commodityid", goodDetailType.getId());
				bundle.putInt("goodType", 55);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
			if (flag == 2) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, GoodsDescriptionActivity.class);
				bundle.putString("commodityid", goodDetailType.getId());
				bundle.putInt("goodType", 56);
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}
			if (flag == 3) {
				Bundle bundle = new Bundle();
				Intent intent = new Intent(act, GoodsDescriptionActivity.class);
				bundle.putString("commodityid",
						goodDetailType.getOnePurchasePId());
				bundle.putInt("goodType", 3);
				String f = goodDetailType.getOnePurchasePId();
				intent.putExtras(bundle);
				startActivity(intent);
				break;
			}

		}
		}
	}
}
