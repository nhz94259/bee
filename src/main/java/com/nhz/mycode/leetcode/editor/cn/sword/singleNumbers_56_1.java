package com.nhz.mycode.leetcode.editor.cn.sword;

public class singleNumbers_56_1 {

    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for(int i = 0 ; i<nums.length;i++){
            ret^=nums[i];
        }
        //ret是结果数a^b的值，而其中ret中为位为1代表：a，b该位不同故可以区分出两个数组
        //记nums除去a，b后的数组为S
        //分为数组[S,a],[S,b]
        //再做异或即可得出答案
        int div = ret&(-ret);
        int a = 0, b = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & div) != 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[]{a,b};
    }
}
