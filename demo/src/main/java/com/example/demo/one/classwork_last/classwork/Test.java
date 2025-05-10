package com.example.demo.one.classwork_last.classwork;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        List<char[]> list = new ArrayList<>();
        while(true){
            addChunk(list);
        }
    }

    private static void addChunk(List<char[]> list) {
        list.add(new char[1024*512]);
        // 获取内存管理Bean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

// 查看堆内存使用情况
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        long max = heapUsage.getMax();
        long used = heapUsage.getUsed();
        double useRate=(double) used/max*100;
//        System.out.printf("堆内存使用率:%.2f%% \n",useRate);
        if(useRate>80){
            System.out.printf("预警！！！堆内存使用率:%.2f%% \n",useRate);
            list.remove(0);
        }

    }
}
