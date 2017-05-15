package com.yfzx.library.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.Assert;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yfzx.library.widget.dialog.CustomDialog;
import com.yfzx.lwpai.R;
import com.yfzx.lwpai.util.ScreenHelper;
import com.yfzx.lwpai.util.ValidateHelper;

public final class ToolPhoto {

	private static final String TAG = ToolPhoto.class.getSimpleName();

	private static String filePath = null;

	public static final int CROP_REQUEST_CODE = 0xffff;

	private ToolPhoto() {
		// can't be instantiated
	}

	public static boolean takePhoto(final Context context, final String dir,
			final String filename, final int cmd) {
		filePath = "";

		final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (!ValidateHelper.isEmpty(dir) && !ValidateHelper.isEmpty(filename)) {
			if (dir.endsWith(File.separator)) {
				filePath = dir + filename;
			} else {
				filePath = dir + File.separator + filename;
			}

			final File cameraDir = new File(dir);
			if (!cameraDir.exists()) {
				cameraDir.mkdirs();
			}
			final File file = new File(filePath);
			final Uri outputFileUri = Uri.fromFile(file);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
		}
		try {
			((Activity) context).startActivityForResult(intent, cmd);

		} catch (final ActivityNotFoundException e) {
			return false;
		}
		return true;
	}

	public static String getResultPhotoPath(Context context,
			final Intent intent, final String dir) {
		if (filePath != null && new File(filePath).exists()) {
			return filePath;
		}

		return resolvePhotoFromIntent(context, intent, dir);
	}

	@TargetApi(Build.VERSION_CODES.KITKAT)
	@SuppressLint("NewApi")
	public static String resolvePhotoFromIntent(final Context ctx,
			final Intent data, String dir) {
		if (ctx == null || data == null || dir == null) {
			Log.e(TAG, "resolvePhotoFromIntent fail, invalid argument");
			return null;
		}
		final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
		final Uri uri = Uri.parse(data.toURI());
		if (isKitKat && DocumentsContract.isDocumentUri(ctx, uri)) {
			if (isExternalStorageDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];

				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/"
							+ split[1];
				}
			} else if (isDownloadsDocument(uri)) {
				final String id = DocumentsContract.getDocumentId(uri);
				final Uri contentUri = ContentUris.withAppendedId(
						Uri.parse("content://downloads/public_downloads"),
						Long.valueOf(id));
				return getDataColumn(ctx, contentUri, null, null);
			} else if (isMediaDocument(uri)) {
				final String docId = DocumentsContract.getDocumentId(uri);
				final String[] split = docId.split(":");
				final String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				final String selection = "_id=?";
				final String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(ctx, contentUri, selection, selectionArgs);
			}
		} else if ("content".equalsIgnoreCase(uri.getScheme())) {
			if (isGooglePhotosUri(uri))
				return uri.getLastPathSegment();
			return getDataColumn(ctx, uri, null, null);
		} else if ("file".equalsIgnoreCase(uri.getScheme())) {
			return uri.getPath();
		}

		return null;

		// if (!dir.endsWith(File.separator)) {
		// dir = dir + File.separator;
		// }
		//
		// String filePath = null;
		// final Uri uri = Uri.parse(data.toURI());
		// Cursor cu = ctx.getContentResolver().query(uri, null, null, null,
		// null);
		// if (cu != null && cu.getCount() > 0) {
		// try {
		// cu.moveToFirst();
		// final int pathIndex = cu.getColumnIndex(MediaColumns.DATA);
		// Log.e(TAG, "orition: " +
		// cu.getString(cu.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION)));
		// filePath = cu.getString(pathIndex);
		// Log.d(TAG, "photo from resolver, path:" + filePath);
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// } else if (data.getData() != null) {
		// filePath = data.getData().getPath();
		// if (!(new File(filePath)).exists()) {
		// filePath = null;
		// }
		// Log.d(TAG, "photo file from data, path:" + filePath);
		//
		// } else if (data.getAction() != null &&
		// data.getAction().equals("inline-data")) {
		//
		// try {
		// final String fileName = DateFormat.format("yyyy-MM-dd-HH-mm-ss",
		// System.currentTimeMillis()) + ".jpg";
		// filePath = dir + fileName;
		//
		// final Bitmap bitmap = (Bitmap) data.getExtras().get("data");
		// final File file = new File(filePath);
		// if (!file.exists()) {
		// file.createNewFile();
		// }
		//
		// BufferedOutputStream out;
		// out = new BufferedOutputStream(new FileOutputStream(file));
		// final int cQuality = 100;
		// bitmap.compress(Bitmap.CompressFormat.PNG, cQuality, out);
		// out.close();
		// Log.d(TAG, "photo image from data, path:" + filePath);
		//
		// } catch (final Exception e) {
		// e.printStackTrace();
		// }
		//
		// } else {
		// if (cu != null) {
		// cu.close();
		// cu = null;
		// }
		// Log.e(TAG, "resolve photo from intent failed");
		// return null;
		// }
		// if (cu != null) {
		// cu.close();
		// cu = null;
		// }
		// return filePath;
	}

	public static String getDataColumn(Context context, Uri uri,
			String selection, String[] selectionArgs) {

		Cursor cursor = null;
		final String column = "_data";
		final String[] projection = { column };

		try {
			cursor = context.getContentResolver().query(uri, projection,
					selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				final int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri
				.getAuthority());
	}

	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri
				.getAuthority());
	}

	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri
				.getAuthority());
	}

	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri
				.getAuthority());
	}

	public static void openAlbum(final Activity activity, final int cmd) {
		filePath = "";

		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
			activity.startActivityForResult(intent, cmd);
		} else {
			activity.startActivityForResult(intent, cmd);
		}

	}

	public static void selectPicture(final Activity activity, final int cmd) {
		final CustomDialog dialog = new CustomDialog(activity,
				R.layout.dialog_picture,
				ScreenHelper.getScreenWidthPix(activity) * 3 / 4,
				ViewGroup.LayoutParams.WRAP_CONTENT);

		TextView txvTakePhoto = (TextView) dialog
				.findViewById(R.id.txv_take_photo);
		TextView txvAlbum = (TextView) dialog.findViewById(R.id.txv_album);
		TextView txvCancel = (TextView) dialog.findViewById(R.id.txv_cancel);
		txvTakePhoto.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				takePhoto(activity, ToolStorage.getImageDir(activity),
						System.currentTimeMillis() + ".jpg", cmd);
				dialog.dismiss();
			}
		});

		txvAlbum.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				openAlbum(activity, cmd);
				dialog.dismiss();
			}
		});
		txvCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	/**
	 * @Title: startImageAction
	 * @return void
	 * @throws
	 */
	public static void startCorpImage(Activity activity, String filePath,
			int outputX, int outputY) {
		Intent intent = null;
		intent = new Intent("com.android.camera.action.CROP");
		Uri uri = Uri.fromFile(new File(filePath));
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		intent.putExtra("scale", true);
		// intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("return-data", true);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // no face detection
		activity.startActivityForResult(intent, CROP_REQUEST_CODE);
	}

	private static final int MAX_DECODE_PICTURE_SIZE = 1920 * 1440;

	public static Bitmap extractThumbNail(final String path, final int height,
			final int width, final boolean crop) {
		Assert.assertTrue(path != null && !path.equals("") && height > 0
				&& width > 0);

		BitmapFactory.Options options = new BitmapFactory.Options();

		try {
			options.inJustDecodeBounds = true;
			Bitmap tmp = BitmapFactory.decodeFile(path, options);
			if (tmp != null) {
				tmp.recycle();
				tmp = null;
			}

			Log.d(TAG, "extractThumbNail: round=" + width + "x" + height
					+ ", crop=" + crop);
			final double beY = options.outHeight * 1.0 / height;
			final double beX = options.outWidth * 1.0 / width;
			Log.d(TAG, "extractThumbNail: extract beX = " + beX + ", beY = "
					+ beY);
			options.inSampleSize = (int) (crop ? (beY > beX ? beX : beY)
					: (beY < beX ? beX : beY));
			if (options.inSampleSize <= 1) {
				options.inSampleSize = 1;
			}

			// NOTE: out of memory error
			while (options.outHeight * options.outWidth / options.inSampleSize > MAX_DECODE_PICTURE_SIZE) {
				options.inSampleSize++;
			}

			int newHeight = height;
			int newWidth = width;
			if (crop) {
				if (beY > beX) {
					newHeight = (int) (newWidth * 1.0 * options.outHeight / options.outWidth);
				} else {
					newWidth = (int) (newHeight * 1.0 * options.outWidth / options.outHeight);
				}
			} else {
				if (beY < beX) {
					newHeight = (int) (newWidth * 1.0 * options.outHeight / options.outWidth);
				} else {
					newWidth = (int) (newHeight * 1.0 * options.outWidth / options.outHeight);
				}
			}

			options.inJustDecodeBounds = false;

			Log.i(TAG, "bitmap required size=" + newWidth + "x" + newHeight
					+ ", orig=" + options.outWidth + "x" + options.outHeight
					+ ", sample=" + options.inSampleSize);
			Bitmap bm = BitmapFactory.decodeFile(path, options);
			if (bm == null) {
				Log.e(TAG, "bitmap decode failed");
				return null;
			}

			Log.i(TAG,
					"bitmap decoded size=" + bm.getWidth() + "x"
							+ bm.getHeight());
			final Bitmap scale = Bitmap.createScaledBitmap(bm, newWidth,
					newHeight, true);
			if (scale != null) {
				// bm.recycle();
				bm = scale;
			}

			if (crop && !bm.isRecycled()) {
				final Bitmap cropped = Bitmap.createBitmap(bm,
						(bm.getWidth() - width) >> 1,
						(bm.getHeight() - height) >> 1, width, height);
				if (cropped == null) {
					return bm;
				}

				// bm.recycle();
				bm = cropped;
				Log.i(TAG,
						"bitmap croped size=" + bm.getWidth() + "x"
								+ bm.getHeight());
			}
			return bm;

		} catch (final OutOfMemoryError e) {
			Log.e(TAG, "decode bitmap failed: " + e.getMessage());
			options = null;
		}

		return null;
	}
	
	public static String saveThumbNail(Activity context, String filePath, final int height, final int width) {

		if (TextUtils.isEmpty(filePath)) {
			return "";
		}

		String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);
		String thumbName = ToolStorage.getThumbDir(context) + File.separator + "Thumb_" + fileName;

		File file = new File(thumbName);
		FileOutputStream fos = null;
		Bitmap bitmap = extractThumbNail(filePath, height, width, false);

//		int degree = readPictureDegree(fileName);
//		bitmap = rotaingImageView(degree, bitmap);

		if (bitmap == null) {
			return "";
		}
		try {
			fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 把数据写入文件

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		} finally {
			try {
				if (fos != null) {
					fos.flush();
					fos.close();
				}
				if (bitmap != null && !bitmap.isRecycled()) {
					bitmap.recycle();
					bitmap = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return thumbName;

	}
}
