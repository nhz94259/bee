package com.nhz.mycode.leetcode.editor.cn;

public class searchMatrix {
    public static void main(String[] args) {
        int[][] nums = new int[1][2];
        nums[0][0] = 1;
        nums[0][1] = 3;
        System.out.println(searchMatrix(nums, 3));

    }

    public static boolean searchMatrix(int[][] array, int target) {

        if (array.length == 0 || array[0].length == 0) return false;

        int column = array[0].length - 1;

        for (int i = 0; i <= array.length - 1; i++) {

            for (int j = 0; j <= column; j++) {

                if (array[i][j] > target) {
                    column = j;
                    break;
                } else if (array[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
