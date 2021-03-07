//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。 
//
// 若可行，输出任意可行的结果。若不可行，返回空字符串。 
//
// 示例 1: 
//
// 
//输入: S = "aab"
//输出: "aba"
// 
//
// 示例 2: 
//
// 
//输入: S = "aaab"
//输出: ""
// 
//
// 注意: 
//
// 
// S 只包含小写字母并且长度在[1, 500]区间内。 
// 
// Related Topics 堆 贪心算法 排序 字符串

package com.nhz.mycode.leetcode.editor.cn;


import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class ReorganizeString {
    public static String reorganizeString(String str) {
        if (str.length() < 2) {
            return "";
        }
        int maxCount = 0;
        int[] counts = new int[26];
        for (char c : str.toCharArray()) {
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (str.length() + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return counts[c2 - 'a'] - counts[c1 - 'a'];
            }
        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuilder resultStr = new StringBuilder();
        while (queue.size() > 1) {
            char c1 = queue.poll();
            char c2 = queue.poll();
            resultStr.append(c1);
            resultStr.append(c2);
            int idx1 = c1 - 'a';
            int idx2 = c2 - 'a';
            counts[idx1]--;
            counts[idx2]--;
            if (counts[idx1] > 0) {
                queue.offer(c1);
            }
            if (counts[idx2] > 0) {
                queue.offer(c2);
            }
        }
        if (queue.size() > 0) {
            resultStr.append(queue.poll());
        }
        return resultStr.toString();
    }

    public static void main(String[] args) {
        String str = "zhmyooooo";
        System.out.println(reorganizeString(str));
    }
}