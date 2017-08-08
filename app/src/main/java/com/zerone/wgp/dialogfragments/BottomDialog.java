package com.zerone.wgp.dialogfragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.zerone.wgp.activities.R;

import java.util.zip.Inflater;

/**
 * Created by D22391 on 2017/8/8.
 */

public class BottomDialog extends DialogFragment {
	private static final String TAG = "BottomDialog";
	private AlertDialog dialog;

	public static BottomDialog newInstance() {
		Bundle args = new Bundle();
		BottomDialog bottomDialog = new BottomDialog();
		bottomDialog.setArguments(args);
		return bottomDialog;

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_bottom, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setView(view);
		dialog = builder.create();
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		// 设置宽度为屏宽、靠近屏幕底部。
		Window window = dialog.getWindow();
		WindowManager.LayoutParams wlp = window.getAttributes();
		wlp.gravity = Gravity.BOTTOM;
		window.setAttributes(wlp);

		return dialog;
	}

	@Override
	public void onStart() {
		super.onStart();
		Dialog dialog = getDialog();
		if (null != dialog) {
			dialog.getWindow().setLayout(-1, -2);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
