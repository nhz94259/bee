package com.nhz.mycode.leetcode.editor.cn.sword;

public class maxSubArray {

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int sum = nums[0];
        for(int i = 1 ; i< nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
            sum = Math.max(sum,dp[i]);
        }
        return sum;
    }
}
