package com.nhz.mycode.completed.arthas;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadLockLab {
        public static int M =  1024 * 1024;

        private static HashSet<byte[]> hashSet = new HashSet();
        /** 线程池，大小1*/
        private static ExecutorService executorService = Executors.newFixedThreadPool(1);

        public static void main(String[] args) {
        // 模拟 CPU 过高，这里注释掉了，测试时可以打开
        cpu();
        // 模拟线程阻塞
         thread();
        //  模拟线程死锁
        deadThread();
        // 不断的向 hashSet 集合增加数据
           addHashSetThread();
        }

        /**
         * 不断的向 hashSet 集合添加数据
         */
        public static void addHashSetThread() {
            // 初始化常量
            new Thread(() -> {
                int count = 0;
                while (true) {
                    try {
                        byte[] array = new byte[1 * M];
                        hashSet.add(array);
                        System.out.println("添加了"+hashSet.size()+"M");
                        Thread.sleep(3000);
                        count++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"addHashSetThread").start();
        }

        public static void cpu() {
            cpuHigh();
            cpuNormal();
        }

        /**
         * 极度消耗CPU的线程
         */
        private static void cpuHigh() {
            Thread thread = new Thread(() -> {
                while (true) {
                    log.info("cpu start 100");
                }
            },"cpuHigh");
            // 添加到线程
            executorService.submit(thread);
        }

        /**
         * 普通消耗CPU的线程
         */
        private static void cpuNormal() {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    while (true) {
                        log.info("cpu start");
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },"cpuNormal").start();
            }
        }

        /**
         * 模拟线程阻塞,向已经满了的线程池提交线程
         */
        private static void thread() {
            Thread thread = new Thread(() -> {
                while (true) {
                    log.debug("thread start");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"crazy create thread");
            // 添加到线程
            executorService.submit(thread);
        }

        /**
         * 死锁
         */
        private static void deadThread() {
            /** 创建资源 */
            String resourceA = new String();
            String resourceB = new String();
            // 创建线程
            Thread threadA = new Thread(() -> {
                synchronized (resourceA) {
                    log.info(Thread.currentThread() + " get ResourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info(Thread.currentThread() + "waiting get resourceB");
                    synchronized (resourceB) {
                        log.info(Thread.currentThread() + " get resourceB");
                    }
                }
            });

            Thread threadB = new Thread(() -> {
                synchronized (resourceB) {
                    log.info(Thread.currentThread() + " get ResourceB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info(Thread.currentThread() + "waiting get resourceA");
                    synchronized (resourceA) {
                        log.info(Thread.currentThread() + " get resourceA");
                    }
                }
            });
            threadA.start();
            threadB.start();
        }

}
