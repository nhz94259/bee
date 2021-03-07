package com.nhz.mycode.leetcode.editor.cn;

public class ReverseWords {

    public static void main(String[] args) {
        reverse("a good   example");
    }

    public static String reverse(String s) {
        if (s.trim().isEmpty()) {
            return "";
        }
        String[] words = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (!words[i].trim().isEmpty()) {
                stringBuilder.append(words[i].trim()).append(" ");
            }
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }
}
