package com.zerone.wgp.activities;

import android.support.v4.app.Fragment;

import com.zerone.wgp.abclass.BaseFragmentActivity;
import com.zerone.wgp.fragment.MyDialogFragment;

/**
 * Created by D22391 on 2017/8/7.
 */

public class MyDialogActivity extends BaseFragmentActivity {
	@Override
	protected Fragment createFragment() {
		return MyDialogFragment.newInstance();
	}
}
