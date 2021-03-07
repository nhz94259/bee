package com.nhz.mycode.leetcode.editor.cn.sort;


import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = {23, 132, 213, 123, 2, 4, 66, 123, 53};
        System.out.println("before sort:" + Arrays.toString(array));
        MergeSort sort = new MergeSort();
        sort.sort(array,0,array.length-1);
        System.out.println("after  sort:" + Arrays.toString(array));
    }

    public void sort(int[] nums,int left , int right){
        if (left < right) {
            // Find the middle point
            int mid = left + (right-left)/2;
            // Sort first and second halves
            sort(nums, left, mid );
            sort(nums, mid + 1, right);
            // Merge the sorted halves
            merge(nums, left, right, mid);
        }
    }
    //0 1 2  '3 4 5 6 7'
    private void merge(int[] nums, int left, int right, int mid) {

        int n1 = mid - left + 1;
        int n2 = right - mid ;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = nums[left + i];
        }
        for (int j = 0; j < n2; j++) {
            //mid + 1 + j ***
            R[j] = nums[mid + 1 + j];
        }
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                nums[k++] = L[i++];
            }else{
                nums[k++] = R[j++];
            }
        }
        while (i < n1) {
            nums[k++] = L[i++];
        }
        while (j < n2) {
            nums[k++] = R[j++];
        }
    }

}
