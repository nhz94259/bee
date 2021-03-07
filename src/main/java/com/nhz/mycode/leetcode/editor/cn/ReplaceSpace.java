package com.nhz.mycode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReplaceSpace {
    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuffer("We Are Happy.")));
    }

    public static String replaceSpace(StringBuffer str) {
        List<String> list = Arrays.asList(str.toString().split(" "));
        return list.stream().collect(Collectors.joining("%20"));
    }
}
