package com.nhz.mycode.leetcode.editor.cn.sort;

public class binarySearch {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        binarySearch method = new binarySearch();
        System.out.println(method.binarySearch(nums, 2));
    }
    private  int binarySearch(int[] nums,int target){
        int left = 0;
        int right = nums.length -1;
        while(left<=right){
            int pivot = left + (right-left)/2;
            if(target>nums[pivot]){
                left = pivot + 1;
            }else{
                right = pivot -1;
            }
        }
        return left;
    }
}
