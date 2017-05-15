package com.yfzx.lwpai.util;
import java.util.Date;
import android.content.Context;
import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolDateTime;
import com.yfzx.lwpai.entity.TimeEntity;
import com.yfzx.lwpai.entity.TimeEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 获取当前时间
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-24
 */
public class GetTimeHelper {

	public static Date TIME;

	/**
	 * 获取服务器当前时间
	 * 
	 * @param context
	 * @return
	 * @return
	 */

	public static void getCurrentTime(final Context context) {
		xHttpClient httpClient = new xHttpClient(context, false);
		httpClient.url.append("api/lucky/GetServerTime");

		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				TimeEntity response = JsonUtil.parseObject(context,
						responseInfo.result, TimeEntity.class);
				if (response != null) {
					ResultEntity data = response.getResult();
					TIME = ToolDateTime.parseDate(data.getTime());
				}
			}
		});
	}

	/**
	 * 如果不能获取服务器时间就获得系统时间
	 * 
	 * @author: songbing.zhou
	 * @return
	 */
	public static Date getTime() {
		if (TIME == null) {
			TIME = ToolDateTime.gainCurrentDate();
		}
		return TIME;

	}

}
