package com.pkl.boot.common.config;

import java.util.concurrent.*;

public class ThreadPoolInstance {
    public static class ThreadPoolExcuter{
        public static ExecutorService executorService1 = new ThreadPoolExecutor(1,1,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        public static ExecutorService executorService2 = new ThreadPoolExecutor(2,8,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
    }
    public static ExecutorService getThreadPool1(){
        return ThreadPoolExcuter.executorService1;
    }
    public static ExecutorService getThreadPool2(){
        return ThreadPoolExcuter.executorService2;
    }
}
