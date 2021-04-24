package com.pkl.boot.service.impl;

import com.pkl.boot.entity.User;
import com.pkl.boot.mapper.HelloMapper;
import com.pkl.boot.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloServiceImpl implements IHelloService {
    @Autowired
    private HelloMapper helloMapper;
    @Override
    public List<User> getUser(String id) {
        return helloMapper.getUser(id);
    }
}
