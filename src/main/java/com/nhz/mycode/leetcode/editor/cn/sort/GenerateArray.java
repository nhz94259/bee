package com.nhz.mycode.leetcode.editor.cn.sort;

import java.util.Random;

public class GenerateArray {
    private static Random random = new Random();

    public static int[] closeArray(int capacity){
        int result[] = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            result[i] = random.nextInt(5);
        }
        return result;
    }
}
