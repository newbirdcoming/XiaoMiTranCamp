package com.example.demo.one.test4;

public class ThreadLocalTest {
    private static final ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);


    public static void main(String[] args) {
        threadLocal.set(1000);
        new Thread(()->{
            Integer integer = threadLocal.get();
            threadLocal.set(100);
            integer = threadLocal.get();
            System.out.println("thread:"+Thread.currentThread().getName()+"valie:"+integer);
        }).start();
        new Thread(()->{
            Integer integer = threadLocal.get();
            threadLocal.set(300);
            integer = threadLocal.get();
            System.out.println("thread:"+Thread.currentThread().getName()+"valie:"+integer);
        }).start();

        System.out.println(threadLocal.get());
    }
}
