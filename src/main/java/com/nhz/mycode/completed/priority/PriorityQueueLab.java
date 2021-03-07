package com.nhz.mycode.completed.priority;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityQueueLab {
    //默认是小顶堆
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(3);
    //大顶堆
    private static PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2-o1; //大顶堆
           //return o1-o2;//小顶堆 及默认
        }
    });
    private static PriorityQueue<Integer> bigHeap2 = new PriorityQueue<>(Comparator.reverseOrder());
    private static PriorityBlockingQueue<Integer> safeHeap = new PriorityBlockingQueue<>(3,Comparator.reverseOrder());
    public static void main(String[] args) {
        System.out.println("小顶堆");
        add1(4);
        add1(2);
        add1(6);
        add1(0);
        System.out.println("-----------------------------------");
        System.out.println("大顶堆");
        add2(4);
        add2(2);
        add2(6);
        add2(0);
        System.out.println("-----------------------------------");
        add3(4);
        add3(2);
        add3(6);
        add3(0);
        System.out.println("-----------------------------------");
        System.out.println("安全-大顶堆");
        add4(4);
        add4(2);
        add4(6);
        add4(0);
    }
    private static void add1(int x){
        queue.add(x);
        System.out.println("加入："+x+"，当前最小值:"+queue.peek()+"，内容："+queue.toString()+"，长度："+queue.size());
    }
    private static void add2(int x){
        bigHeap.add(x);
        System.out.println("加入："+x+"，当前最大值:"+bigHeap.peek()+"，内容："+bigHeap.toString()+"，长度："+bigHeap.size());
    }
    private static void add3(int x){
        bigHeap2.add(x);
        System.out.println("加入："+x+"，当前最大值:"+bigHeap2.peek()+"，内容："+bigHeap2.toString()+"，长度："+bigHeap2.size());
    }
    private static void add4(int x){
        safeHeap.add(x);
        System.out.println("加入："+x+"，当前最大值:"+safeHeap.peek()+"，内容："+safeHeap.toString()+"，长度："+safeHeap.size());
    }

}
