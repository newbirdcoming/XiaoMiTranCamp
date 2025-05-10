package com.example.demo.one.classwork_last.classwork;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 创建订单（循环创建自增）【先创建订单】
 * 使用多线程Cpu核数
 */
public class Main {

//    定义订单数
    static final long sliceNumber=1000l;

//    定义全局总金额
    static private OrderResult res=new OrderResult(0,0);

    public static void main(String[] args) throws InterruptedException {

        //    2.创建多线程：线程数量为cpu核数，充分利用cpu资源，加速订单金额计算
        // 获取cpu核数
        int cpuSize=getCpuSize();
        CountDownLatch countDownLatch = new CountDownLatch(cpuSize);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                cpuSize,
                cpuSize,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //    1.创建订单
//        List<Order> orders=create_order();
//        System.out.println("分片大小:"+size);
        List<List<Order>> orders=new ArrayList<>();
        for (int i = 0; i < cpuSize; i++) {
            orders.add(create_order(sliceNumber));
        }
        for (int i = 0; i < cpuSize; i++) {
            int k=i;
            executor.submit(()->{
//            5.计算订单总金额
                try {
                    calculatorTotalSum(orders.get(k));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
//        关闭线程池
        executor.shutdown();
        countDownLatch.await(1000,TimeUnit.MINUTES);

////        输出总金额(必须保证最后输出):使用一个判断
//        while(res.getRecord()<cpuSize){
//            System.out.print("");
//        }
        System.out.println("所有订单的总金额为:"+res.getSum());

    }

    private static int getCpuSize() {
        return Runtime.getRuntime().availableProcessors();
    }


    static  List<Order> create_order(long number) {
        List<Order> orders = new ArrayList<>();
        for (long i = 1; i <=number; i++) {
//            订单id从1开始递增 价格随机(1-1000)
            Random random = new Random();
            int temp=random.nextInt(1000)+1;
            temp=1;
            orders.add(new Order(UUID.randomUUID().toString(),"订单"+i,temp,new char[1024*512]));
            System.out.println("生成订单:订单id:"+i+"订单名称: 订单"+i+"订单金额:"+temp);
        }
        return orders;
    }



//    6.每次累加设置内存报警 将最后结果累加到全局变量中sum
    static void calculatorTotalSum(List<Order> orders){
        checkMemory();
        long total=0;
//        使用streamApi和Lamda表达式计算
//        int[] ints = orders.stream().map(Order::getPrice).mapToInt(Integer::intValue).toArray();
//        total = Arrays.stream(ints).sum();
        total = orders.stream().mapToInt(Order::getPrice).sum();
        synchronized (OrderResult.class){
            res.setSum(res.getSum()+total);
            res.setRecord(res.getRecord()+1);
        }
    }

    private static void checkMemory() {
        // 获取内存管理Bean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 查看堆内存使用情况
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        float useRate=(float)heapUsage.getUsed()/heapUsage.getMax();
//        Random random = new Random();
//        useRate=random.nextInt(100);
        System.out.println("当前使用率"+useRate);
        if(useRate>80){
            System.out.println("预警:当前JVM内存使用率超过80%,当前使用率"+useRate);
        }
    }


}


class Order{
//    订单id：使用uuid保证不重复
    private  String id;
//    商品名称：
    private String name;
//    价格：int类型
    private int price;

    private char[] slice;

    public Order(String id,String name,int price,char[] slice){
        this.id=id;
        this.name=name;
        this.price=price;
        this.slice=slice;
    }

    public int getPrice(){
        return this.price;
    }
}


//定义一个订单计算结果类
class OrderResult{
    private long sum;


    private int record;

    public OrderResult(long sum,int record){
        this.sum=sum;
        this.record=record;
    }

    public void setSum(long sum){
        this.sum=sum;
    }

    public void setRecord(int sum){
        this.record=sum;
    }


    public long getSum(){
        return this.sum;
    }


    public int getRecord(){
        return this.record;
    }
}




