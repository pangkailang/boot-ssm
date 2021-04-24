package com.pkl.boot.common.test;

import com.pkl.boot.entity.User;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * lamda表达式，可以简化代码，但是适用于简单的处理，如果遇到复杂的业务场景比如各种嵌套循环还是用传统的书写，可读性，可调节性高一点
 * lamda内部不支持异常抛出，如果需要异常抛出，请在里面try catch之后，记录错误状态，在外面进行统一异常处理
 *
 */
public class LamdaTest {
    /**
     * list转map
     */
    @Test
    public void listToMap(){
        List<User> list = new ArrayList<>();
        list.add(new User("111", "23", "小龙"));
        list.add(new User("222", "24", "小红"));
        list.add(new User("333", "25", "小蓝"));
        list.add(new User("444", "26", "小华"));
        list.add(new User("555", "27", "小杨"));
        list.add(new User("111", "28", "小明"));
        Stream<User> stream  = list.stream();//把集合转换为流,这里要加泛型，否则下面返回的是一个object参数
        Map<String, String> result1 =  stream.collect(Collectors.toMap(User::getId, User::getName));//会产生Duplicate key
        Map<String, String> result2 =  stream.collect(Collectors.toMap(User::getId, User::getName,(value1,value2)->value1,HashMap::new));//不会产生Duplicate key
        System.out.println(result1);
    }

    /**
     * map转list
     */
    @Test
    public void mapToList(){
        Map<String,String> map = new HashMap();
        map.put("111", "小明");
        map.put("222", "小兰");
        List<User> list = map.entrySet().stream().map(e -> new User(e.getKey(),e.getValue())).collect(Collectors.toList());
        list.stream().forEach(u-> System.out.println(u.getName()));
    }

    /**
     * peek操作list
     */
    @Test
    public void peekToList(){
        List<User> list = new ArrayList<>();
        list.add(new User("111", "23", "小龙"));
        list.add(new User("222", "24", "小红"));
        list.add(new User("333", "25", "小蓝"));
        list.add(new User("444", "26", "小华"));
        list.add(new User("555", "27", "小杨"));
        list.add(new User("111", "28", "小明"));

        List<User> maped = list.stream()
                .peek(e -> {//peek函数是一种特殊的map函数，当函数没有返回值或者参数就是返回值的时候可以使用peek函数。
                    e.setAge(e.getAge() + 1);
                    e.setName(e.getName().equals("小龙")?"小龙龙":"小虫虫");
                }).collect(Collectors.toList());
    }

    /**
     * filter
     */
    @Test
    public void filteList(){
        List<User> list = new ArrayList<>();
        list.add(new User("111", "23", "小龙"));
        list.add(new User("222", "24", "小红"));
        list.add(new User("333", "25", "小蓝"));
        list.add(new User("444", "26", "小华"));
        list.add(new User("555", "27", "小杨"));
        list.add(new User("111", "28", "小明"));

        List<User> resultList = list.stream()
                .filter(e -> "小龙".equals(e.getName())).collect(Collectors.toList());
        System.out.println(resultList);
    }

    /**
     * sort
     */
    @Test
    public void sortList(){
        List<User> list = new ArrayList<>();
        list.add(new User("111", "23", "小龙"));
        list.add(new User("222", "24", "小红"));
        list.add(new User("333", "25", "小蓝"));
        list.add(new User("444", "26", "小华"));
        list.add(new User("555", "27", "小杨"));
        list.add(new User("666", "28", "小明"));

        List<User> resultList = list.stream()
                .sorted(Comparator.comparing(User::getId).reversed()).collect(Collectors.toList());
        System.out.println(resultList);
    }

    /**
     *map可以对管道流中的数据进行转换操作，但是如果管道中还有管道该如何处理？即：如何处理二维数组及二维集合类。
     * 那么用map就不能够处理了，需要用到flatMap
     */
    @Test
    public void flatMap(){
        List<String> words = Arrays.asList("hello", "word");
        words.stream()
                .map(w -> Arrays.stream(w.split("")))    //[[h,e,l,l,o],[w,o,r,l,d]]
                .forEach(System.out::println);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        words.stream().flatMap(w -> Arrays.stream(w.split(""))).forEach(System.out::println);
    }
    /**
     * 新建线程处理任务
     */
    @Test
    public void handleThread(){
        //这段代码比new Runnable要简单很多
        new Thread(() -> doSomething()).start();
    }
    public void doSomething(){
        //doSomething
        System.out.println(123);
    }

    /**
     * 中断
     */
    @Test
    public void returnList(){
        List<User> list = new ArrayList<>();
        list.add(new User("111", "23", "小龙"));
        list.add(new User("222", "24", "小红"));
        list.add(new User("333", "25", "小蓝"));
        list.add(new User("444", "26", "小华"));
        list.add(new User("555", "27", "小杨"));
        list.add(new User("666", "28", "小明"));

        list.stream().map(u->u.getName()).forEach((e)->doSth(e));//这种循环是无法通过内部进行完整return，只能return当前循环
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        list.forEach(u->doSth(u.getName()));//这种循环是无法通过内部进行return，只能return当前循环
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        list.stream().anyMatch(u -> doSthBoolean(u.getName()));//可以完整return当前循环
        System.out.println("************************************");
        list.stream().anyMatch(u->doSthBreak(list,u));
    }
    public void doSth(String e){
        if("小红".equals(e)){
            return;
        }
        System.out.println(e);
    }
    public Boolean doSthBoolean(String e) {
        if ("小杨".equals(e)) {
            return false;
        }
        System.out.println(e);
        return true;
    }
    public Boolean doSthBreak(List<User> list ,User user) {
        System.out.println("11111");
        Boolean result = list.stream().anyMatch(u->doSthBoolean(u.getName()));
        System.out.println(result);
        return true;
    }
}
