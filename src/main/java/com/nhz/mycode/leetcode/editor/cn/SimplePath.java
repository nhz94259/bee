package com.nhz.mycode.leetcode.editor.cn;


import org.apache.logging.log4j.util.Strings;

import java.util.Stack;

public class SimplePath {
    public static void main(String[] args) {
        System.out.println(simplePath("/a/./b/../../c/"));
        System.out.println(simplePath("/a/../../b/../c//.//"));
        System.out.println(simplePath("/a//b////c/d//././/.."));
        System.out.println(simplePath("/../"));
        System.out.println(simplePath("/home/"));
    }


    public static String simplePath(String path) {
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        Stack<String> ans = new Stack<>();
        for (int i = paths.length - 1; i >= 0; i--) {
            if (paths[i] != null && !paths[i].trim().isEmpty()) {
                stack.push(paths[i]);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek().equals(".")) {
                stack.pop();
            } else if (stack.peek().equals("..")) {
                stack.pop();
                if (!ans.isEmpty()) {
                    ans.pop();
                }
            } else {
                ans.push(stack.pop());
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (ans.isEmpty()) {
            return "/";
        }
        while (!ans.isEmpty()) {
            stringBuilder.insert(0, ans.pop()).insert(0, "/");
        }
        return stringBuilder.toString();
    }
}
