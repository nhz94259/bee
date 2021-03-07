package com.nhz.mycode.leetcode.editor.cn.find;

public class binarySearch {
    public static int findIndex(int[] nums, int target){
        if (nums.length == 0) {
            return -1;
        }
        int l = 0, r = nums.length -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (target != nums[l]) {
            return -1;
        }
        return l;
    }
}
