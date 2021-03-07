package com.nhz.mycode.completed.executor.diffbewteenSubAndExecute;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteMethodLab {
    /**
     * 线程池中submit 与 execute区别
     */
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //execute 是Executor 接口的唯一定义，是一个无返回的线程执行体
        Runnable r = () -> System.out.println("Lambda Expression Test with Runnable");
        executorService.execute(r);
        Runnable r1 = () -> System.out.println(1 / 0);
        //execute会捕捉到异常
        executorService.execute(r1);
        executorService.shutdown();
    }
}
