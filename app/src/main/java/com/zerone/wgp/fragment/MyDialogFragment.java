package com.zerone.wgp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mylhyl.circledialog.CircleDialog;
import com.zerone.wgp.activities.R;
import com.zerone.wgp.dialogfragments.BottomDialog;
import com.zerone.wgp.dialogfragments.DatePicDialog;

import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by D22391 on 2017/8/7.
 */

public class MyDialogFragment extends Fragment {
	private Button bt_dialog, bt_ad, bt_confirm, bt_bottom;
	private static final String TAG = "MyDialogFragment";
	private static final String DIALOG_DATE = "DataPicDialog";
	private TextView tv_date;
	private static final int REQUEST_CODE = -100;

	public static MyDialogFragment newInstance() {
		return new MyDialogFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_fragment, container, false);
		bt_dialog = view.findViewById(R.id.bt_dialog);
		tv_date = view.findViewById(R.id.tv_date);

		bt_ad = view.findViewById(R.id.bt_ad);
		bt_confirm = view.findViewById(R.id.bt_confirm);
		bt_bottom = view.findViewById(R.id.bt_bottom);
		bt_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
						.setTitleText("Are you sure?")
						.setContentText("Won't be able to recover this file!")
						.setConfirmText("Yes,delete it!")
						.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(SweetAlertDialog sDialog) {
								// reuse previous dialog instance
								sDialog.setTitleText("Deleted!")
										.setContentText("Your imaginary file has been deleted!")
										.setConfirmText("OK")
										.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
											@Override
											public void onClick(SweetAlertDialog sweetAlertDialog) {
												Toast.makeText(getActivity(), "hhhh", Toast.LENGTH_SHORT).show();
											}
										})
										.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
							}
						})
						.show();
			}
		});
		bt_ad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
						.setTitleText("Oops...")
						.setContentText("Something went wrong!")
						.show();
			}
		});
		bt_dialog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				DatePicDialog dataPicDialog = DatePicDialog.showDialog(fragmentManager, getActivity(),new Date());
				//设置目标Fragment和相应的请求码
				dataPicDialog.setTargetFragment(MyDialogFragment.this, REQUEST_CODE);
			}
		});
		bt_bottom.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FragmentManager fragmentManager = getFragmentManager();
				BottomDialog bottomDialog = BottomDialog.newInstance();
				bottomDialog.show(fragmentManager, "ssss");
			}
		});
		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != Activity.RESULT_OK) {
			return;
		}
		if (requestCode == REQUEST_CODE) {
			String date = (String) data.getSerializableExtra(DatePicDialog.EXTRA_DATA);
			tv_date.setText(date);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume: ..run");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop: ..run");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause: ....run");
	}
}
