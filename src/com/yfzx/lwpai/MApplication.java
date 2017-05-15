package com.yfzx.lwpai;

import java.io.File;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.yfzx.library.core.BaseApplication;
import com.yfzx.library.tools.ToolFile;
import com.yfzx.library.tools.ToolImage;
import com.yfzx.lwpai.contants.FileContants;

public class MApplication extends BaseApplication {

	public static MApplication self;
	public static DbUtils db;
	private PreferencesCookieStore cookieStore;
	private int width;
	private int height;

	@Override
	public void onCreate() {
		super.onCreate();
		ToolImage.init(getApplicationContext());
		self = this;
		db = DbUtils.create(self);
		creatFileOrDir();
		// 对全局未捕获的异常进行捕捉并写入SD卡
		Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler());
	}
	
	private void creatFileOrDir() {
		if (!ToolFile.isMountedSDCard()) {
			return;
		}
		File baseDir = new File(FileContants.DIR_BASE);
		baseDir.mkdirs();
		File scanningDir = new File(FileContants.DIR_LOG);
		scanningDir.mkdirs();
	}

	public PreferencesCookieStore getCookieStore() {
		if (cookieStore == null) {
			cookieStore = new PreferencesCookieStore(this);
		}
		return cookieStore;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
