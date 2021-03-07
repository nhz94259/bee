package com.nhz.mycode.completed.executor.diffbewteenSubAndExecute;

import org.apache.tomcat.util.threads.TaskThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.*;

public class SubmitMethodLab {
    /**
     * 线程池中submit 与 execute区别
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println("-------------实验1------------------");
        Future result1 = executorService.submit(new Callable<Object>() {
            public Object call() {
                System.out.println("This is submit() method example");
                return "Returning Callable Task Result";
            }
        });
        System.out.println(result1.get());
        System.out.println("-------------实验2------------------");
        Runnable r1 = () -> System.out.println(1 / 0);
        executorService.submit(r1);
        System.out.println("程序静悄悄的什么都没有输出，异常没有，日志也没有，我们的错误直接被吞掉了。");
        System.out.println("-------------实验3------------------");
        Future result3 = executorService.submit(r1);
        //System.out.println(result3.get());
        System.out.println("打开注释后通过 Future get 等到了异常");
        executorService.shutdown();
        System.out.println("-------------实验4------------------");
        ExecutorService executorService2 = Executors.newSingleThreadExecutor(new MyThreadFactory());
        //execute
        executorService2.execute(r1);
        executorService2.shutdown();
    }


}
