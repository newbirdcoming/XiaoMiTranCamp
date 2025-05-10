package com.example.demo.one.classTask2;

public class Main1 {
    
    public static void main(String[] args) {
        Bank bank=new Bank(0);
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                bank.deposit(10);
            }

        }, "线程1");

        Thread thread2 =new Thread(()->{
            for (int i = 0; i < 3; i++) {
                bank.withdrawal(10);
            }
        },"线程2");

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Bank{
    private int balance;

    public Bank(int balance){
        this.balance=balance;
    }

    void deposit(int number){
        synchronized (this) {
            System.out.println("存款前：" + balance);
            System.out.println("存款金额：" + balance);
            balance += number;
            System.out.println(Thread.currentThread().getName() + balance);
        }
    }

    void withdrawal(int number){
        synchronized (this) {
            System.out.println("取款前：" + balance);
            System.out.println("取款金额：" + balance);
            if(balance-number<0)
                System.out.println("取款失败，余额：" + balance);
            else{
                balance -= number;
                System.out.println(Thread.currentThread().getName()+"取款后：" + balance);
            }
        }
    }


}
