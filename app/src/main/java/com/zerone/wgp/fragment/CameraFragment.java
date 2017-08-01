package com.zerone.wgp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.utils.DateString;
import com.zerone.wgp.utils.PictureUtils;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by D22391 on 2017/8/1.
 */
public class CameraFragment extends Fragment {
	private ImageView iv_photo;
	private ImageButton ib_camera;
	private Context mContext;
	private static final String TAG = "CameraFragment";
	private File mPhotoFile;
	private static final int REQUEST_PHOTO = 2;

	public static CameraFragment newInstance() {
		return new CameraFragment();
	}

	/**
	 * 这里强调一点是onCreate方法是较onCreateView方法先执行的
	 *
	 * @param savedInstanceState
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		mPhotoFile = getPhotoFile();
		Log.d(TAG, "onCreate: run");
//		updatePhotoView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: run");
		View view = inflater.inflate(R.layout.camera_fragment, container, false);
		final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//触发相机应用的intent

		ib_camera = view.findViewById(R.id.ib_camera);
		ib_camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Uri uri = FileProvider.getUriForFile(mContext, "com.zerone.wgp.activities.fileprovider",
						mPhotoFile);//把本地文件路径转换为相机能看见的Uri形式。
				captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);
				List<ResolveInfo> cameraActivities = getActivity()
						.getPackageManager().queryIntentActivities(captureImage
								, PackageManager.MATCH_DEFAULT_ONLY);
				for (ResolveInfo activity : cameraActivities) {
					mContext.grantUriPermission(activity.activityInfo.packageName, uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
				}
				startActivityForResult(captureImage, REQUEST_PHOTO);
				Log.d(TAG, "onClick: run");

			}
		});
		iv_photo = (ImageView) view.findViewById(R.id.photo_iv);
		updatePhotoView();

		return view;
	}

	public File getPhotoFile() {
		File fileDir = mContext.getFilesDir();
		Log.i(TAG, "getPhotoFile: " + DateString.lshFormat(new Date()));
		return new File(fileDir, DateString.lshFormat(new Date()) + ".jpg");

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		if (requestCode == REQUEST_PHOTO) {
			Log.d(TAG, "onActivityResult: run");
			Uri uri = FileProvider.getUriForFile(mContext, "com.zerone.wgp.activities.fileprovider"
					, mPhotoFile);
			getActivity().revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
			updatePhotoView();
//
		}

	}

	//刷新图片显示的方法
	private void updatePhotoView() {
		if (mPhotoFile == null || !(mPhotoFile.exists())) {
			iv_photo.setImageDrawable(null);
		} else {
			Bitmap bitmap = PictureUtils.getScaleBitmap(mPhotoFile.getPath(), getActivity());
			Log.d(TAG, "updatePhotoView: Bitmap");
			iv_photo.setImageBitmap(bitmap);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
