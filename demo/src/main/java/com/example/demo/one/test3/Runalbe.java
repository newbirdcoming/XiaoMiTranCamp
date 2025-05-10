package com.example.demo.one.test3;

public class Runalbe implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行中");
    }


    public static void main(String[] args) {
        new Thread(new Runalbe(),"拦截").start();
    }
}
