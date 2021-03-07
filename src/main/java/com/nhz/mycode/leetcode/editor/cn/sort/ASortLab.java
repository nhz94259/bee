package com.nhz.mycode.leetcode.editor.cn.sort;

import java.util.Arrays;

public class ASortLab {

    public static void main(String[] args){
        int[] array = {23, 132, 213, 123, 2, 4, 66, 123, 53};
        System.out.println("before sort:" + Arrays.toString(array));
        ASortLab sort = new ASortLab();
        sort.mergerSort(array,0,array.length-1);
        System.out.println("after  sort:" + Arrays.toString(array));
    }
    private void mergerSort(int[] nums,int left ,int right){
        if(left<right){
            int mid = left + (right-left)/2;
            mergerSort(nums,left,mid);
            mergerSort(nums,mid+1,right);
            merge(nums, left, right, mid);
        }
    }
    // 1 2 3 4 5 6
    private void merge(int[] nums ,int left,int right ,int mid){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = nums[left+i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = nums[mid + j + 1];
        }
        int k = left;
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if (L[i] < R[j]) {
                nums[k++] = L[i++];
            } else {
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
