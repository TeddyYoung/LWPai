package com.yfzx.lwpai;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.sql.Date;

import android.os.Environment;
import android.text.format.DateFormat;

import com.yfzx.library.config.SysEnv;
import com.yfzx.lwpai.contants.FileContants;

/**
 * 全局异常捕获类
 * @author Shawn
 */
public class MyExceptionHandler implements UncaughtExceptionHandler {
	private UncaughtExceptionHandler exceptionHandler;

	public MyExceptionHandler() {
		exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		writeToFile(
				writer.toString() + "\n" + thread.getName() + "\n"
						+ thread.toString(), FileContants.DIR_LOG
						+ "lwpai_trace.txt");
		exceptionHandler.uncaughtException(thread, ex);
	}

	
	private void writeToFile(String stacktrace, String filename) {
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_UNMOUNTED)) {
				return;
			}
			File targetFile = new File(filename);
			File fileDir = new File(FileContants.DIR_LOG);
			if (targetFile.exists()) {
				File newPath = new File(FileContants.DIR_LOG
						+ "/lwpai_trace" + (fileDir.list().length + 1)
						+ ".txt");
				targetFile.renameTo(newPath);
			}
			FileWriter fileWriter = new FileWriter(targetFile);
			BufferedWriter bos = new BufferedWriter(fileWriter);
			bos.write(android.os.Build.VERSION.SDK_INT + "\n");
			bos.write(android.os.Build.MODEL + "\n");
			bos.write("APP :" + SysEnv.getVersionName() + "\n");
			bos.write("time :"
					+ DateFormat.format("yyyy-MM-dd kk:mm:ss",
							new Date(System.currentTimeMillis())) + "\n");
			bos.write(stacktrace);
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
