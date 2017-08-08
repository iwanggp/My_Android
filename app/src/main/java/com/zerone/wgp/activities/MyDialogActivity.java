package com.zerone.wgp.activities;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.zerone.wgp.abclass.BaseFragmentActivity;
import com.zerone.wgp.fragment.MyDialogFragment;

/**
 * Created by D22391 on 2017/8/7.
 */

public class MyDialogActivity extends BaseFragmentActivity {
	private static final String TAG = "MyDialogActivity";

	@Override
	protected Fragment createFragment() {
		return MyDialogFragment.newInstance();
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause: ...run");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart: ...run");

	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume: ....run");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart: ....run");
	}
}
