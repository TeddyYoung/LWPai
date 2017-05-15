package com.yfzx.lwpai.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseFragment;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolNetwork;
import com.yfzx.library.universalimageloader.ImageLoaderUtil;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.activity.AccountBalanceActivity;
import com.yfzx.lwpai.activity.AccountIntegralActivity;
import com.yfzx.lwpai.activity.AccountRedBagActivity;
import com.yfzx.lwpai.activity.AccountRemindActivity;
import com.yfzx.lwpai.activity.LoginActivity;
import com.yfzx.lwpai.activity.MineAccountDetailsActivity;
import com.yfzx.lwpai.activity.MineFeedbackActivity;
import com.yfzx.lwpai.activity.MineLPaiRecordActivity;
import com.yfzx.lwpai.activity.MineLuckyAuctionActivity;
import com.yfzx.lwpai.activity.MineMoreActivity;
import com.yfzx.lwpai.activity.MineMsgCenterActivity;
import com.yfzx.lwpai.activity.MineOrderActivity;
import com.yfzx.lwpai.activity.MineShareActivity;
import com.yfzx.lwpai.activity.MyAccountActivity;
import com.yfzx.lwpai.activity.QunWpaActivity;
import com.yfzx.lwpai.activity.RechargeActivity;
import com.yfzx.lwpai.activity.RegistActivity;
import com.yfzx.lwpai.entity.Account;
import com.yfzx.lwpai.entity.Login;
import com.yfzx.lwpai.entity.Offer;
import com.yfzx.lwpai.entity.Order;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.entity.UserInfo;
import com.yfzx.lwpai.entity.UserInfo.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 我的乐拍
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-6-26
 */
public class MineFragment extends BaseFragment {

	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;
	// Right
	@ViewInject(R.id.ivRight)
	private ImageView ivRight;
	// 我的充值
	@ViewInject(R.id.tvRecharge)
	private TextView tvRecharge;
	// 我的消息
	@ViewInject(R.id.tvNews)
	private TextView tvNews;
	// 一键加群
	@ViewInject(R.id.tvAddGroup)
	private TextView tvAddGroup;
	// 昵称
	@ViewInject(R.id.tvName)
	private TextView tvName;
	// 角色
	@ViewInject(R.id.tvRole)
	private TextView tvRole;
	// 头像
	@ViewInject(R.id.ivHead)
	private ImageView ivHead;
	// 管理收货地址
	@ViewInject(R.id.tvAddress)
	private ImageView tvAddress;
	// 未登录显示的页面
	@ViewInject(R.id.llytNoLogin)
	private LinearLayout llytNoLogin;
	// 登录显示的页面
	@ViewInject(R.id.llMyAccoun)
	private RelativeLayout llMyAccoun;
	// 余额
	@ViewInject(R.id.tvBalance)
	private TextView tvBalance;
	// 可提现
	@ViewInject(R.id.tvRemind)
	private TextView tvRemind;
	// 我的红包
	@ViewInject(R.id.tvRedBag)
	private TextView tvRedBag;
	// 我的积分
	@ViewInject(R.id.tvIntegral)
	private TextView tvIntegral;
	// 幸运拍
	@ViewInject(R.id.tvXYP)
	private TextView tvXYP;
	// 红包区
	@ViewInject(R.id.tvHB)
	private TextView tvHB;
	// 一元拍
	@ViewInject(R.id.tvYYP)
	private TextView tvYYP;
	// 趣味拍
	@ViewInject(R.id.tvQWP)
	private TextView tvQWP;
	// 待发货
	@ViewInject(R.id.tvDFH)
	private TextView tvDFH;
	// 待收货
	@ViewInject(R.id.tvDSH)
	private TextView tvDSH;
	// 待晒单
	@ViewInject(R.id.tvDSD)
	private TextView tvDSD;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mine, container, false);
		ViewUtils.inject(this, view);
		initWidget();
		initDatas();
		return view;
	}
	

	@Override
	public void onResume() {
		super.onResume();
		if (UserManage.isLogin()) {// 如果登录了，获取用户信息
			// 修改我的账户登录的界面
			llMyAccoun.setVisibility(View.VISIBLE);
			llytNoLogin.setVisibility(View.INVISIBLE);
			// 获取用户信息
			GetUserInfoList();
			// 获取账目明细
			GetUserAccountList();
			// 乐拍记录
			GetUserOfferList();
			// 中拍商品
			GetUserOrderList();
		} else {
			// 修改我的账户未登录的界面
			llMyAccoun.setVisibility(View.INVISIBLE);
			llytNoLogin.setVisibility(View.VISIBLE);
			// 清楚账目记录
			tvBalance.setText("- -");
			tvRemind.setText("- -");
			tvRedBag.setText("- -");
			tvIntegral.setText("- -");
			// 乐拍记录
			tvHB.setVisibility(View.INVISIBLE);
			tvXYP.setVisibility(View.INVISIBLE);
			tvYYP.setVisibility(View.INVISIBLE);
			tvQWP.setVisibility(View.INVISIBLE);
			// 中拍商品
			tvDSD.setVisibility(View.INVISIBLE);
			tvDSH.setVisibility(View.INVISIBLE);
			tvDFH.setVisibility(View.INVISIBLE);
		}
	}

	/**
	 * 登录操作
	 * 
	 * @author: bangwei.yang
	 */
	private void login(final String username, final String password) {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/Login/UserLogin");// 方法
		httpClient.url.append("/" + username);// 名字
		httpClient.url.append("/" + password);// 密码
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Login response = JsonUtil.parseObject(act, responseInfo.result,
						Login.class);
				if (response != null) {
					// 存储到缓存中
					com.yfzx.lwpai.entity.Login.ResultEntity data = response
							.getResult().get(0);
					User user = new User();
					user.setAccount(username);
					user.setPassword(password);
					user.setCellPhone(data.getCellPhone());
					user.setFaceImage(data.getFaceImage());
					user.setUserId(data.getUserId());
					user.setUserName(data.getUsername());
					user.setRealName(data.getRealName());
					UserManage.update(user);
					userClientId();
				}
			}
		});

	}

	/**
	 * 
	 * 更新clientid
	 * 
	 * @author: bangwei.yang
	 */
	private void userClientId() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/UserClientId/0/");// 方法
		httpClient.url.append(PushManager.getInstance().getClientid(
				act.getApplicationContext()));// clientid
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				// BaseResponse response = JsonUtil.parseObject(act,
				// responseInfo.result, BaseResponse.class);
				// finish();
			}
		});
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	public void initWidget() {
		ivRight.setImageResource(R.drawable.mine_topic);
		ivLeft.setImageResource(R.drawable.mine_set);
		tvCenter.setText("账号中心");
		tvCenter.setTextColor(getResources().getColor(R.color.black));
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initDatas() {
	}

	/**
	 * 获取用户信息
	 * 
	 * @author: bangwei.yang
	 */
	private void GetUserInfoList() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserInfoList");// 方法
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				UserInfo response = JsonUtil.parseObject(act,
						responseInfo.result, UserInfo.class);
				if (response != null) {
					ResultEntity data = response.getResult().get(0);
					// 存储到缓存中
					ImageLoaderUtil.getRoundImage(data.getFaceImage(), ivHead);
					tvName.setText(data.getUserRank());
					tvRole.setText(data.getCellPhone());
				}
			}
		});

	}

	/**
	 * 账目明细
	 * 
	 * @author: bangwei.yang
	 */
	private void GetUserAccountList() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserAccountList");// 方法
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Account response = JsonUtil.parseObject(act,
						responseInfo.result, Account.class);
				if (response != null) {
					com.yfzx.lwpai.entity.Account.ResultEntity data = response
							.getResult().get(0);
					tvBalance.setText(data.getUseableBalance());
					tvRemind.setText(data.getRequestableBanlance());
					tvRedBag.setText(data.getHongBao());
					tvIntegral.setText(data.getIntegral());
				}
			}
		});

	}

	/**
	 * 获取乐拍记录的红点
	 * 
	 * @author: bangwei.yang
	 */
	private void GetUserOfferList() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserOfferList");// 方法
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Offer response = JsonUtil.parseObject(act, responseInfo.result,
						Offer.class);
				if (response != null) {
					com.yfzx.lwpai.entity.Offer.ResultEntity data = response
							.getResult().get(0);
					if (!data.getLuckyOfferNum().equals("0")) {
						tvXYP.setText(data.getLuckyOfferNum());
						tvXYP.setVisibility(View.VISIBLE);
					} else {
						tvXYP.setVisibility(View.INVISIBLE);
					}
					if (!data.getOneOfferNum().equals("0")) {
						tvYYP.setText(data.getOneOfferNum());
						tvYYP.setVisibility(View.VISIBLE);
					} else {
						tvYYP.setVisibility(View.INVISIBLE);
					}
					if (!data.getQWOfferNum().equals("0")) {
						tvQWP.setText(data.getQWOfferNum());
						tvQWP.setVisibility(View.VISIBLE);
					} else {
						tvQWP.setVisibility(View.INVISIBLE);
					}
					if (!data.getTryAreaOfferNum().equals("0")) {
						tvHB.setText(data.getTryAreaOfferNum());
						tvHB.setVisibility(View.VISIBLE);
					} else {
						tvHB.setVisibility(View.INVISIBLE);
					}
				}
			}
		});

	}

	/**
	 * 获取中拍商品的红点
	 * 
	 * @author: bangwei.yang
	 */
	private void GetUserOrderList() {
		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/members/GetUserOrderList");// 方法
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				Order response = JsonUtil.parseObject(act, responseInfo.result,
						Order.class);
				if (response != null) {
					com.yfzx.lwpai.entity.Order.ResultEntity data = response
							.getResult().get(0);
					if (!data.getWaitForSend().equals("0")) {
						tvDFH.setText(data.getWaitForSend());
						tvDFH.setVisibility(View.VISIBLE);
					} else {
						tvDFH.setVisibility(View.INVISIBLE);
					}
					if (!data.getWaitForReceive().equals("0")) {
						tvDSH.setText(data.getWaitForReceive());
						tvDSH.setVisibility(View.VISIBLE);
					} else {
						tvDSH.setVisibility(View.INVISIBLE);
					}
					if (!data.getWaitForShare().equals("0")) {
						tvDSD.setText(data.getWaitForShare());
						tvDSD.setVisibility(View.VISIBLE);
					} else {
						tvDSD.setVisibility(View.INVISIBLE);
					}
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
	@OnClick({ R.id.llMyAccoun, R.id.tvAllOrders, R.id.tvRecharge,
			R.id.tvRegest, R.id.tvLogin, R.id.tvAllRecord, R.id.ivLeft,
			R.id.ivRight, R.id.tvAllDetails, R.id.llytMsg, R.id.tvNews,
			R.id.llytShare, R.id.tvAddGroup, R.id.llLucky, R.id.llRed,
			R.id.llOne, R.id.llInterest, R.id.llytAccountbalance,
			R.id.llytWithdrawals, R.id.llytMyred, R.id.llytIntegral,
			R.id.llytAll, R.id.llytDFH, R.id.llytDSH, R.id.llytDSD,
			R.id.ivHeadLogin })
	public void onClick(View v) {
		Bundle bundle = new Bundle();
		if (v.getId() != R.id.tvRegest) {
			if (!UserManage.isLogin()) {
				showActivity(act, LoginActivity.class);
				return;
			}else {
				login(UserManage.getUser().getAccount(), UserManage.getUser()
						.getPassword());
			}
		}
		switch (v.getId()) {
		case R.id.llMyAccoun:// 我的账户
			showActivity(act, MyAccountActivity.class);
			break;
		case R.id.tvAllOrders:// 查看全部订单
			showActivity(act, MineOrderActivity.class);
			break;
		case R.id.tvAllRecord:// 查看乐拍记录
			showActivity(act, MineLPaiRecordActivity.class);
			break;
		case R.id.tvAllDetails:// 查看账目明细

			bundle.putString("balance", tvBalance.getText().toString());
			bundle.putString("remind", tvRemind.getText().toString());
			bundle.putString("redBag", tvRedBag.getText().toString());
			bundle.putString("integral", tvIntegral.getText().toString());
			showActivity(act, MineAccountDetailsActivity.class, bundle);
			break;

		case R.id.tvRecharge:// 我的充值
			showActivity(act, RechargeActivity.class);
			break;
		case R.id.llytMsg:// 消息中心
			showActivity(act, MineMsgCenterActivity.class);
			break;
		case R.id.tvNews:// 我的消息
			showActivity(act, MineMsgCenterActivity.class);
			break;
		case R.id.llytShare:// 我的晒单
			showActivity(act, MineShareActivity.class);
			break;
		case R.id.tvAddGroup:// 一键加群
			showActivity(act, QunWpaActivity.class);
			break;

		case R.id.ivLeft:// 更多
			showActivity(act, MineMoreActivity.class);
			break;
		case R.id.ivRight:// 意见反馈
			showActivity(act, MineFeedbackActivity.class);
			break;
		case R.id.tvRegest:// 注册
			showActivity(act, RegistActivity.class);
			break;
		case R.id.tvLogin:// 登录
			showActivity(act, LoginActivity.class);
			break;
		case R.id.ivHeadLogin:// 没登录头像
			showActivity(act, LoginActivity.class);
			break;
		case R.id.llLucky:// 幸运拍
			Bundle bundle1 = new Bundle();
			Intent intent1 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle1.putInt("type", 55);
			intent1.putExtras(bundle1);
			startActivity(intent1);
			break;
		case R.id.llRed:// 红包区
			Bundle bundle2 = new Bundle();
			Intent intent2 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle2.putInt("type", 56);
			intent2.putExtras(bundle2);
			startActivity(intent2);
			break;
		case R.id.llOne:// 一元拍
			Bundle bundle3 = new Bundle();
			Intent intent3 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle3.putInt("type", 3);
			intent3.putExtras(bundle3);
			startActivity(intent3);
			break;
		case R.id.llInterest:// 趣味拍
			Bundle bundle4 = new Bundle();
			Intent intent4 = new Intent(act, MineLuckyAuctionActivity.class);
			bundle4.putInt("type", 77);
			intent4.putExtras(bundle4);
			startActivity(intent4);
			break;
		case R.id.llytAccountbalance:// 账户余额
			showActivity(act, AccountBalanceActivity.class, bundle);
			break;

		case R.id.llytWithdrawals:// 可提现
			showActivity(act, AccountRemindActivity.class, bundle);
			break;

		case R.id.llytMyred:// 我的红包
			showActivity(act, AccountRedBagActivity.class);
			break;

		case R.id.llytIntegral:// 我的积分
			bundle.putString("integral", tvIntegral.getText().toString());
			showActivity(act, AccountIntegralActivity.class, bundle);
			break;
		case R.id.llytAll:// 全部
			showActivity(act, MineOrderActivity.class);
			break;

		case R.id.llytDFH:// 待发货
			bundle.putInt("curragePage", 1);
			showActivity(act, MineOrderActivity.class, bundle);
			break;
		case R.id.llytDSH:// 待收货
			bundle.putInt("curragePage", 2);
			showActivity(act, MineOrderActivity.class, bundle);
			break;
		case R.id.llytDSD:// 待晒单
			bundle.putInt("curragePage", 3);
			showActivity(act, MineOrderActivity.class, bundle);
			break;
		default:
			break;
		}
	}

}
