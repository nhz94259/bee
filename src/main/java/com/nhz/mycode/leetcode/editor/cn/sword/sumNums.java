package com.nhz.mycode.leetcode.editor.cn.sword;

public class sumNums {

    public int sumNums1(int n) {
        int ans = 0;
        int pair = n/2;
        ans = pair*(1+n);
        if((n&1)==1) ans +=(n+1)/2;
        return ans;
    }

    public int sumNums(int n) {

        return (n/2)*(1+n)+((n&1)==1?(n+1)>>1:0);
    }
}
