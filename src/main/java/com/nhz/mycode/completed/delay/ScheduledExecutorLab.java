package com.nhz.mycode.completed.delay;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorLab {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("执行");
            }
        };
        //延迟2秒后执行该任务
        scheduledThreadPool.schedule(runnable, 2, TimeUnit.SECONDS);
        scheduledThreadPool.schedule(runnable, 3, TimeUnit.SECONDS);
        //最后关闭
        scheduledThreadPool.shutdown();
    }
}
