package com.yfzx.lwpai.receiver;

import java.util.List;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.activity.MainActivity;
import com.yfzx.lwpai.entity.GoodsIdEntity;
import com.yfzx.lwpai.service.AnimService;
import com.yfzx.lwpai.sqlite.GoodsIDHelper;
import com.yfzx.lwpai.util.GetTimeHelper;

/**
 * 个推透传服务
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-8-6
 */
public class PushReceiver extends BroadcastReceiver {

	private static final String PACKAGE_NAME = "com.hns.cloud";
	private static final String ALARM_ACTIVITY = ".ui.AlarmInfoActivity";
	private static final String ALARM_FILTER_NAME = ".ui.ALARMINFO";
	private static final String MAIN_ACTIVITY = ".ui.MainActivity";

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		Log.d("GetuiSdkDemo", "onReceive() action=" + bundle.getInt("action"));
		switch (bundle.getInt(PushConsts.CMD_ACTION)) {

		case PushConsts.GET_MSG_DATA:
			// 获取透传数据
			// String appid = bundle.getString("appid");
			byte[] payload = bundle.getByteArray("payload");
			String taskid = bundle.getString("taskid");
			String messageid = bundle.getString("messageid");

			// smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
			boolean result = PushManager.getInstance().sendFeedbackMessage(
					context.getApplicationContext(), taskid, messageid, 90001);
			System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));

			// 根据数据，本地处理相应情况
			if (payload != null) {
				String data = new String(payload);
				parseMessage(context, data);
				Log.d("GetuiSdkDemo", "Got Payload:" + data);
			}
			break;
		case PushConsts.GET_CLIENTID:
			// 获取ClientID(CID)
			// 第三方应用需要将CID上传到第三方服务器，并且将当前用户帐号和CID进行关联，以便日后通过用户帐号查找CID进行消息推送
			String cid = bundle.getString("clientid");

			Log.d("lhs", "cid = " + cid);
			// App.self.saveCid(cid);
			// App.self.updateCid(cid);
			// if (GetuiSdkDemoActivity.tView != null)
			// GetuiSdkDemoActivity.tView.setText(cid);
			break;

		case PushConsts.THIRDPART_FEEDBACK:
			/*
			 * String appid = bundle.getString("appid"); String taskid =
			 * bundle.getString("taskid"); String actionid =
			 * bundle.getString("actionid"); String result =
			 * bundle.getString("result"); long timestamp =
			 * bundle.getLong("timestamp");
			 * 
			 * Log.d("GetuiSdkDemo", "appid = " + appid); Log.d("GetuiSdkDemo",
			 * "taskid = " + taskid); Log.d("GetuiSdkDemo", "actionid = " +
			 * actionid); Log.d("GetuiSdkDemo", "result = " + result);
			 * Log.d("GetuiSdkDemo", "timestamp = " + timestamp);
			 */
			break;
		default:
			break;
		}
	}

	private void parseMessage(Context context, String data) {
		// if (TextUtils.isEmpty(data)) {
		// return;
		// }
		// String re[] = data.split(",");
		// if (re.length < 11) {
		// return;
		// }
		// ChatBean chat = new ChatBean();
		// chat.setSysId(re[0]);
		// chat.setMsgType(Integer.parseInt(re[1]));
		// chat.setMsgContent(re[2]);
		// chat.setMsgContent2(re[3]);
		// chat.setMsgContent3(re[4]);
		// chat.setMsgContent4(re[5]);
		// chat.setMsgContent5(re[6]);
		// chat.setUserSysId(re[7]);// 21
		// chat.setFriendUserSysId(re[8]);// 33
		// // chat.setBelongId(UserManage.getUser().getUserId());
		// chat.setBelongId(re[8]);
		// chat.setUpdDate(re[9]);
		// chat.setUpdTime(re[10]);
		//
		// chat.setReadState(MsgConfigs.STATE_UNREAD);
		// chat.setReleaseDate(re[9]);
		// chat.setReleaseTime(re[10]);
		// chat.setStatus(MsgConfigs.STATE_UNREAD_RECEIVED);
		// chat.setUpdUser("");
		// Log.d("lhs", chat.getUpdDate() + " " + chat.getUpdTime());
		// Date datet.getUpdTime());
		// if (date == null) {
		// Log.d("lhs", "date is null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// } else {
		// chat.setCreateAt(date.getTime());
		// }
		//
		// if (chat.getUserSysId().equals(// 自己话题自己的操作，不做提醒
		// chat.getFriendUserSysId()) && chat.getMsgType() == 6) {
		// return;
		// } else {
		// MsgHelper.saveNew(chat);
		// }
		//
		// ActivityManager am = (ActivityManager) context
		// .getSystemService(Context.ACTIVITY_SERVICE);
		// RunningTaskInfo taskInfo = am.getRunningTasks(1).get(0);
		// // if
		// //
		// (taskInfo.topActivity.getClassName().contains(context.getPackageName()))
		// // {
		// if (taskInfo.topActivity.getClassName().contains("com.yfzx.meipei"))
		// {
		// //
		// if(taskInfo.topActivity.getClassName().equals(context.getPackageName()+".activity.ChatActivity")){
		// //
		// // }else{
		// //
		// // }
		//
		// Intent intent = new Intent();
		// intent.putExtra("msgId", chat.getSysId());
		// intent.putExtra("fromId", chat.getUserSysId());
		// intent.putExtra("msgTime",
		// chat.getUpdDate() + " " + chat.getUpdTime());
		// intent.setAction(MsgConfigs.BROADCAST_NEW_MESSAGE);
		// context.sendBroadcast(intent);
		// getMsgNotice(context);
		// } else {
		// if (MsgHelper.getTotalNoReadCount() > 0) { =
		// DateFormatUtils.parseStrToDateTime(chat.getUpdDate() + " "
		// + cha
		// notice(context, "收到了透传消息" + data);
		getMsg(context, data);

	}

	private void getMsg(Context context, String data) {
		try {
			String[] datas = data.split(",");
			String animType = datas[0];
			String goodsID = datas[1];
			int type = Integer.parseInt(datas[2]);
			// if (isInPackageActivity(context)) {
			// showWindow(context, animType, goodsID, type);
			// } else {
			// notice(context, "有新商品开始竞拍啦，快去看看吧", animType, goodsID, type);
			// }

			// 查询数据库
			GoodsIDHelper goodsIDHelper = new GoodsIDHelper(context);
			if (goodsIDHelper.select(goodsID) == 0) {
				goodsIDHelper.insert(GetTimeHelper.getTime().getTime() + "",
						goodsID);
				if (goodsIDHelper.select(goodsID) == 1) {
					if (isInPackageActivity(context)) {
						showWindow(context, animType, goodsID, type);
					} else {
						notice(context, "有新商品开始竞拍啦，快去看看吧", animType, goodsID,
								type);
					}
				}
			}
			List<GoodsIdEntity> goodsIdEntities = goodsIDHelper.GoodsIdEntity();
			for (GoodsIdEntity goodsIdEntity : goodsIdEntities) {
				String time = goodsIdEntity.getTime();
				String goodid = goodsIdEntity.getGoodid();
				long timeTemp = Long.parseLong(time);
				 if (GetTimeHelper.getTime().getTime() - 86400 > timeTemp) {
				 goodsIDHelper.delete(goodid);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isInPackageActivity(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		// get the info from the currently running task
		List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
		ComponentName componentInfo = taskInfo.get(0).topActivity;
		return componentInfo.getPackageName().equals(context.getPackageName());
	}

	/**
	 * 通知栏的消息提示
	 * 
	 * @author: bangwei.yang
	 * @param context
	 * @param content
	 */
	private void notice(Context context, String content, String animType,
			String goodsID, int type) {
		NotificationManager nm = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(
				R.drawable.actionbar_tab_bg, content,
				System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_VIBRATE; // 调用系统自带声音
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra("showAnim", true);
		intent.putExtra("animType", animType);
		intent.putExtra("category", 0);// 0进行 1即将开始 2结束
		intent.putExtra("lucky", 1); // 显示TOP3000
		intent.putExtra("goodsId", goodsID);// 商品ID
		intent.putExtra("type", type);// 0一元拍，2红包区，2幸运拍
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		// PendingIntent
		PendingIntent contentIntent = PendingIntent.getActivity(context,
				(int) System.currentTimeMillis(), intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(context, context.getResources()
				.getText(R.string.app_name), content, contentIntent);
		nm.notify((int) 1, notification);
	}

	private void showWindow(Context context, String animType, String goodsID,
			int type) {
		AnimService.startAnim(context, animType, goodsID, type);
	}
}
