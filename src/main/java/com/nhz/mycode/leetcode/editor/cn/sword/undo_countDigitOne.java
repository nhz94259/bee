package com.nhz.mycode.leetcode.editor.cn.sword;


public class undo_countDigitOne {
    public int countDigitOne(int n) {
        int count = 0;
        while(n!=0){//11 1011
            int temp = n & (-n);   //取到最后一个1
            if(temp!=0&&temp%5==1){
                count++ ;
            }
            if(n%10==0){
                count++ ;
            }
            n = n & (n -1);       //去除掉最后个1
        }
        return count;
    }

    public static void main(String[] args) {
        undo_countDigitOne method = new undo_countDigitOne();
        System.out.println(method.countDigitOne(11));
    }

}
