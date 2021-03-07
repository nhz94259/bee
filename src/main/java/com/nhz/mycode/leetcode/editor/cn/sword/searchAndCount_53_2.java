package com.nhz.mycode.leetcode.editor.cn.sword;

public class searchAndCount_53_2 {
    public int search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }
    int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= tar) i = m + 1;
            else j = m - 1;
        }
        return i;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,3};
        searchAndCount_53_2 method = new searchAndCount_53_2();
        System.out.println(method.search(nums, 2));
    }
}
