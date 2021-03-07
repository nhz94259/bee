package com.nhz.mycode.completed.arthas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class HighCPULab {

        public static int M =  1024 * 1024;

        /** 线程池，大小1*/
        private static ExecutorService executorService = Executors.newFixedThreadPool(1);

        public static void main(String[] args) {
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
}
