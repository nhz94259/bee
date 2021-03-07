package com.nhz.mycode.leetcode.editor.cn.tools;

public class ArraysUtils {
    /**
     * 交换数组内两个元素
     */
    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j]=tmp;
    }
}
