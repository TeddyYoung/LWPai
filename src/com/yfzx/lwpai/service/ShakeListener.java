package com.yfzx.lwpai.service;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 一个检测手机摇晃的监听器 加速度传感器 values[0]： x-axis 方向加速度 values[1]： y-axis 方向加速度
 * values[2]： z-axis 方向加速度
 */
/**
 * 传感器
 * 
 * @author: songbing.zhou
 * @version Revision: 0.0.1
 * @Date: 2015-8-26
 */
public class ShakeListener implements SensorEventListener {
	// 传感器管理器
	private SensorManager sensorManager;
	// 传感器
	private Sensor sensor;
	// 重力感应监听器
	private OnShakeListener onShakeListener;
	// 上下文
	private Context mContext;

	// 手机上一个位置时重力感应坐标

	// 构造器
	public ShakeListener(Context c) {
		// 获得监听对象
		mContext = c;
		start();
	}

	// 开始
	public void start() {
		// 获得传感器管理器
		sensorManager = (SensorManager) mContext
				.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager != null) {
			// 获得重力传感器
			sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		}
		// 注册
		if (sensor != null) {

			// 还有SENSOR_DELAY_UI、SENSOR_DELAY_FASTEST、SENSOR_DELAY_GAME等，
			// 根据不同应用，需要的反应速率不同，具体根据实际情况设定
			sensorManager.registerListener(this, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
		}

	}

	// 停止检测
	public void stop() {
		sensorManager.unregisterListener(this);
	}

	// 设置重力感应监听器
	public void setOnShakeListener(OnShakeListener listener) {
		onShakeListener = listener;
	}

	// 重力感应器感应获得变化数据
	public void onSensorChanged(SensorEvent event) {
		// 获得x,y,z坐标
		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];
		float speed = (float) Math.sqrt(x * x + y * y + z * z);

		// 达到速度阀值，发出提示
		if (speed >= 20) {
			onShakeListener.onShake();
		}
	}

	// 当传感器精度改变时回调该方法
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	// 摇晃监听接口
	public interface OnShakeListener {
		public void onShake();
	}

}