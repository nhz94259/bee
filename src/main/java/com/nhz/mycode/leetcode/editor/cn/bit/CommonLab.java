package com.nhz.mycode.leetcode.editor.cn.bit;

public class CommonLab {
    public static void swap(Integer a, Integer b) {
        a = a^b;
        b = a^b;
        a = a^b;
    }
}
