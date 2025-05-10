package com.example.demo.one.classTask4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Main {
    private static final double MEMORY_THRESHOLD_RATIO = 0.8; // 内存使用阈值（80%）
    private static final long CHECK_INTERVAL_MS = 1000; // 内存检查间隔（毫秒）
    private static final long LOG_INTERVAL_MS = 5000; // 内存日志输出间隔（毫秒）
    private static final List<String> sharedList = new CopyOnWriteArrayList<>(); // 线程安全列表
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final AtomicBoolean thresholdExceeded = new AtomicBoolean(false);
    private static final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    public static void main(String[] args) {
        String directoryPath = "file"; // 替换为实际目录路径
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        // 创建固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 内存监控逻辑 - 使用函数式接口
        Supplier<Double> memoryUsageSupplier = Main::calculateMemoryUsageRatio;
        Consumer<Double> memoryLogger = Main::logMemoryUsage;
        Consumer<Double> thresholdChecker = ratio -> {
            if (ratio >= MEMORY_THRESHOLD_RATIO) {
                triggerMemoryAlert(ratio);
            } else if (thresholdExceeded.getAndSet(false)) {
                System.out.println("内存使用已恢复正常水平");
            }
        };

        // 启动内存监控任务
        startMemoryMonitor(memoryUsageSupplier, memoryLogger, thresholdChecker);

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.canRead()) {
                    executorService.submit(() -> readFile(file));
                }
            }
        }

        // 优雅关闭线程池
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // 关闭调度器
        scheduler.shutdown();
    }

    private static void startMemoryMonitor(
            Supplier<Double> memoryReader,
            Consumer<Double> logger,
            Consumer<Double> thresholdHandler
    ) {
        // 定期检查内存使用情况
        scheduler.scheduleAtFixedRate(() -> {
            try {
                double memoryRatio = memoryReader.get();
                thresholdHandler.accept(memoryRatio);
            } catch (Exception e) {
                System.err.println("内存监控任务异常: " + e.getMessage());
            }
        }, 0, CHECK_INTERVAL_MS, TimeUnit.MILLISECONDS);

        // 定期输出内存使用日志
        scheduler.scheduleAtFixedRate(() -> {
            try {
                logger.accept(memoryReader.get());
            } catch (Exception e) {
                System.err.println("内存日志任务异常: " + e.getMessage());
            }
        }, 0, LOG_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

    private static double calculateMemoryUsageRatio() {
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        long usedMemory = heapUsage.getUsed();
        long maxMemory = heapUsage.getMax() == -1 ? heapUsage.getCommitted() : heapUsage.getMax();
        return (double) usedMemory / maxMemory;
    }

    private static void logMemoryUsage(double memoryRatio) {
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.printf("内存使用: %.2f%% (%.2f MB / %.2f MB)%n",
                memoryRatio * 100,
                heapUsage.getUsed() / (1024.0 * 1024),
                (heapUsage.getMax() == -1 ? heapUsage.getCommitted() : heapUsage.getMax()) / (1024.0 * 1024));
    }

    private static void triggerMemoryAlert(double memoryRatio) {
        if (thresholdExceeded.compareAndSet(false, true)) {
            System.err.printf("⚠️ 警告: 内存使用超过阈值 %.2f%%! 当前使用: %.2f%%%n",
                    MEMORY_THRESHOLD_RATIO * 100, memoryRatio * 100);

            // 可以在这里添加更复杂的处理逻辑
            System.err.println("建议操作:");
            System.err.println("1. 检查是否存在内存泄漏");
            System.err.println("2. 考虑增加JVM堆内存大小");
            System.err.println("3. 优化数据处理逻辑，减少内存占用");

            // 打印堆内存使用详情
            printDetailedMemoryUsage();
        }
    }

    private static void printDetailedMemoryUsage() {
        System.out.println("\n=== 堆内存详细信息 ===");
        MemoryUsage heapUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.printf("初始大小: %.2f MB%n", heapUsage.getInit() / (1024.0 * 1024));
        System.out.printf("当前使用: %.2f MB%n", heapUsage.getUsed() / (1024.0 * 1024));
        System.out.printf("已提交: %.2f MB%n", heapUsage.getCommitted() / (1024.0 * 1024));
        System.out.printf("最大容量: %.2f MB%n",
                heapUsage.getMax() == -1 ? heapUsage.getCommitted() / (1024.0 * 1024) : heapUsage.getMax() / (1024.0 * 1024));

        // 打印GC信息
        ManagementFactory.getGarbageCollectorMXBeans().forEach(gcBean -> {
            System.out.printf("GC 名称: %s, 执行次数: %d, 总耗时: %d ms%n",
                    gcBean.getName(),
                    gcBean.getCollectionCount(),
                    gcBean.getCollectionTime());
        });
    }

    private static void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sharedList.add(line);
                // 可选：每处理一定数量的行后进行一次内存检查
                if (sharedList.size() % 1000 == 0) {
                    Thread.yield(); // 让出CPU时间，提高内存检查的及时性
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 简单的线程工厂构建器，用于设置线程名称
    static class ThreadFactoryBuilder {
        private String nameFormat;

        public ThreadFactoryBuilder setNameFormat(String nameFormat) {
            this.nameFormat = nameFormat;
            return this;
        }

        public ThreadFactory build() {
            return r -> {
                Thread t = new Thread(r);
                if (nameFormat != null) {
                    t.setName(String.format(nameFormat, t.getId()));
                }
                return t;
            };
        }
    }
}