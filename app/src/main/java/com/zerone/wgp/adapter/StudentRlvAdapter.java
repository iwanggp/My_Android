package com.zerone.wgp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.domain.Student;
import com.zerone.wgp.viewholders.StudentViewHolder;

import java.util.List;


/**
 * Created by Administrator on 2017/8/2.
 * 这里面主要是重写三个方法
 */
public class StudentRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Student> mStudentList;
    private LayoutInflater mLayoutInflater;
    private static final String TAG = "StudentRlvAdapter";

    public StudentRlvAdapter(Context context, List<Student> mStudentList) {
        this.mContext = context;
        this.mStudentList = mStudentList;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_list_student, parent, false);
        StudentViewHolder mStudentViewHolder = new StudentViewHolder(view, mContext);
        return mStudentViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + mStudentList.get(position).getPhone());
        ((StudentViewHolder) holder).bind(mStudentList.get(position));

    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }
}
