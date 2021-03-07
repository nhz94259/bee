package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.Deque;
import java.util.LinkedList;

public class maxSlidingWindow_redo {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length <k) return new int[0];
        int[] result = new int[nums.length - k + 1];
        //queue存储窗口最大值下标
        Deque<Integer> queue = new LinkedList<>();
        for(int i = 0;i<nums.length;i++){
            if (!queue.isEmpty()&&(i - k) <= queue.peekFirst()) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.removeLast();
            }
            queue.addLast(i);
            if (i - k + 1 > 0) {
                result[i-k + 1] = queue.peekFirst();
            }
        }
        return result;
    }

}
