//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学

package com.nhz.mycode.leetcode.editor.cn;
public class ReverseInteger {
    public int reverse(int x) {
        boolean negative = false;
        if (x < 0) {
            negative = true;
            x = -x;
        }
        int res = 0 ;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x/10;
        }
        return negative ? -res : res;
    }

    public static void main(String[] args) {
        ReverseInteger method = new ReverseInteger();
        System.out.println(method.reverse(-123));
    }
}