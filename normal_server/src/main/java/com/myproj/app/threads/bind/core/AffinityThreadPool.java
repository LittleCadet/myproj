package com.myproj.app.threads.bind.core;

import net.openhft.affinity.AffinityLock;
import net.openhft.affinity.AffinityStrategies;
import net.openhft.affinity.AffinityStrategy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 绑核 比 不绑核：性能提升1% - 5%。
 */
public class AffinityThreadPool {

    public static ExecutorService newFixedThreadPool(int nThreads) {
        return Executors.newFixedThreadPool(nThreads, new AffinityThreadFactory());
    }
    public static ExecutorService newFixedThreadPoolV2(int nThreads) {
        return Executors.newFixedThreadPool(nThreads);
    }

    private static class AffinityThreadFactory implements ThreadFactory {
        private final AtomicInteger threadCount = new AtomicInteger(0);
        private final String namePrefix;

        public AffinityThreadFactory() {
            this("AffinityThread-");
        }

        public AffinityThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        /**
         * AffinityStrategies.SAME_CORE, // 策略: 同核心
         * AffinityStrategies.DIFFERENT_CORE, // 策略: 不同核心
         * AffinityStrategies.ANY // 策略: 任意核心
         * @param r
         * @return
         */
        @Override
        public Thread newThread(Runnable r) {
            int threadNum = threadCount.getAndIncrement();
            return new Thread(() -> {
                // 使用AffinityLock绑定核心
                try (AffinityLock lock = AffinityLock.acquireLock(1)) {
                    System.out.println("Thread " + Thread.currentThread().getName() +
                                     " locked to CPU " + lock.cpuId());
                    r.run();
                }

//                r.run();
            }, namePrefix + threadNum);
        }
    }

    /**
     * 绑核测试
     */
    public static void test() {
        ExecutorService executor = newFixedThreadPool(4);

//        test1(executor);
        test2(executor);

        executor.shutdown();
    }

    /**
     * 不绑核测试
     */
    public static void testV2() {
        ExecutorService executor = newFixedThreadPoolV2(4);
        test2(executor);
        executor.shutdown();
    }

    public static void test1(ExecutorService executor) {
        // 这个代表：当前线程绑定到CPU 1上，然后执行任务。
        try (AffinityLock lock = AffinityLock.acquireLock(1)) {
            System.out.println("Thread " + Thread.currentThread().getName() +
                    " locked to CPU " + lock.cpuId());
            for (int i = 0; i < 10; i++) {
                final int taskId = i;
                // 任务提交到线程池， 依旧是该线程享受绑核服务。但运行任务，就没有绑核服务了。
                // 任务提交到线程池后， 该线程会释放绑定的cpu.
                executor.execute(() -> {
                    // 此时是全新线程， 所以没有绑核了。
                    System.out.println("Task " + taskId + " running on " +
                            Thread.currentThread().getName());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }

    public static void test2(ExecutorService executor) {
        executor.execute(() -> {
            // 此时是全新线程， 该线程绑核了。
            long start = System.currentTimeMillis();
            AtomicInteger count = new AtomicInteger(0);
            while(true) {
                System.out.println(" running on " + Thread.currentThread().getName() + ":" + count.getAndIncrement());
                if(System.currentTimeMillis() - start > TimeUnit.MINUTES.toMillis(1)) {
                    break;
                }
            }
        });
    }


    public static void main(String[] args) {
        test();
    }
}