package com.example.demo.one.test6;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建线程池
 */
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= new ThreadPoolExecutor(
                2,
                5,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );

        for (int i = 0; i < 15; i++) {
            int taskId=i;
            executor.submit(()->{
                System.out.println("线程"+Thread.currentThread().getName()+"执行任务"+taskId);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
//        关闭线程池
        executor.shutdown();
    }
}
