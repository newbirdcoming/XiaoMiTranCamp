package com.example.demo.one.classwork;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 创建订单（循环创建自增）【先创建订单】
 * 使用多线程Cpu核数
 */
public class Main {

//    定义订单数
    static final int orderNumber=10000;

//    定义全局总金额
    static private OrderResult res=new OrderResult(0,0);

    public static void main(String[] args) {
        //    1.创建订单
        List<Order> orders=create_order();

        //    2.创建多线程：线程数量为cpu核数，充分利用cpu资源，加速订单金额计算
        // 获取cpu核数 TODO getCpuSize()暂时返回20
        int cpuSize=getCpuSize();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                cpuSize,
                cpuSize,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );
        //    3.对订单进行分片吗
        int total = orders.size();
//        System.out.println("订单长度:"+total);
        int size=0;

        if(total%cpuSize==0)
            size=total/cpuSize;
        else
            size=(total/cpuSize)+1;
//        System.out.println("分片大小:"+size);
        for (int i = 0; i < cpuSize; i++) {
            int begin=i*size;
            int temp=(i+1)*size-1;
            int end=temp<=total-1?temp:total-1;
            int k=i;
//            4.分批读取
            executor.submit(()->{
//            5.计算订单总金额
                List<Order> curOrders=orders.subList(begin,end);
                calculatorTotalSum(curOrders);
            });
        }
//        关闭线程池
        executor.shutdown();


//        输出总金额(必须保证最后输出):使用一个判断
        while(res.getRecord()<cpuSize){
            System.out.print("");
        }
        System.out.println("所有订单的总金额为:"+res.getSum());

    }

    private static int getCpuSize() {
        return 20;
    }


    static  List<Order> create_order() {
        List<Order> orders = new ArrayList<>();
        for (long i = 1; i <=orderNumber; i++) {
//            订单id从1开始递增 价格随机(1-1000)
            Random random = new Random();
            int temp=random.nextInt(1000)+1;
            orders.add(new Order(i,"订单"+i,temp));
            System.out.println("生成订单:订单id:"+i+"订单名称: 订单"+i+"订单金额:"+temp);
        }
        return orders;
    }



//    6.每次累加设置内存报警 将最后结果累加到全局变量中sum
    static void calculatorTotalSum(List<Order> orders){
        checkMemory();
        long total=0;
//        使用streamApi和Lamda表达式计算
        int[] ints = orders.stream().map(Order::getPrice).mapToInt(Integer::intValue).toArray();
        total = Arrays.stream(ints).sum();
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
        float useRate=heapUsage.getUsed()/ heapUsage.getMax();
        Random random = new Random();
        useRate=random.nextInt(100);
        if(useRate>80){
            System.out.println("预警:当前JVM内存使用率超过80%,当前使用率"+useRate+"%");
        }
    }


}


class Order{
//    订单id：使用uuid保证不重复
    private  long id;
//    商品名称：
    private String name;
//    价格：int类型
    private int price;

    public Order(long id,String name,int price){
        this.id=id;
        this.name=name;
        this.price=price;
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




