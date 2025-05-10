package com.example.demo.one.test5;

import lombok.Synchronized;

public class Main {


    private static Object lock1=new Object();
    private static Object lock2=new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized(lock1){
                System.out.println(Thread.currentThread().getName()+"获取锁："+lock1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"释放锁："+lock1);
                synchronized(lock2){
                    System.out.println(Thread.currentThread().getName()+"获取锁："+lock2);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName()+"释放锁："+lock2);
                }
            }

        }).start();



        new Thread(()->{

            synchronized(lock2){
                System.out.println(Thread.currentThread().getName()+"获取锁："+lock2);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName()+"释放锁："+lock2);
                synchronized(lock1){
                    System.out.println(Thread.currentThread().getName()+"获取锁："+lock1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName()+"释放锁："+lock1);
                }
            }
        }).start();


    }
}
