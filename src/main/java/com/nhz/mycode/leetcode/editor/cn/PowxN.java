//实现 pow(x, n) ，即计算 x 的 n 次幂函数。 
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找

package com.nhz.mycode.leetcode.editor.cn;

public class PowxN {

    public static void main(String[] args) {
        PowxN method = new PowxN();
        System.out.println(method.myPow(2, -2147483648));
    }
    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        long b = n;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        double pow = 1;
        while (b >= 1) {
            if ((b & 1) == 1) {
                pow *= x;
            }
            x *= x;
            b >>= 1;
        }
        return pow;
    }

    public double myPow2(double x, int n) {
        return n > 0 ? helper(x, n) : 1 / helper(x, -n);
    }

    public double helper(double x, long n) {
        if (n == 0) {
            return 1;
        }
        long b = (long) n;
        double ans = helper(x, b / 2);
        return (n & 1) == 1 ? ans * ans * x : ans * ans;
    }

}