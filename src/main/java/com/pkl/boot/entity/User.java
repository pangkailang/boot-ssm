package com.pkl.boot.entity;

import com.pkl.boot.common.config.FirstGroup;
import com.pkl.boot.common.config.TwoGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ApiModel(value = "用户对象")
public class User {
    public User() {
        getUser();
    }
    @NotNull
    @ApiModelProperty(value="用户id",example = "1211e1wdd3223231esad12")
    private String id;

    @ApiModelProperty(value="用户年龄",example = "12")
    private String age;

    @ApiModelProperty(value="用户姓名",example = "王龙")
    private String name;

    @ApiModelProperty(value="部门列表")
    private List<Dept> listDept;
    public void getUser(){
        System.out.println("获取到父元素。。。。。。。");
    }

    public User( String id, String age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
    public User( String id, String age) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", listDept=" + listDept +
                '}';
    }
}
