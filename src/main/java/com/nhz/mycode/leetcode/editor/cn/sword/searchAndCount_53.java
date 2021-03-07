package com.nhz.mycode.leetcode.editor.cn.sword;

public class searchAndCount_53 {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length ==0) {
            return 0;
        }
        int l = 0, r = nums.length - 1, m = 0;
        while( l <= r ){
            m = l + (r - l)/2;
            if(target > nums[m]){
                l = m + 1;
            }else {
                r = m - 1;
            }
        }
        if(nums[l]!=target) return 0;
        return sumTarget(nums,l);
    }

    private int sumTarget(int[] nums,int k){
        int sum = 1;
        for (int i = k - 1; i >= 0; i--) {
            if (nums[i] == nums[k]) sum++;
            if (nums[i] != nums[k]) break;
        }
        for (int i = k + 1; i < nums.length; i++) {
            if (nums[i] == nums[k]) sum++;
            if (nums[i] != nums[k]) break;
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        searchAndCount_53 method = new searchAndCount_53();
        System.out.println(method.search(nums, 2));
    }
}
