package com.yfzx.lwpai.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolNetwork;
import com.yfzx.lwpai.MApplication;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.entity.Login;
import com.yfzx.lwpai.entity.User;
import com.yfzx.lwpai.fragment.GoodsCategoryFragment;
import com.yfzx.lwpai.fragment.HomeFragment;
import com.yfzx.lwpai.fragment.LatestFragment;
import com.yfzx.lwpai.fragment.MineFragment;
import com.yfzx.lwpai.service.AnimService;
import com.yfzx.lwpai.service.DownLoadService;
import com.yfzx.lwpai.service.VersionUpdateTask;
import com.yfzx.lwpai.service.VersionUpdateTask.OnUpdateCallback;
import com.yfzx.lwpai.util.GetTimeHelper;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 主函数
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-1
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

	// 首页
	@ViewInject(R.id.rdoBtnHome)
	private RadioButton rdoBtnHome;
	// 商品分类
	@ViewInject(R.id.rdoBtnShare)
	private RadioButton rdoBtnShare;
	// 最新揭晓
	@ViewInject(R.id.rdoBtnLatest)
	private RadioButton rdoBtnLatest;
	// 我的乐拍
	@ViewInject(R.id.rdoBtnMine)
	private RadioButton rdoBtnMine;

	// 用于管理Fragment
	private FragmentManager fgManager = getSupportFragmentManager();
	private static Fragment fragment = new Fragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 绑定界面UI
		requestWindowFeature(Window.FEATURE_PROGRESS);
		ViewUtils.inject(this);

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		MApplication.self.setHeight(height);
		MApplication.self.setWidth(width);
		//获得系统时间
		GetTimeHelper.getCurrentTime(act);
		initWidget();
		initData();
		
		// 初始化个推
		PushManager.getInstance().initialize(this.getApplicationContext());

		if (getIntent().getBooleanExtra("showAnim", false)) {
			if (getIntent().hasExtra("animType")
					&& getIntent().hasExtra("category")
					&& getIntent().hasExtra("lucky")
					&& getIntent().hasExtra("goodsId")
					&& getIntent().hasExtra("type")) {
				String animType = getIntent().getStringExtra("animType");
				String goodsId = getIntent().getStringExtra("goodsId");
				int type = getIntent().getIntExtra("type", 1);
				Log.i("zzMainGoodig", goodsId);
				AnimService.startAnim(this, animType, goodsId, type);
			}
		}

		if (ToolNetwork.getInstance().getNetworkType()
				.equals(ToolNetwork.NETWORK_WIFI)
				&& CacheManage.getAccessor().getBoolean("auto_update", true)) {
			VersionUpdateTask.checkUpdate(this, new OnUpdateCallback() {
				@Override
				public void update(boolean update, String content,
						final String url) {
					if (!update) {
						return;
					}
					new AlertDialog.Builder(MainActivity.this)
							.setTitle("发现新版本")
							.setMessage(content)
							.setPositiveButton("更新",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											DownLoadService.startDownLoad(
													MainActivity.this, url);
										}
									}).setNegativeButton("忽略", null).show();
				}
			});
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent.getBooleanExtra("showAnim", false)) {
			if (intent.hasExtra("animType") && intent.hasExtra("category")
					&& intent.hasExtra("lucky") && intent.hasExtra("goodsId")
					&& intent.hasExtra("type")) {
				String animType = intent.getStringExtra("animType");
				String goodsId = intent.getStringExtra("goodsId");
				int type = intent.getIntExtra("type", 1);
				Log.i("zznewGoodig", goodsId);
				AnimService.startAnim(this, animType, goodsId, type);
			}
		}
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initWidget() {
		// 默认选中广场页面
		rdoBtnShare.performClick();
		rdoBtnHome.performClick();
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {
		if (UserManage.isLogin()) {
			login(UserManage.getUser().getAccount(), UserManage.getUser()
					.getPassword());
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
	 * 切换到对应的一级菜单位置
	 * 
	 * @author: bangwei.yang
	 * @param pos
	 */
	public void clickMenu(int pos) {
		switch (pos) {
		case 1: {
			rdoBtnHome.performClick();
			break;
		}
		case 2: {
			rdoBtnShare.performClick();
			break;
		}
		case 3: {
			rdoBtnLatest.performClick();
			break;
		}
		case 4: {
			rdoBtnMine.performClick();
		}
		}
	}

	/**
	 * 切换到对应的商品的页面
	 * 
	 * @author: bangwei.yang
	 * @param pos
	 */
	public void clickGood(int pos) {
		rdoBtnShare.performClick();
		try {
			((GoodsCategoryFragment) fragment).click(pos);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.rdoBtnHome, R.id.rdoBtnLatest, R.id.rdoBtnMine,
			R.id.rdoBtnShare })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rdoBtnHome: {// 首页模块
			switchContent(fragment, new HomeFragment(), "home");
			break;
		}
		case R.id.rdoBtnShare: {// 商品分类
			switchContent(fragment, new GoodsCategoryFragment(), "goodCategory");
			break;
		}
		case R.id.rdoBtnLatest: {// 最新揭晓
			switchContent(fragment, new LatestFragment(), "latest");
			break;
		}
		case R.id.rdoBtnMine: {// 我的乐拍
			switchContent(fragment, new MineFragment(), "mineLPai");
			break;
		}
		}
	}

	/**
	 * 更改显示的页面
	 * 
	 * @author: bangwei.yang
	 * @param from
	 * @param to
	 * @param tag
	 */
	public void switchContent(Fragment from, Fragment to, String tag) {
		if (fragment != to) {
			FragmentTransaction transaction = fgManager.beginTransaction();
			try {
				// 根据Tag查找是否添加
				Fragment tmpFragment = fgManager.findFragmentByTag(tag);
				if (tmpFragment != null) { // 先判断是否被add过，如果不为空为true
					to = tmpFragment;// 替换成已添加的界面
					transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
				} else {
					transaction.hide(from).add(R.id.flytContent, to, tag)
							.commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
				}
				// 修改当前选择页面
				fragment = to;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 菜单、返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return false;
	}

	/**
	 * 双击退出函数
	 */
	private Boolean isExit = false;

	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
//			ToolToast.showLong("再按一次退出程序~");
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			finish();
			stopService(new Intent(this, AnimService.class));
			System.exit(0);
		}
	}
}
