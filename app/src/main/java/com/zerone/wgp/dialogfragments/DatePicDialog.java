package com.zerone.wgp.dialogfragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;//记得也从v4.app中导入DialogFragment
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.zerone.wgp.activities.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by D22391 on 2017/8/7.
 */

public class DatePicDialog extends DialogFragment {
	public static final String EXTRA_DATA = "com.zerone.wgp.fragment.DatePicDialog";

	private static final String TAG = "DatePicDialog";
	private static final String ARG_DATE = "date";
	private DatePicker mDatePicker;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private AlertDialog dialog;

	/**
	 * 用来接受传递过来的值方法
	 *
	 * @param date 接受传递的值
	 * @return
	 */
	public static DatePicDialog newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(ARG_DATE, date);
		DatePicDialog DatePicDialog = new DatePicDialog();
		DatePicDialog.setArguments(args);//这里设置参数值，后面在获得
		return DatePicDialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Log.d(TAG, "onCreateDialog: ...run");
		Date date = (Date) getArguments().getSerializable(ARG_DATE);//在这里获得传进来的值
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, 6);
		int year = calendar.get(Calendar.YEAR);
		int mouth = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		View v = LayoutInflater.from(getActivity())
				.inflate(R.layout.dialog_date, null);
		mDatePicker = v.findViewById(R.id.dialog_data);
		mDatePicker.init(year, mouth, day, null);
		return dialog = new AlertDialog.Builder(getActivity())
				.setView(v)
				.setTitle("Haoxiaoqian")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						int year = mDatePicker.getYear();
						int month = mDatePicker.getMonth();
						int day = mDatePicker.getDayOfMonth();
						Log.d(TAG, "year" + year);
						Log.d(TAG, "month" + month);
						Log.d(TAG, "day" + day);
						Date date = new GregorianCalendar(year, month, day).getTime();
						Log.d(TAG, "yyyy--MM--dd" + sdf.format(date));
						sendResult(Activity.RESULT_OK, sdf.format(date));
					}
				})
				.setNeutralButton("sss", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {

					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						dismiss();
					}
				})
				.setCancelable(true)
				.create();
	}


	private void sendResult(int resultCode, String date) {
		if (getTargetFragment() == null) {
			Log.d(TAG, "sendResult: ..run");
			return;
		}
		Intent intent = new Intent();
		intent.putExtra(EXTRA_DATA, date);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
	}

	/**
	 * 防止Dialog多次弹出
	 *
	 * @param fragmentManager fragment管理者
	 * @param activity        Activity上下文
	 * @param date            附属Fragment要传递的值
	 * @return
	 */
	public static DatePicDialog showDialog(FragmentManager fragmentManager, FragmentActivity activity, Date date) {
		DatePicDialog DatePicDialog =
				(DatePicDialog) fragmentManager.findFragmentByTag(TAG);
		if (null == DatePicDialog) {
			DatePicDialog = newInstance(date);
		}

		if (!activity.isFinishing()
				&& null != DatePicDialog
				&& !DatePicDialog.isAdded()) {
			fragmentManager.beginTransaction()
					.add(DatePicDialog, TAG)
					.commitAllowingStateLoss();
		}

		return DatePicDialog;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView: ..run");
		View view = inflater.inflate(R.layout.camera_fragment, container, false);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate: ....run");
	}

	@Override
	public void onStart() {
		super.onStart();
		Log.d(TAG, "onStart: ...run");
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume: ..run");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop: ...run");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy: ...run");
	}
}
