package com.pkl.boot.mapper;

import com.pkl.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloMapper {
    User getUser(String id);
}
