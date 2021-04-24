package com.pkl.boot.common.test;

import com.pkl.boot.entity.User;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EncodePasswordTest {
    /**
     * bcrypt加密比较可靠
     */
    @Test
    public void encodePassword(){
        String pwd = "123456";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(pwd);
        System.out.println("第一次加密的值为："+encode);
        boolean matchs = bCryptPasswordEncoder.matches(pwd, encode);
        System.out.println("第一次验证是："+ matchs);
        encode = bCryptPasswordEncoder.encode(pwd);
        System.out.println("第二次加密的值为:"+encode);
        matchs = bCryptPasswordEncoder.matches(pwd, encode);
        System.out.println("第二次验证是："+matchs);

    }
}
