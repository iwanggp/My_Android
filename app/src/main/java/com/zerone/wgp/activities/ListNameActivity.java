package com.zerone.wgp.activities;

import android.support.v4.app.Fragment;

import com.zerone.wgp.abclass.BaseFragmentActivity;
import com.zerone.wgp.fragment.ListNameFragment;

/**
 * Created by Administrator on 2017/8/2.
 */

public class ListNameActivity extends BaseFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return ListNameFragment.newInstance();
    }
}
