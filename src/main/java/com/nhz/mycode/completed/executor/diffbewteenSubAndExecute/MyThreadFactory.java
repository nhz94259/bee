package com.nhz.mycode.completed.executor.diffbewteenSubAndExecute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    //只有execute方法可以获取到异常，submit 异常将回被当作返回的结果一部分 通过get请求一同获取到
    public  class  ExceptionHandler  implements Thread.UncaughtExceptionHandler {
        public  void  uncaughtException(Thread t, Throwable e) {
            logger.error("current name :{},err：{}",t.getName()+t.getId(),e.toString());
        }
    }

    private static Logger logger = LoggerFactory.getLogger(MyThreadFactory.class);

    public  Thread newThread(Runnable r) {
        Thread t =  new  Thread(r);
        t.setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println( "自定义 Thread["  + t.getName() +  "] created." );
        return  t;
    }

}
