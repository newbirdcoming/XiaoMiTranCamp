package com.example.demo.one.test3;


/**
 * describe：线程的常见实现方式
 *
 */
public class Main {


    class MyThread extends Thread{
        private String orderId;
        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName()+"正在执行");
            System.out.println("OrderId："+orderId);
            System.out.println(Thread.currentThread().getName()+"执行完毕");
        }

        public MyThread(String name) {
            super(name);
        }

        public MyThread(String name, String orderId) {
            super(name);
            this.orderId = orderId;
        }
    }

    public static void main(String[] args) {
        Main main=new Main();
        main.new MyThread("线程1","1232").start();
        main.new MyThread("线程2","234324").start();
        main.new MyThread("线程3","23231").start();
        main.new MyThread("线程4","234324").start();
        main.new MyThread("线程5","23424").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"运行");
        },"lanjie").start();


        //TODO 使用ThreadLocal存取订单号（自己想的未完成） 自己的线程创建变量实现该功能（已实现）

    }


}
