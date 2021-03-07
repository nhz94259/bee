package com.nhz.mycode.leetcode.editor.cn.sort;

import com.nhz.mycode.leetcode.editor.cn.tools.ArraysUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {23, 132, 213, 123,2,4,66,123,53};
        QuickSort sort = new QuickSort();
        System.out.println("before sort:" + Arrays.toString(array));
        sort.quickSort(array, 0, array.length - 1);
        System.out.println("after  sort:" + Arrays.toString(array));
    }
    public  void quickSort(int[] nums, int l, int r){
        if (l < r) {
            int pivot = partition(nums, l, r);
            quickSort(nums, l, pivot - 1);
            quickSort(nums, pivot + 1, r);
        }
    }

    public  int partition(int[] nums, int l, int r) {
        int pivot = nums[r];
        int index = l;
        for (int i = index; i < r; i++) {
            if (pivot > nums[i]) {
                ArraysUtils.swap(nums, i, index);
                index++;
            }
        }
        ArraysUtils.swap(nums, r, index);
        return index;
    }

}
