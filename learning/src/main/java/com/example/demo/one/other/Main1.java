package com.example.demo.one.other;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        // 获取内存管理Bean
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

        // 查看堆内存使用情况
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("堆内存使用情况:");
        printMemoryUsage(heapUsage);

        // 查看非堆内存使用情况
        MemoryUsage nonHeapUsage = memoryMXBean.getNonHeapMemoryUsage();
        System.out.println("\n非堆内存使用情况:");
        printMemoryUsage(nonHeapUsage);

        // 获取所有内存池的信息
        List<MemoryPoolMXBean> memoryPools = ManagementFactory.getMemoryPoolMXBeans();
        System.out.println("\n各内存池的详细信息:");
        for (MemoryPoolMXBean pool : memoryPools) {
            System.out.println("\n内存池名称: " + pool.getName());
            System.out.println("类型: " + pool.getType());
            printMemoryUsage(pool.getUsage());
        }
    }

    private static void printMemoryUsage(MemoryUsage usage) {
        System.out.println("  初始大小: " + formatSize(usage.getInit()));
        System.out.println("  当前使用量: " + formatSize(usage.getUsed()));
        System.out.println("  已提交大小: " + formatSize(usage.getCommitted()));
        System.out.println("  最大容量: " + formatSize(usage.getMax()));
    }

    private static String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f MB", bytes / (1024.0 * 1024));
        return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
    }
}
