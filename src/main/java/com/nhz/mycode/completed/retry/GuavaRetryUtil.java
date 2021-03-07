package com.nhz.mycode.completed.retry;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaRetryUtil {

    public static Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfException() // 异常时重试
            .withBlockStrategy(BlockStrategies.threadSleepStrategy())
            .retryIfExceptionOfType(IllegalStateException.class) // 特定异常时才重试
            .retryIfResult(Predicates.equalTo(false)) // 返回结果 false 也重试
            .withStopStrategy(StopStrategies.stopAfterAttempt(3)) // 重试次数
             .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
            //.withWaitStrategy(WaitStrategies.incrementingWait(1, TimeUnit.SECONDS, 2, TimeUnit.SECONDS))  // 重试策略
            .withRetryListener(new MyRetryListener<>())
            .build();

    public static class MyRetryListener<Boolean> implements RetryListener {
        @Override
        public <Boolean> void onRetry(Attempt<Boolean> attempt) {
            System.out.print("[retry]time=" + attempt.getAttemptNumber());
            // 距离第一次重试的延迟
            System.out.print(",delay=" + attempt.getDelaySinceFirstAttempt());
            // 重试结果: 是异常终止, 还是正常返回
            System.out.print(",hasException=" + attempt.hasException());
            System.out.print(",hasResult=" + attempt.hasResult());
            // 是什么原因导致异常
            if (attempt.hasException()) {
                System.out.print(",causeBy=" + attempt.getExceptionCause().toString());
            } else {
                // 正常返回时的结果
                System.out.print(",result=" + attempt.getResult());
            }
            // bad practice: 增加了额外的异常处理代码
            try {
                Boolean result = attempt.get();
                System.out.print(",rude get=" + result);
            } catch (ExecutionException e) {
                System.err.println("this attempt produce exception." + e.getCause().toString());
            }
        }
    }
}
