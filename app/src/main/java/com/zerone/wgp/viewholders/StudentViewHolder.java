package com.zerone.wgp.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.zerone.wgp.activities.R;
import com.zerone.wgp.domain.Student;

/**
 * Created by Administrator on 2017/8/2.
 * 实际是ViewHolder要做的事情也很少就是托管一下主要的布局而已
 */

public class StudentViewHolder extends RecyclerView.ViewHolder {
    private Context mContext;
    private static final String TAG = "StudentViewHolder";
    private TextView tv_id, tv_name, tv_phone;

    public StudentViewHolder(View itemView, Context context) {
        super(itemView);
        this.mContext = context;
        tv_id = itemView.findViewById(R.id.tv_id);
        tv_name = (TextView) itemView.findViewById(R.id.tv_name);
        tv_phone = (TextView) itemView.findViewById(R.id.tv_phone);
    }

    public void bind(Student student) {
        tv_id.setText(student.getID().toString().trim());
        Log.d(TAG, "bind: " + student.getName());
        tv_name.setText(student.getName());
        tv_phone.setText(student.getPhone().toString().trim());

    }
}
