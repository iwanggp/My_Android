package com.zerone.wgp.domain;

import java.util.UUID;

/**
 * Created by Administrator on 2017/8/2.
 * 定义一个Student的bean
 */

public class Student {
    private UUID ID;

    private String name;
    private String phone;

    public Student(String name, String phone) {
        ID = UUID.randomUUID();
        this.name = name;
        this.phone = phone;
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
