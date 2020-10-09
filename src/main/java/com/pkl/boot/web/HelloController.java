package com.pkl.boot.web;

import com.pkl.boot.entity.User;
import com.pkl.boot.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private IHelloService helloService;
    @GetMapping("web")
    public String hello(){
        return "访问web成功....";
    }
    @GetMapping(value="/user/{id}")
    public User getUser(@PathVariable("id") String id){
        return helloService.getUser(id);
    }
}
