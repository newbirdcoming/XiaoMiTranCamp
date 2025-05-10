package com.example.demo.one.test8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * oom
 */
public class Main {
    public static void main(String[] args) {
        Main main=new Main();
        // 创建缓存线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        // 持续提交耗时任务，迫使线程池创建大量线程
        for (int i = 0; ; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("任务 " + taskId + " 由线程 " + Thread.currentThread().getName() + " 执行");
//                创建对象
                for (int i1 = 0;; i1++) {
                    Student student = main.new Student("name:");
                }
//                try {
//                    // 模拟耗时操作，防止线程回收
//                    Thread.sleep(Long.MAX_VALUE);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }
//                return null;
            });
        }
    }



    class Student{
        private String name;
        public Student(String name){
            this.name=name;
        }
    }
}
