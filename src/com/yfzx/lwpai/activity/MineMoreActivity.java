package com.yfzx.lwpai.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.data.message.CacheManage;
import com.yfzx.library.tools.ToolDataClean;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.UserManage;
import com.yfzx.lwpai.service.DownLoadService;
import com.yfzx.lwpai.service.VersionUpdateTask;
import com.yfzx.lwpai.service.VersionUpdateTask.OnUpdateCallback;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 更多
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-8
 */
@ContentView(R.layout.activity_mine_more)
public class MineMoreActivity extends BaseActivity {
	// back
	@ViewInject(R.id.ivLeft)
	private ImageView ivLeft;
	// 标题
	@ViewInject(R.id.tvCenter)
	private TextView tvCenter;

	// 标题
	@ViewInject(R.id.auto_update)
	private CheckBox autoUpdate;

	// 缓存大小
	@ViewInject(R.id.tvCacheSize)
	private TextView tvCacheSize;

	// 版本号
	@ViewInject(R.id.tvVersion)
	private TextView tvVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewUtils.inject(this);
		initWidget();
		initDate();
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initWidget() {
		tvCenter.setText("更多");
		ivLeft.setImageResource(R.drawable.top_back_black);
		// 版本号
		tvVersion.setText("V" + getResources().getString(R.string.versionName));
		autoUpdate.setChecked(CacheManage.getAccessor().getBoolean(
				"auto_update", true));
		autoUpdate.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				CacheManage.getAccessor().putBoolean("auto_update", isChecked);
			}
		});
	}

	/**
	 * 初始化数据
	 * 
	 * @author: bangwei.yang
	 */
	private void initDate() {
		getCacheSize();
	}

	/**
	 * 获取缓存数据
	 * 
	 * @author: bangwei.yang
	 */
	private void getCacheSize() {
		// 获取本地缓存
		String cacheSize = "";
		try {
			cacheSize = ToolDataClean.getCacheSize(getCacheDir());
		} catch (Exception e) {
			e.printStackTrace();
		}
		tvCacheSize.setText(cacheSize);
	}

	/**
	 * 点击事件
	 * 
	 * @author: bangwei.yang
	 * @param v
	 */
	@OnClick({ R.id.ivLeft, R.id.btnExit, R.id.llSite, R.id.llytCleanCache,
			R.id.llHelp, R.id.llFeedback, R.id.llSecurity })
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivLeft:// 返回
			finish();
			break;
		case R.id.btnExit:// 退出
			UserManage.clear();
			finish();
			break;
		case R.id.llHelp:// 使用帮助
			showActivity(act, MineHelpCenterActivity.class);
			break;
		case R.id.llFeedback:// 意见反馈
			showActivity(act, MineFeedbackActivity.class);
			break;
		case R.id.llSite:// 关于
			showActivity(act, AboutActivity.class);
			break;
		case R.id.llytCleanCache:// 清除缓存
			ProgressHelper.getInstance().show(act, "正在清除缓存，请稍等...", true);
			ToolDataClean.cleanCustomCache(getCacheDir().getAbsolutePath());
			getCacheSize();
			ProgressHelper.getInstance().cancel();
			break;
		case R.id.llSecurity:
			VersionUpdateTask.checkUpdate(this, new OnUpdateCallback() {
				@Override
				public void update(boolean update, String content,
						final String url) {
					if (!update) {
						ToolToast.showShort("当前已是最新版本");
						return;
					}
					new AlertDialog.Builder(MineMoreActivity.this)
							.setTitle("发现新版本")
							.setMessage(content)
							.setPositiveButton("更新",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											DownLoadService.startDownLoad(
													MineMoreActivity.this, url);
										}
									}).setNegativeButton("忽略", null).show();
				}
			});
			break;
		default:
			break;
		}
	}

}
