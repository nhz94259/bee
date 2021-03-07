package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.PriorityQueue;

public class getLeastNumbers {


    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0 ; i< arr.length ; i++){
            queue.add(arr[i]);
        }
        int[] ans = new int[k];
        int i = 0;
        while(i<k){
            ans[i++] = queue.poll();
        }
        return ans;
    }
}
