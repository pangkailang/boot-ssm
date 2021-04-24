package com.pkl.boot.mapper;

import com.pkl.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelloMapper {
    List<User> getUser(String id);
}
