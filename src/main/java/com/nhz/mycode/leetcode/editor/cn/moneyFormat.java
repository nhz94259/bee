package com.nhz.mycode.leetcode.editor.cn;

import java.util.Arrays;

public class moneyFormat {
    public static void main(String[] args) {
        format("1231231231231");
    }

    public static String format(String s) {
        char[] chars = s.toCharArray();
        // 除数
        int nums1 = chars.length / 3;
        // 余数
        int nums2 = chars.length % 3;
        int total = nums2 == 0 ? nums1 - 1 : nums1;
        int newLength = s.length() + total;
        char[] anschar = new char[newLength];
        int index = chars.length - 1;
        int count = 0;
        for (int i = newLength - 1; i >= 0; i--) {
            if ((newLength - i - count) % 3 == 0) {
                anschar[i] = chars[index];
                index--;
                count++;
                i = i - 1;
                anschar[i] = ',';
            } else {
                anschar[i] = chars[index];
                index--;
            }
        }
        System.out.println(String.valueOf(anschar) + " 元");
        return Arrays.toString(anschar);
    }
}
