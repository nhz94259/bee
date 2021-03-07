//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window

package com.nhz.mycode.leetcode.editor.cn;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null) {
                return 0;
            }
            char[] chars = s.toLowerCase().toCharArray();
            int max = 0;
            //存上一次数组位置
            int currentLen = 0;
            int[] charIndex = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            for (int i = 0; i < chars.length; i++) {
                if (charIndex[chars[i] - 'a'] == -1) {
                    charIndex[chars[i] - 'a'] = i;
                    currentLen++;
                } else if (i - charIndex[chars[i] - 'a'] < currentLen) {
                    int temp = charIndex[chars[i] - 'a'];
                    charIndex[chars[i] - 'a'] = i;
                    i = temp;
                    currentLen = 0;
                } else {

                }
                max = Math.max(max, currentLen);
            }
            return max;
        }

        public int lengthOfLongestSubstring2(String s) {
            char[] chars = s.toCharArray();
            int max = 0;
            HashSet set = new HashSet<Character>();
            for (int i = 0; i < chars.length; i++) {
                set.clear();
                set.add(chars[i]);
                for (int j = i + 1; j < chars.length; j++) {
                    if (set.contains(chars[j])) {
                        break;
                    } else {
                        set.add(chars[i]);
                    }
                }
                if (max < set.size()) {
                    max = set.size();
                }
            }
            return max;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}