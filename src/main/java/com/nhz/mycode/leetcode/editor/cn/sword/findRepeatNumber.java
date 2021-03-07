package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.HashSet;

public class findRepeatNumber {

    public int findRepeatNumber(int[] nums) {
        int[] arr  = new int[nums.length];
        for(int i = 0 ; i < nums.length;i++){
            if(arr[nums[i]]>0) {
                return nums[i];
            }
            arr[nums[i]]++;
        }
        return 0;
    } // 3 : 011
      // 1 : 001 010

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,2,3};
        findRepeatNumber method = new findRepeatNumber();
        System.out.println(method.findRepeatNumber(nums));
    }
}
