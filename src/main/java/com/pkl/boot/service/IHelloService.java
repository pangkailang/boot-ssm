package com.pkl.boot.service;

import com.pkl.boot.entity.User;

import java.util.List;

public interface IHelloService {
    List<User> getUser(String id);
}
