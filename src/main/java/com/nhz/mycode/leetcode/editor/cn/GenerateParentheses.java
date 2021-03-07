//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法

package com.nhz.mycode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(n, n, result, "");
        return result;
    }

    private void generate(int left, int right, List<String> result, String str) {
        if (left == 0 && right == 0) {
            result.add(str);
        }
        if (left > right) {
            return;
        }
        if (left > 0) {
            generate(left - 1, right, result, str + "(");
        }
        if (right > 0) {
            generate(left, right - 1, result, str + ")");
        }
    }
}