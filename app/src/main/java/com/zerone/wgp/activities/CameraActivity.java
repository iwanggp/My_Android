package com.zerone.wgp.activities;

import android.support.v4.app.Fragment;

import com.zerone.wgp.abclass.BaseFragmentActivity;
import com.zerone.wgp.fragment.CameraFragment;

/**
 * Created by D22391 on 2017/8/1.
 */

public class CameraActivity extends BaseFragmentActivity {
	@Override
	protected Fragment createFragment() {
		return CameraFragment.newInstance();
	}
}
