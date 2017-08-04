package com.zerone.wgp.activities;

import android.provider.Settings;
import android.provider.Telephony;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.zerone.wgp.abclass.BaseFragmentActivity;
import com.zerone.wgp.fragment.CameraFragment;

/**
 * Created by D22391 on 2017/8/1.
 */

public class CameraActivity extends BaseFragmentActivity {
    private static final String TAG = "CameraActivity";

    @Override
    protected Fragment createFragment() {
        CameraActivity.myRun();
        return CameraFragment.newInstance();
    }

    public static void myRun() {
        Log.d(TAG, "myRun: " + Thread.currentThread().getId());
    }

}
