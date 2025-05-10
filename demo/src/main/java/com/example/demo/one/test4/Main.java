package com.example.demo.one.test4;


/**
 * 锁：Synchornized
 */
public class Main {


    private static Integer count=0 ;

    public synchronized void Increment(){
            System.out.println(Thread.currentThread().getName()+"开始操作 count："+count);
            count++;
            System.out.println(Thread.currentThread().getName()+"操作结束 count："+count);
    }

    public static <List> void main(String[] args) {
        Main main=new Main();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                main.Increment();
            }).start();
        }
    }
}
