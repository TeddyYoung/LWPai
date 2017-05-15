package com.yfzx.library.universalimageloader;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.yfzx.library.tools.ToolImage;
import com.yfzx.library.tools.ToolNetwork;
import com.yfzx.lwpai.CommonGlobal;
import com.yfzx.lwpai.R;

public class ImageLoaderUtil {

	public static String smallImageUrl = CommonGlobal.SERVER
			+ "/api/modules/picture/downloadSmallPic/";
	public static String bigImageUrl = CommonGlobal.SERVER
			+ "/api/modules/picture/downloadBigPic/";

	public static ImageLoader imageLoader = ToolImage.getImageLoader();

	public static void getBigImage(String url, ImageView imageAware) {
		imageLoader.displayImage(bigImageUrl + url, imageAware, imageOptions);
	}

	public static void getSmallImage(String url, ImageView imageAware) {
		imageLoader.displayImage(smallImageUrl + url, imageAware, imageOptions);
	}

	/**
	 * 根据网络读取不同质量的图片
	 * 
	 * @author: bangwei.yang
	 * @param url100
	 *            /为低质量图片，url440位高质量图片
	 * @param imageAware
	 */
	public static void getByUrl(String url100, String url440,
			ImageView imageAware) {
		if (ToolNetwork.getInstance().getNetworkType()
				.equals(ToolNetwork.NETWORK_WIFI)) {
			imageLoader.displayImage(url440, imageAware, imageOptions);
		} else {
			imageLoader.displayImage(url100, imageAware, imageOptions);
		}
	}

	/**
	 * 通过地址获取图片
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param imageAware
	 */
	public static void getByUrl(String url, ImageView imageAware) {
		imageLoader.displayImage(url, imageAware, imageOptions);
	}

	/**
	 * 获取圆角的图片
	 * 
	 * @author: bangwei.yang
	 * @param url
	 * @param imageAware
	 */
	public static void getRoundImage(String url, ImageView imageAware) {
		imageLoader.displayImage(url, imageAware, circleOptions);
	}

	public static DisplayImageOptions circleOptions = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			// 加载开始默认的图片
			.showImageForEmptyUri(R.drawable.mine_head)
			// url爲空會显示该图片，自己放在drawable里面的
			.showImageOnFail(R.drawable.mine_head).cacheOnDisk(true)
			.displayer(new CircleBitmapDisplayer()).build();

	// 通用的缓存模板
	@SuppressWarnings("deprecation")
	public static DisplayImageOptions imageOptions = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.cacheOnDisc(true)
			 .showImageOnLoading(R.drawable.tx)
			// 加载开始默认的图片
			.showImageForEmptyUri(R.drawable.tx)
			// url爲空會显示该图片，自己放在drawable里面的
			.showImageOnFail(R.drawable.failed)
			// 加载图片出现问题，会显示该图片
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			.bitmapConfig(Bitmap.Config.RGB_565).build();

}
