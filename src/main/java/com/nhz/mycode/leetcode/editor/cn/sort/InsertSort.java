package com.nhz.mycode.leetcode.editor.cn.sort;

import com.nhz.mycode.leetcode.editor.cn.tools.ArraysUtils;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] array = {23, 132, 213, 123,2,4,66,123,53};
        System.out.println("before sort:" + Arrays.toString(array));
        sort(array);
        System.out.println("after  sort:" + Arrays.toString(array));
    }
    //主要思想：从下标i开始逐个向前遍历大小，如比在前者小，则交换位置,直至前i有序 break;
    //其中因为当前量i 就随着交换而移动，会变换下标，故取一个current 来动态代表当前调用的值下标
    public static void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int current = i;
            for (int j = i - 1; j >= 0; j--) {
                if(nums[current]<nums[j]){
                    ArraysUtils.swap(nums, current, j);
                    current = j;
                }else{
                    break;
                }
            }
        }
    }
}
