package com.pkl.boot.entity;

import javax.jws.soap.SOAPBinding;

public class SubUser extends User {
    public SubUser() {
    }

    @Override
    public void getUser() {
        System.out.println("获取到子元素");
    }
}
