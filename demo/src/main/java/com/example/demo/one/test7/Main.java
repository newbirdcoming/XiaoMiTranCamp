package com.example.demo.one.test7;


/**
 * @author Mr.Lan
 * @version 1.0
 * @ClassName test3$
 * @description 创建线程 实现runable接口
 * @date 2024/12/29 20:48
 **/


//一个对象被多个线程使用 使得多个线程 操作一个拥有票数对象

//        如何解决并发下多个线程同时操作一个对象的不安全性 第x张票被多个人拿到
//        原因分析：在多线程并发访问同一个资源时，出现某张票被多个人拿到的情况，这是由于多线程竞争共享资源时，可能会导致数据的不一致性。这种现象在未加同步或其他同步机制的情况下容易发生。
public class Main implements Runnable{

    long minPrime;

    int number;
    Main(long minPrime) {
        this.minPrime = minPrime;
    }

    @Override
    public void run() {
        number=10;
        while(true){
            if(number<0)
                break;
            System.out.println(Thread.currentThread().getName()+"--->拿到了第"+number--+"张票");
        }
    }

    public static void main(String[] args) {
        Main p = new Main(143);
        new Thread(p,"小明").start();
        new Thread(p,"小张").start();
        new Thread(p,"小刘").start();

    }
}

