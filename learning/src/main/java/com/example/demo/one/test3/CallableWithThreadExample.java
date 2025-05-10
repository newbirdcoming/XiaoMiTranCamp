package com.example.demo.one.test3;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 定义一个实现Callable接口的类
class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 模拟一些耗时操作
        Thread.sleep(2000);
        return 42;
    }
}

public class CallableWithThreadExample {
    public static void main(String[] args) {
        // 创建Callable任务
        MyCallable myCallable = new MyCallable();
        // 使用FutureTask将Callable任务适配为Runnable
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        // 创建Thread并传入FutureTask
        Thread thread = new Thread(futureTask);
        // 启动线程
        thread.start();

        try {
            // 获取Callable任务的执行结果，此方法会阻塞直到任务完成
            Integer result = futureTask.get();
            System.out.println("任务执行结果: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}