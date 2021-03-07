package com.nhz.mycode.completed.delay;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueLab {

    public static void main(String[] args) throws InterruptedException {
        /**
         * 底层实现是 PriorityQueue 以时间为小顶堆 优先级队列队列
         */
        DelayQueue<OrderDelay> queue = new DelayQueue <>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            //给每个工单随机的一个下发时间
            String orderId = "order-id-0" + i;
            int duration = random.nextInt(10);
            System.out.println(String.format("id：%s,设置延时：%s s 执行",orderId,duration));
            queue.put(new OrderDelay(orderId, TimeUnit.SECONDS.convert(duration,TimeUnit.SECONDS)));
        }
        while (!queue.isEmpty()) {
            queue.take().print();
        }
    }
}
