package com.zerone.wgp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.adapter.PictureAdapter;
import com.zerone.wgp.domain.ImageModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */

public class PictureFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private static List<ImageModel> mlist;

    public static PictureFragment newInstance(List<ImageModel> list) {
        mlist = list;
        return new PictureFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.picture_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));//设置一个新的布局每行显示3个
        mRecyclerView.setAdapter(new PictureAdapter(getActivity(),mlist));
        return view;
    }
}
