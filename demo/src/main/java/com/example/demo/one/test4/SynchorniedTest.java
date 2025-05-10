package com.example.demo.one.test4;


/**
 * @author Mr.Lan
 * @version 1.0
 * @ClassName ThreadSynchronized$
 * @description TODO
 * @date 2024/12/31 19:38
 **/
public class SynchorniedTest {
    static class Account {
        private  int balance;
        private String name;

        public Account(String name,int balance){
            this.balance=balance;
            this.name=name;
        }

    }

    static class Draw extends Thread{
        private Account account;

        private int fetchAmount;

        private int nowAmount;



        public Draw(Account account,int fetchAmount,String name,int nowAmount){
            super(name);
            this.account=account;
            this.fetchAmount=fetchAmount;
            this.nowAmount=nowAmount;
        }


        @Override
        public void run(){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            if(account.balance-fetchAmount>=0){
//                int i = account.balance - fetchAmount;
//                nowAmount+=fetchAmount;
//                System.out.println(Thread.currentThread().getName()+"取钱"+fetchAmount+"成功---------->"+"账户余额为------------->"+i);
//                System.out.println("手里的余额为"+nowAmount);
//                this.account.balance=i;
//            }
            if(account.balance-fetchAmount>=0){
                account.balance=account.balance - fetchAmount;
                nowAmount+=fetchAmount;
                System.out.println(Thread.currentThread().getName()+"取钱"+fetchAmount+"成功---------->"+"账户余额为------------->"+account.balance);
                System.out.println("手里的余额为"+nowAmount);
            }
            else{
                System.out.println(Thread.currentThread().getName()+"取钱失败");
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Account amount = new Account("公积金", 20000);
        Draw drwa1 = new Draw(amount, 8000, "小明",0);
        Draw drwa2 = new Draw(amount, 18000, "小妹",0);
        drwa1.start();
        drwa2.start();
        Thread.sleep(2000);
        System.out.println("账户名："+amount.name+"的余额为"+amount.balance);
    }



}
