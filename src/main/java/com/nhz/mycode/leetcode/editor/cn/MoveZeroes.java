//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//


package com.nhz.mycode.leetcode.editor.cn;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != 0) {
                nums[i] = nums[j];
                i++;
            }
        }
        for (; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        int currIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int j = findNextNotZeroIdx(i + 1, nums);
                if (j == -1) {
                    break;
                }
                nums[i] = nums[j];
                nums[j] = 0;
            }
        }
    }

    public int findNextNotZeroIdx(int start, int[] nums) {
        for (int i = start; i < nums.length; i++) {
            if (nums[i] != 0) {
                return i;
            }
        }
        return -1;
    }
}