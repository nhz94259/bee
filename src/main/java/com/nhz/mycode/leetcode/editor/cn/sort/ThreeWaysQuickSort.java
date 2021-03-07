package com.nhz.mycode.leetcode.editor.cn.sort;

import com.nhz.mycode.leetcode.editor.cn.tools.ArraysUtils;

import java.util.Arrays;

public class ThreeWaysQuickSort {

    public static void main(String[] args) {
        int[] array = {23, 132, 213, 123,2,4,66,123,53};
        int[] result = {2,4,23,53, 66,123, 123,132, 213};
        System.out.println("before sort:" + Arrays.toString(array));
        ThreeWaysQuickSort sort = new ThreeWaysQuickSort();
        sort.way3quickSort(array, 0, array.length - 1);
        System.out.println("ans      is:" + Arrays.toString(result));
        System.out.println("after  sort:" + Arrays.toString(array));
    }

    public void way3quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int[] pivot = partition(nums, left, right);
            way3quickSort(nums, left, pivot[0] - 1);
            way3quickSort(nums, pivot[1] + 1, right);
        }
    }
    public int[] partition(int[] nums, int left, int right){
        int pivot = nums[right];
        int l = left, r = right;
        /*    i <= r    */
        for (int i = l; i <= r; i++) {
            if (pivot > nums[i]) {
                ArraysUtils.swap(nums,i,l);
                l++;
            }else if(pivot < nums[i]){
                ArraysUtils.swap(nums,i,r);
                r--;
               // i--;
            }
        }
        int result[] = {l, r};
        while (l++ < r) {
            nums[l] = pivot;
        }
        return result;
    }


}
