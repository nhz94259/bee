//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划

package com.nhz.mycode.leetcode.editor.cn;
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start= 0 ,end = 0;
        char[] arr = s.toCharArray();
        for(int i = 1 ;i < arr.length -1;i++){
            int len1 = expandAroundCenter(arr, i, i);
            int len2 = expandAroundCenter(arr,i,i+1);
            int len = Math.max(len1,len2);
            if ((end - start) < len) {
                start = i - (len - 1)/2;
                end = len/2 + i ;
            }
        }
        return s.substring(start, end + 1);
    }
    // 1 2 3 2 1
    // 1 2 3 4 4 3 2 1

    private int expandAroundCenter(char[] arr ,int left ,int right){
        while(left>=0&&right<arr.length&&arr[left]==arr[right]){
            left--;
            right++;
        }
        // 7 - 0 - 1
        return right -left - 1;
    }
    public static void main(String[] args) {
        LongestPalindromicSubstring method = new LongestPalindromicSubstring();
        System.out.println(method.longestPalindrome("12321"));
    }
}