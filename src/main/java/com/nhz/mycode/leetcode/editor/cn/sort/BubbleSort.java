package com.nhz.mycode.leetcode.editor.cn.sort;

import java.util.Hashtable;

/**
 * https://www.runoob.com/w3cnote/sort-algorithm-summary.html
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 10, 11, 12, 13, 14, 15};
        int[] ans = sort(nums);
        for (int i : ans) {
            System.out.println(i);
        }
    }

    private static int[] sort(int[] nums) {
        int temp = 0;
        boolean change;
        for (int i = 0; i < nums.length - 1; i++) {
            System.out.println(i);
            //做n-1次冒泡
            change = false;
            for (int j = nums.length - 1; j > i; j--) {
                //从最后一个开始，一致到i,选择最大放置最后
                if (nums[j] < nums[j - 1]) {
                    temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                    change = true;
                }
            }
            if (!change) {
                System.out.println("直接tsop");
                break;
            }
        }
        return nums;
    }
}
