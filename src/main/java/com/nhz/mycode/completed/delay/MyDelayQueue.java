package com.nhz.mycode.completed.delay;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyDelayQueue {

    /**
     * 自己实现一个不安全的延时粗度的延时队列
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        PriorityQueue<OrderDelay> time_out_queue  = new PriorityQueue<>(new Comparator<OrderDelay>(){
            @Override
            public int compare(OrderDelay o1,OrderDelay o2){
                return (int) (o1.delay - o2.delay);
            }
        });
        for (int i = 1; i <= 5; i++) {
            //给每个工单随机的一个下发时间
            String orderId = "order-id-0" + i;
            int duration = random.nextInt(10);
            System.out.println(String.format("id：%s,设置延时：%s s 执行",orderId,duration));
            time_out_queue.add((new OrderDelay(orderId, TimeUnit.SECONDS.convert(duration,TimeUnit.SECONDS))));
        }
        while (!time_out_queue.isEmpty()) {
            OrderDelay peek = time_out_queue.peek();
            long sleep_time = peek.timeout - System.nanoTime();
            System.out.println(String.format("没有任务我先再睡%s秒",sleep_time/1000000000));
            TimeUnit.NANOSECONDS.sleep(peek.timeout-System.nanoTime());
            time_out_queue.poll().print();
        }
    }
}
