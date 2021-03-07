//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串

package com.nhz.mycode.leetcode.editor.cn;
public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        //推论结果最大长度为乘数的位数和
        int len = m + n;
        int[] ansArr = new int[len];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        //处理每一位数字
        for (int i = len - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        //判断需要从ansArr开始统计ansArr[0] 是0证明不需要要处理，从1开始即可
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < len) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings method = new MultiplyStrings();
        System.out.println(method.multiply2("123", "456"));
    }


    public String multiply2(String num1, String num2) {
        if("0".equals(num1)||"0".equals(num2)) return "0";
        int m = num1.length();
        int n = num2.length();
        int len = m + n;
        int[] res = new int[len];
        for(int i = m-1;i>=0;i--){
            int x = num1.charAt(i) - '0';
            for(int j = n-1;j>=0;j--){
                int y = num2.charAt(j) - '0';
                res[i+j+1] +=x*y;
            }
        }
        for(int i = len-1;i>0;i--){
            res[i-1] += res[i]/10;
            res[i] = res[i]%10;
        }
        StringBuilder ans = new StringBuilder();
        int index = res[0]==0?1:0;
        for(int i = index;i<len;i++){
            ans.append(res[i]);
            i++;
        }
        return ans.toString();
    }
}