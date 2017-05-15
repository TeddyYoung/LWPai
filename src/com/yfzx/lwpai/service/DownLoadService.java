package com.yfzx.lwpai.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;

import com.yfzx.lwpai.R;
import com.yfzx.lwpai.contants.FileContants;

/**
 * 下载APK服务 无需绑定Activity,适合带通知的下载服务,在后台下载
 */
public class DownLoadService extends Service {
	private static final int DOWNLOAD_ING = 0;
	private static final int DOWNLOAD_FINISH = 1;
	private static final int DOWNLOAD_FAILED = 2;
	private static final String DOWN_LOAD_URL = "down_load_url";
	private static final int NOTIFY_ID = 0x1;

	private NotificationManager mNotifyManager;
	private NotificationCompat.Builder mNotification;
	private PendingIntent mSusPendingIntent;
	private PendingIntent mFailPendingIntent;
	private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
	// 是否已经有apk在下载的标记
	private boolean downLoading = false;
	// 下载apk的存储路径
	private String filePath = FileContants.DIR_APK;
	private String fileName = "LWPai.apk";
	// 上次更新通知的时间
	private long lastUpTime = 0;
	// 当前下载url
	private String url;

	/**
	 * 后台下载apk,带通知
	 * 
	 * @param url
	 */
	public static void startDownLoad(Context context, String url) {
		Intent intent = new Intent(context, DownLoadService.class);
		intent.putExtra(DOWN_LOAD_URL, url);
		context.startService(intent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotification = new NotificationCompat.Builder(this);
		mNotification.setSmallIcon(R.drawable.ic_launcher);
		mNotification.setContentTitle(getString(R.string.app_name));
		mNotification.setOngoing(true);
		mNotification.setAutoCancel(false);
		mNotification.setDefaults(NotificationCompat.PRIORITY_DEFAULT);
		mNotification.setTicker(getString(R.string.version_update));
		mNotification.setContentText(getString(R.string.apk_downloading));
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (downLoading) {
			return START_STICKY;
		}
		if (intent != null) {
			String url = intent.getStringExtra(DOWN_LOAD_URL);
			if (!TextUtils.isEmpty(url)) {
				mNotification.setWhen(System.currentTimeMillis());
				mNotifyManager.notify(NOTIFY_ID, mNotification.build());
				mNotification.setDefaults(NotificationCompat.PRIORITY_LOW);
				downLoad(url);
			}
		}
		return START_STICKY;
	}

	private void downLoad(final String urlString) {
		new AsyncTask<String, Integer, Integer>() {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				Intent failIntent = new Intent(DownLoadService.this,
						DownLoadService.class);
				failIntent.putExtra(DOWN_LOAD_URL, url);
				mFailPendingIntent = PendingIntent.getService(
						DownLoadService.this, R.string.app_name, failIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				super.onProgressUpdate(values);
				downLoadIng(values[0], values[1]);
			}

			@Override
			protected Integer doInBackground(String... params) {
				String urlString = params[0];
				downLoading = true;
				InputStream is = null;
				FileOutputStream fos = null;
				BufferedInputStream bis = null;
				try {
					URL url = new URL(urlString);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流
					is = conn.getInputStream();
					File file = new File(filePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(filePath, fileName);
					// 如果apk文件存在则删除重新下载
					if (apkFile.exists()) {
						apkFile.delete();
					}
					fos = new FileOutputStream(apkFile);
					// 当前读取流的大小
					int current;
					// 已读取流的大小
					int total = 0;
					bis = new BufferedInputStream(is);
					// 缓存
					byte buf[] = new byte[1024];
					// 线程是否被中断
					boolean interrupted = Thread.currentThread()
							.isInterrupted();
					// 写入到文件中
					while (!interrupted && (current = bis.read(buf)) != -1) {
						fos.write(buf, 0, current);
						total += current;
						publishProgress(total, length);
						interrupted = Thread.currentThread().isInterrupted();
					}
					if (interrupted) {// 下载终止
						return DOWNLOAD_FAILED;
					} else {
						return DOWNLOAD_FINISH;
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
					return DOWNLOAD_FAILED;
				} catch (IOException e) {
					e.printStackTrace();
					return DOWNLOAD_FAILED;
				} finally {
					try {
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (fos != null) {
							fos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (bis != null) {
							bis.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			protected void onPostExecute(Integer result) {
				super.onPostExecute(result);
				if (result == DOWNLOAD_FINISH) {
					// 下载成功,点击安装
					Intent susIntent = new Intent(Intent.ACTION_VIEW);
					File file = new File(filePath + fileName);
					if (file.exists()) {
						susIntent.setDataAndType(Uri.fromFile(file),
								"application/vnd.android.package-archive");
						mSusPendingIntent = PendingIntent.getActivity(
								DownLoadService.this, R.string.app_name, susIntent,
								PendingIntent.FLAG_UPDATE_CURRENT);
					}
					if(Build.VERSION.SDK_INT <= 10){
						Notification notification = mNotification.build();
						notification.contentView = null;
					} else {
						mNotification.setContent(null);
					}
					mNotification.setTicker(getString(R.string.version_update));
					mNotification.setContentTitle(getString(R.string.app_name));
					mNotification.setContentText(getString(R.string.apk_download_finish));
					mNotification.setContentIntent(mSusPendingIntent);
					mNotification.setDefaults(NotificationCompat.PRIORITY_DEFAULT);
					mNotification.setWhen(System.currentTimeMillis());
					mNotification.setOngoing(false);
					mNotification.setAutoCancel(true);
					mNotifyManager.notify(NOTIFY_ID, mNotification.build());
					downLoading = false;
					DownLoadService.this.stopSelf();
				} else {
					downLoadFailed();
				}
			}

			@Override
			protected void onCancelled() {
				super.onCancelled();
				downLoadFailed();
			}

			private void downLoadFailed() {
				// 下载失败,点击重新下载
				if(Build.VERSION.SDK_INT <= 10){
					Notification notification = mNotification.build();
					notification.contentView = null;
				} else {
					mNotification.setContent(null);
				}
				mNotification.setTicker(getString(R.string.version_update));
				mNotification.setContentTitle(getString(R.string.app_name));
				mNotification.setContentText(getString(R.string.apk_download_failed));
				mNotification.setContentIntent(mFailPendingIntent);
				mNotification.setDefaults(NotificationCompat.PRIORITY_DEFAULT);
				mNotification.setWhen(System.currentTimeMillis());
				mNotification.setOngoing(false);
				mNotification.setAutoCancel(true);
				mNotifyManager.notify(NOTIFY_ID, mNotification.build());
				downLoading = false;
			}

			private void downLoadIng(int current, int length) {
				// 发通知频率太高会导致SystemUI ANR,因此需要控制更新通知时间间隔
				if (System.currentTimeMillis() - lastUpTime < 1000) {
					return;
				}
				lastUpTime = System.currentTimeMillis();
				RemoteViews remoteView = new RemoteViews(getPackageName(),
						R.layout.layout_notify_version_update);
				remoteView.setProgressBar(R.id.notify_pro_bar, length, current,
						true);
				remoteView.setTextViewText(R.id.notify_pro_txt, current * 100
						/ length + "%");
				remoteView.setTextViewText(R.id.notify_time,
						formatter.format(new Date(System.currentTimeMillis())));
				if(Build.VERSION.SDK_INT <= 10){
					Notification notification = mNotification.build();
					notification.contentView = remoteView;
					mNotifyManager.notify(NOTIFY_ID, notification);
				} else {
					mNotification.setContent(remoteView);
					mNotifyManager.notify(NOTIFY_ID, mNotification.build());
				}
			}
		}.execute(urlString);
	}
}
