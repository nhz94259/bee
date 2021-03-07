package com.nhz.mycode.leetcode.editor.cn;

import org.springframework.util.StringUtils;


public class MaxString {


    public static void main(String[] args) {
        String s = "Abbbbcddddddc";
        System.out.println(findTheMaxString(s));
    }

    public static int findTheMaxString(String s) {
        if (StringUtils.isEmpty(s)) {
            return 0;
        }
        int end = 0, ans = 0, begin = 0;
        char[] chars = s.toCharArray();
        char last = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == last) {
                end = i;
            } else {
                last = chars[i];
                if (end - begin + 1 > ans) {
                    ans = end - begin + 1;
                }
                begin = i;
            }
        }
        if (end - begin + 1 > ans) {
            ans = end - begin + 1;
        }
        return ans;
    }
}
