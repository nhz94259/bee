package com.nhz.mycode.leetcode.editor.cn.heap;


import java.util.PriorityQueue;

public class PrintK {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 5, 12, 13, 41, 124};
        printKth(3, array);
    }
    //小顶堆 容量为k 堆顶即为第K大元素
    public static PriorityQueue<Integer> queue = new PriorityQueue();
    public static int count = 0;
    public static int capacity = 0;

    private static void printKth(int k, int[] array) {
        capacity = k;
        for (int i = 0; i < array.length; i++) {
            if (count + 1 > capacity) {
                if(queue.peek()<array[i]){
                    queue.poll();
                    queue.add(array[i]);
                }
            } else {
                count++;
                queue.add(array[i]);
            }
        }
        System.out.println(queue.peek());
    }



}
