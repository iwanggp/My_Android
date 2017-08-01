package com.zerone.wgp.utils;

import com.zerone.wgp.domain.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/2.
 * 一个管理Student的工具类
 */

public class StudentLab {
    private static List<Student> mStudentList;

    public static List<Student> getStudentList() {
        mStudentList = new ArrayList<Student>();
        for (int i = 0; i < 10000; i++) {
            Student student = new Student("wang" + i, "186658269" + i);
            mStudentList.add(student);
        }
        return mStudentList;
    }


}
