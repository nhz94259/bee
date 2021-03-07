package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.ArrayList;

public class missingNumber {

    public int missingNumber(int[] nums) {
        if(nums[0]!=0) return 0;
        for(int i = 0 ; i<nums.length;i++){
            if(nums[i]!=i) return i;
        }
        return nums.length;
    }

    public static void main(String[] args) {
        missingNumber method = new missingNumber();
        System.out.println(method.missingNumber(new int[]{0, 1, 3}));
    }
}
