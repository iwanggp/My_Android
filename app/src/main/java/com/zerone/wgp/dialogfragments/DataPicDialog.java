package com.zerone.wgp.dialogfragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;//记得也从v4.app中导入DialogFragment
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.zerone.wgp.activities.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by D22391 on 2017/8/7.
 */

public class DataPicDialog extends DialogFragment {
	public static final String EXTRA_DATA = "com.zerone.wgp.fragment.datapicdialog";

	private static final String TAG = "DataPicDialog";
	private static final String ARG_DATE = "date";
	private DatePicker mDatePicker;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 用来接受传递过来的值方法
	 *
	 * @param date 接受传递的值
	 * @return
	 */
	public static DataPicDialog newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(ARG_DATE, date);
		DataPicDialog dataPicDialog = new DataPicDialog();
		dataPicDialog.setArguments(args);//这里设置参数值，后面在获得
		return dataPicDialog;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Date date = (Date) getArguments().getSerializable(ARG_DATE);//在这里获得传进来的值
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
//		calendar.add(Calendar.YEAR, 6);
		int year = calendar.get(Calendar.YEAR);
		int mouth = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		View v = LayoutInflater.from(getActivity())
				.inflate(R.layout.dialog_date, null);
		mDatePicker = v.findViewById(R.id.dialog_data);
		mDatePicker.init(year, mouth, day, null);
		return new AlertDialog.Builder(getActivity())
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
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						dismiss();
					}
				})
				.create();
	}

	private void sendResult(int resultCode, String date) {
		if (getTargetFragment() == null) {
			return;
		}
		Intent intent = new Intent();
		intent.putExtra(EXTRA_DATA, date);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
	}
}
