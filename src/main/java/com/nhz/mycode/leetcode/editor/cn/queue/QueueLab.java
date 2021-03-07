package com.nhz.mycode.leetcode.editor.cn.queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class QueueLab {
    private PriorityQueue<Integer> queue = new PriorityQueue<>();
    public static void main(String[] args) {
        PriorityQueue<Integer> queue_raise = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer num1,Integer num2){
                return num1 - num2;
            }
        });
        queue_raise.add(1);
        queue_raise.add(4);
        queue_raise.add(2);
        queue_raise.add(3);
        while (!queue_raise.isEmpty()) {
            System.out.println(queue_raise.poll());
        }
        System.out.println("##");
        PriorityQueue<Integer> queue_desc = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer num1,Integer num2){
                return  num2 - num1;
            }
        });
        queue_desc.add(1);
        queue_desc.add(4);
        queue_desc.add(2);
        queue_desc.add(3);
        while (!queue_desc.isEmpty()) {
            System.out.println(queue_desc.poll());
        }



    }
}
