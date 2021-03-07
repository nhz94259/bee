package com.nhz.mycode.leetcode.editor.cn.sort;

import java.util.Date;

public class MultiQuickLab {
    public static void main(String[] args) {
        int[] array1 = GenerateArray.closeArray(10000);
        int[] array2 = array1.clone();
        QuickSort common = new QuickSort();

        ThreeWaysQuickSort way3 = new ThreeWaysQuickSort();
        Date test_start1 = new Date();
        common.quickSort(array1, 0, array1.length - 1);
        Date test_end1 = new Date();
        Date test_start2 = new Date();
        way3.way3quickSort(array2, 0, array1.length - 1);
        Date test_end2 = new Date();
        long time1 =  test_end1.getTime()-test_start1.getTime();
        long time2 =  test_end2.getTime() -test_start2.getTime();
        System.out.println(String.format("普通快排：耗时%sms",time1));
        System.out.println(String.format("三路快排：耗时%sms",time2));
    }

}
