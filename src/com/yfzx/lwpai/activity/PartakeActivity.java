package com.yfzx.lwpai.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.lidroid.xutils.http.ResponseInfo;
import com.yfzx.library.core.BaseActivity;
import com.yfzx.library.core.BaseResponse;
import com.yfzx.library.http.JsonUtil;
import com.yfzx.library.http.xHttpClient;
import com.yfzx.library.http.xResopnse;
import com.yfzx.library.tools.ToolToast;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.R.id;
import com.yfzx.lwpai.entity.RedBagInfoEntity;
import com.yfzx.lwpai.entity.RedBagInfoEntity.ResultEntity;
import com.yfzx.lwpai.view.ProgressHelper;

/**
 * 参与竞拍
 * 
 * @author: bangwei.yang
 * @version Revision: 0.0.1
 * @Date: 2015-7-23
 */
public class PartakeActivity extends BaseActivity implements OnClickListener {

	// 全局
	private ImageView ivClose;
	private Button btnBidders;
	private LinearLayout llytEnpty;
	private LinearLayout llytRedBag;
	private LinearLayout llytLuck;
	private LinearLayout llytOne;

	// 幸运拍
	private EditText edtTxtLuckNum;
	private TextView tvLuckAdd;
	private TextView tvLuckReduce;
	private EditText edtTxtLuckTrandPwd;
	private TextView tvLuckForget;
	// 一元拍
	private RadioButton rdoBtn5;
	private RadioButton rdoBtn10;
	private RadioButton rdoBtn20;
	private RadioButton rdoBtn30;
	private RadioButton rdoBtn40;
	private RadioButton rdoBtn50;
	private EditText edtTxtOneNum;
	private TextView tvOneAdd;
	private TextView tvOneReduce;
	private EditText edtTxtOneTrandPwd;
	private TextView tvOneForget;

	// 红包区
	private EditText edtTxtRedNum;
	private TextView tvRedAdd;
	private TextView tvRedMoney;
	private TextView tvRedReduce;
	private Spinner spinner;

	// 使用的数据
	private String goodsId;
	private String goodsOneID;
	private String hongbaoId;
	private int type;// 55幸运拍，56红包区，3一元拍
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	private List<ResultEntity> redBagData;
	/**
	 * 市场价格
	 */
	private int marketPrice;
	/**
	 * 商品ID
	 */
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partake);
		getWindow().setLayout(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		// 根据类型去选择显示的布局
		llytRedBag = (LinearLayout) findViewById(R.id.llytRedBag);
		llytLuck = (LinearLayout) findViewById(R.id.llytLuck);
		llytOne = (LinearLayout) findViewById(R.id.llytOne);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {// 55幸运拍，56红包区，3一元拍
			type = bundle.getInt("type");
			goodsId = bundle.getString("goodsId");
			goodsOneID = bundle.getString("goodsOneID");
			if (type == 55 || type == 77) {
				// llytLuck.setVisibility(View.VISIBLE);
				llytOne.setVisibility(View.VISIBLE);
			} else if (type == 56) {
				llytRedBag.setVisibility(View.VISIBLE);
				getMyHongBao(bundle.getString("id"));
			} else {
				llytOne.setVisibility(View.VISIBLE);
				marketPrice = bundle.getInt("marketPrice");
			}

		}

		initWidget();
		initData();
	}

	private void initWidget() {
		// 公共页面
		llytEnpty = (LinearLayout) findViewById(R.id.llytEnpty);
		llytEnpty.setOnClickListener(this);
		btnBidders = (Button) findViewById(R.id.btnBidders);
		btnBidders.setOnClickListener(this);
		ivClose = (ImageView) findViewById(R.id.ivClose);
		ivClose.setOnClickListener(this);

		// 一元拍
		tvOneAdd = (TextView) findViewById(R.id.tvOneAdd);
		tvOneAdd.setOnClickListener(this);
		tvOneReduce = (TextView) findViewById(R.id.tvOneReduce);
		tvOneReduce.setOnClickListener(this);
		tvOneForget = (TextView) findViewById(R.id.tvOneForget);
		tvOneForget.setOnClickListener(this);
		edtTxtOneNum = (EditText) findViewById(R.id.edtTxtOneNum);
		edtTxtOneTrandPwd = (EditText) findViewById(R.id.edtTxtOneTrandPwd);

		// 幸运拍
		tvLuckAdd = (TextView) findViewById(R.id.tvLuckAdd);
		tvLuckForget = (TextView) findViewById(R.id.tvLuckForget);
		tvLuckReduce = (TextView) findViewById(R.id.tvLuckReduce);
		edtTxtLuckTrandPwd = (EditText) findViewById(R.id.edtTxtLuckTrandPwd);
		edtTxtLuckNum = (EditText) findViewById(R.id.edtTxtLuckNum);
		rdoBtn5 = (RadioButton) findViewById(R.id.rdoBtn5);
		rdoBtn10 = (RadioButton) findViewById(R.id.rdoBtn10);
		rdoBtn20 = (RadioButton) findViewById(R.id.rdoBtn20);
		rdoBtn30 = (RadioButton) findViewById(R.id.rdoBtn30);
		rdoBtn40 = (RadioButton) findViewById(R.id.rdoBtn40);
		rdoBtn50 = (RadioButton) findViewById(R.id.rdoBtn50);
		rdoBtn5.setOnClickListener(this);
		rdoBtn10.setOnClickListener(this);
		rdoBtn20.setOnClickListener(this);
		rdoBtn30.setOnClickListener(this);
		rdoBtn40.setOnClickListener(this);
		rdoBtn50.setOnClickListener(this);
		tvLuckForget.setOnClickListener(this);
		tvLuckReduce.setOnClickListener(this);
		tvLuckAdd.setOnClickListener(this);
		// 红包竞拍
		tvRedAdd = (TextView) findViewById(R.id.tvRedAdd);
		tvRedAdd.setOnClickListener(this);
		tvRedReduce = (TextView) findViewById(R.id.tvRedReduce);
		tvRedReduce.setOnClickListener(this);
		tvRedMoney = (TextView) findViewById(R.id.tvRedMoney);
		edtTxtRedNum = (EditText) findViewById(R.id.edtTxtRedNum);
	}

	// 使用数组形式操作
	class SpinnerSelectedListener implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			if (list.get(arg2).equals("请选择红包") || list.get(arg2).equals("暂无红包")) {
				tvRedMoney.setText("红包余额");
				hongbaoId = "";
			} else {
				tvRedMoney.setText(redBagData.get(arg2 - 1).getBalance() + "元");
				hongbaoId = redBagData.get(arg2 - 1).getHongBaoItemId();
			}
		}

		public void onNothingSelected(AdapterView<?> arg0) {

		}
	}

	/**
	 * 初始化界面
	 * 
	 * @author: bangwei.yang
	 */
	private void initData() {
		if (type == 3) {
			if (marketPrice >= 50) {
				rdoBtn5.setVisibility(View.VISIBLE);
				rdoBtn10.setVisibility(View.VISIBLE);
				rdoBtn20.setVisibility(View.VISIBLE);
				rdoBtn30.setVisibility(View.VISIBLE);
				rdoBtn40.setVisibility(View.VISIBLE);
				rdoBtn50.setVisibility(View.VISIBLE);
			} else if (marketPrice >= 40) {
				rdoBtn5.setVisibility(View.VISIBLE);
				rdoBtn10.setVisibility(View.VISIBLE);
				rdoBtn20.setVisibility(View.VISIBLE);
				rdoBtn30.setVisibility(View.VISIBLE);
				rdoBtn40.setVisibility(View.VISIBLE);
			} else if (marketPrice >= 30) {
				rdoBtn5.setVisibility(View.VISIBLE);
				rdoBtn10.setVisibility(View.VISIBLE);
				rdoBtn20.setVisibility(View.VISIBLE);
				rdoBtn30.setVisibility(View.VISIBLE);
			} else if (marketPrice >= 20) {
				rdoBtn5.setVisibility(View.VISIBLE);
				rdoBtn10.setVisibility(View.VISIBLE);
				rdoBtn20.setVisibility(View.VISIBLE);
			} else if (marketPrice >= 10) {
				rdoBtn5.setVisibility(View.VISIBLE);
				rdoBtn10.setVisibility(View.VISIBLE);
			} else if (marketPrice >= 5) {
				rdoBtn5.setVisibility(View.VISIBLE);
			}
		} else if (type == 55 || type == 77) {
			rdoBtn5.setVisibility(View.VISIBLE);
			rdoBtn10.setVisibility(View.VISIBLE);
			rdoBtn20.setVisibility(View.VISIBLE);
			rdoBtn30.setVisibility(View.VISIBLE);
			rdoBtn40.setVisibility(View.VISIBLE);
			rdoBtn50.setVisibility(View.VISIBLE);
			rdoBtn5.setText("10组");
			rdoBtn10.setText("20组");
			rdoBtn20.setText("50组");
			rdoBtn30.setText("100组");
			rdoBtn40.setText("500组");
			rdoBtn50.setText("1000组");
		}

	}

	/**
	 * 获取该用户的红包
	 * 
	 * @author: bangwei.yang
	 */
	private void getMyHongBao(String id) {
		spinner = (Spinner) findViewById(R.id.spinner);
		// 将可选内容与ArrayAdapter连接起来
		adapter = new ArrayAdapter<String>(act,
				android.R.layout.simple_spinner_item, list);
		// 设置下拉列表的风格
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 添加事件Spinner事件监听
		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());
		// 将adapter 添加到spinner中
		spinner.setAdapter(adapter);

		xHttpClient httpClient = new xHttpClient(act);
		httpClient.url.append("api/Discount/GetMyHongBao/" + id);
		httpClient.post(new xResopnse() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				RedBagInfoEntity response = JsonUtil.parseObject(act,
						responseInfo.result, RedBagInfoEntity.class);
				list.clear();
				if (response != null) {
					redBagData = response.getResult();
					list.add("请选择红包");
					for (ResultEntity data : redBagData) {
						list.add(data.getHongBaoName());
					}
					adapter.notifyDataSetChanged();
				} else {
					list.add("暂无红包");
				}
			}
		});
	}

	/**
	 * 参与幸运拍竞拍
	 * 
	 * @author: bangwei.yang
	 */
	private void luckyShopping() {
		String num = edtTxtOneNum.getText().toString().trim();
		if (TextUtils.isEmpty(num)) {
			ToolToast.showShort("请输入购买组数");
			return;
		}
		String pwd = edtTxtOneTrandPwd.getText().toString();
		if (TextUtils.isEmpty(pwd)) {
			ToolToast.showShort("请输入交易密码");
			return;
		}

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/Discount/LuckyShopping");
		httpClient.url.append("/" + pwd);
		httpClient.url.append("/" + goodsId);
		httpClient.url.append("/" + num);
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {// 成功时
						ToolToast.showShort(response.getMessage());
						finish();
					} else {
						ToolToast.showShort(response.getMessage());
					}
				}
			}
		});
	}

	/**
	 * 参与红包竞拍
	 * 
	 * @author: bangwei.yang
	 */
	private void tryAreaShopping() {
		String num = edtTxtRedNum.getText().toString().trim();
		if (TextUtils.isEmpty(num)) {
			ToolToast.showShort("请输入购买组数");
			return;
		}
		if (TextUtils.isEmpty(hongbaoId)) {
			ToolToast.showShort("请选择要使用的红包");
			return;
		}

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/Discount/TryAreaShopping");
		httpClient.url.append("/" + goodsId);
		httpClient.url.append("/" + hongbaoId);
		// httpClient.url.append("/"
		// + tvRedMoney.getText().toString().replace("元", ""));
		httpClient.url.append("/" + num);
		httpClient.url.append("/" + num);
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {// 成功时
						ToolToast.showShort(response.getMessage());
						finish();
					} else {
						ToolToast.showShort(response.getMessage());
					}
				}
			}
		});
	}

	/**
	 * 参与一元竞拍
	 * 
	 * @author: bangwei.yang
	 */
	private void oneShopping() {
		String num = edtTxtOneNum.getText().toString().trim();
		if (TextUtils.isEmpty(num)) {
			ToolToast.showShort("请输入购买组数");
			return;
		}
		String pwd = edtTxtOneTrandPwd.getText().toString();
		if (TextUtils.isEmpty(pwd)) {
			ToolToast.showShort("请输入交易密码");
			return;
		}

		xHttpClient httpClient = new xHttpClient(act, false);
		httpClient.url.append("api/Discount/OneShopping");
		httpClient.url.append("/" + pwd);
		httpClient.url.append("/" + goodsOneID);
		httpClient.url.append("/" + num);
		httpClient.post(new xResopnse() {
			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				ProgressHelper.getInstance().cancel();
				BaseResponse response = JsonUtil.parseObject(act,
						responseInfo.result, BaseResponse.class);
				if (response != null) {
					if (response.getSuccess().equals("True")) {// 成功时
						ToolToast.showShort(response.getMessage());
						finish();
					} else {
						ToolToast.showShort(response.getMessage());
					}
				}
			}
		});
	}

	/**
	 * 重置单选按钮
	 * 
	 * @author: bangwei.yang
	 */
	private void resetRdo() {
		rdoBtn5.setChecked(false);
		rdoBtn10.setChecked(false);
		rdoBtn20.setChecked(false);
		rdoBtn30.setChecked(false);
		rdoBtn40.setChecked(false);
		rdoBtn50.setChecked(false);
	}

	/*
	 * 点击事件 (non-Javadoc)
	 * 
	 * @author: bangwei.yang
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivClose:// 关闭
			finish();
			break;
		case R.id.llytEnpty:// 关闭
			finish();
			break;
		case R.id.btnBidders:// 参与竞拍
			if (type == 56) {// 55幸运拍，56红包区，3一元拍
				tryAreaShopping();
				// luckyShopping();
				// } else if (type == 2) {
				//
			}
			if (type == 55 || type == 77) {
				luckyShopping();
			}
			if (type == 3) {
				oneShopping();
			}
			break;
		// 红包区
		case R.id.tvRedAdd:
			String tmp5 = edtTxtRedNum.getText().toString().trim();
			int num5 = Integer.valueOf(tmp5);
			if (num5 < 10000) {
				num5++;
				edtTxtRedNum.setText(String.valueOf(num5));
				// if (!tvRedMoney.getText().toString().equals("红包余额")) {
				// int redMoney = Integer.parseInt(tvRedMoney.getText()
				// .toString().replace("元", ""));
				// if (redMoney > num5) {
				// num5++;
				// edtTxtRedNum.setText(String.valueOf(num5));
				// tvRedMoney.setText(redMoney - Integer.valueOf(num5)
				// + "元");
				// } else {
				// ToolToast.showShort("红包余额不足");
				// }
				// }
			} else {
				ToolToast.showShort("本商品限购50组");
			}
			break;
		case R.id.tvRedReduce:
			String tmp6 = edtTxtRedNum.getText().toString().trim();
			int num6 = Integer.valueOf(tmp6);
			if (num6 > 1) {
				num6--;
				edtTxtRedNum.setText(String.valueOf(num6));
				// if (!tvRedMoney.getText().toString().equals("红包余额")) {
				// int redMoney = Integer.parseInt(tvRedMoney.getText()
				// .toString().replace("元", ""));
				// if (redMoney >= num6) {
				// num6--;
				// edtTxtRedNum.setText(String.valueOf(num6));
				// tvRedMoney.setText(redMoney + Integer.valueOf(tmp6)
				// + "元");
				// }
				// }
			} else {
				ToolToast.showShort("输入的组数不能小于1组");
			}
			break;

		// 一元拍点击事件
		case R.id.tvOneAdd:
			String tmp1 = edtTxtOneNum.getText().toString().trim();
			int num1 = Integer.valueOf(tmp1);
			if (num1 < 10000) {
				num1++;
				edtTxtOneNum.setText(String.valueOf(num1));
			} else {
				ToolToast.showShort("本商品限购10000组");
			}
			break;
		case R.id.tvOneReduce:
			String tmp2 = edtTxtOneNum.getText().toString().trim();
			int num2 = Integer.valueOf(tmp2);
			if (num2 > 1) {
				num2--;
				edtTxtOneNum.setText(String.valueOf(num2));
			} else {
				ToolToast.showShort("输入的组数不能小于1组");
			}
			break;
		case R.id.rdoBtn5:
			resetRdo();
			if (type == 3) {
				edtTxtOneNum.setText("5");
			} else if (type == 55 || type == 77) {
				edtTxtOneNum.setText("10");
			}
			rdoBtn5.setChecked(true);
			break;

		case R.id.rdoBtn10:
			resetRdo();
			if (type == 3) {
				edtTxtOneNum.setText("10");
			} else if (type == 55 || type == 77) {
				edtTxtOneNum.setText("20");
			}
			rdoBtn10.setChecked(true);
			break;
		case R.id.rdoBtn20:
			resetRdo();
			if (type == 3) {
				edtTxtOneNum.setText("20");
			} else if (type == 55 || type == 77) {
				edtTxtOneNum.setText("50");
			}
			rdoBtn20.setChecked(true);
			break;
		case R.id.rdoBtn30:
			resetRdo();
			if (type == 3) {
				edtTxtOneNum.setText("30");
			} else if (type == 55 || type == 77) {
				edtTxtOneNum.setText("100");
			}
			rdoBtn30.setChecked(true);
			break;
		case R.id.rdoBtn40:
			resetRdo();
			if (type == 3) {
				edtTxtOneNum.setText("40");
			} else if (type == 55 || type == 77) {
				edtTxtOneNum.setText("500");
			}
			rdoBtn40.setChecked(true);
			break;
		case R.id.rdoBtn50:
			resetRdo();
			if (type == 3) {
				edtTxtOneNum.setText("50");
			} else if (type == 55 || type == 77) {
				edtTxtOneNum.setText("1000");
			}
			rdoBtn50.setChecked(true);
			break;
		// 幸运拍点击事件
		case R.id.tvLuckAdd:// 幸运拍页面
			String tmp3 = edtTxtLuckNum.getText().toString().trim();
			int num3 = Integer.valueOf(tmp3);
			if (num3 < 10000) {
				num3++;
				edtTxtLuckNum.setText(String.valueOf(num3));
			} else {
				ToolToast.showShort("本商品限购10000组");
			}
			break;
		case R.id.tvLuckReduce:// 幸运拍页面
			String tmp4 = edtTxtLuckNum.getText().toString().trim();
			int num4 = Integer.valueOf(tmp4);
			if (num4 > 1) {
				num4--;
				edtTxtLuckNum.setText(String.valueOf(num4));
			} else {
				ToolToast.showShort("输入的组数不能小于1组");
			}
			break;
		// 忘记交易密码
		case R.id.tvOneForget:
			showActivity(act, RetrievePasswordActivity.class);
			break;
		case R.id.tvLuckForget:
			showActivity(act, RetrievePasswordActivity.class);
			break;
		default:
			break;
		}
	}

}
