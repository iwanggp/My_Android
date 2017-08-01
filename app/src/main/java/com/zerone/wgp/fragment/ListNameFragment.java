package com.zerone.wgp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.adapter.StudentRlvAdapter;
import com.zerone.wgp.domain.Student;
import com.zerone.wgp.utils.StudentLab;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Administrator on 2017/8/2.
 */

public class ListNameFragment extends Fragment {
    private List<Student> mStudentList;
    private RecyclerView mRecyclerView;
    private StudentRlvAdapter mStudentRlvAdapter;

    public static ListNameFragment newInstance() {
        return new ListNameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStudentList = StudentLab.getStudentList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_name_fragment, container, false);
        mRecyclerView = view.findViewById(R.id.list_student_rlv);//找到RecyclerView后要立马设置其布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mStudentRlvAdapter = new StudentRlvAdapter(getActivity(), mStudentList);
        mRecyclerView.setAdapter(mStudentRlvAdapter);
        return view;
    }
}
