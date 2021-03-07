package com.nhz.mycode.completed.retry;


import java.util.concurrent.Callable;

public class GuavaRetryLab {


    private static Integer num = 0;
    public static Boolean result = false;
    public static void main(String[] args) {
        try {
            System.out.println("stat job");
            result = GuavaRetryUtil.retryer.call(getTokenUserCall);
        } catch (Exception e) {
            System.err.println("still failed after retry.");
        }
        System.out.println(result);
    }


    private static Callable<Boolean> getTokenUserCall = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            num++;
            System.out.println("calling..........num=" + num);
            if (num == 3) {
                return true;
            } else if (num == 1) {
                 throw new IllegalStateException();
            }else if (num == 2) {
                throw new RuntimeException("运行时异常");
            }
            return false;
        }
    };
}
