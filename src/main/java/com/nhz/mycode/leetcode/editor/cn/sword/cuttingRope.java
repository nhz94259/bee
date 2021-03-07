package com.nhz.mycode.leetcode.editor.cn.sword;

public class cuttingRope {

    public int cuttingRope2(int n) {
        if(n<=3) return n-1;
        int[] dp = new int[n+1];
        for(int i = 4 ; i <= n ;i++){
            for(int j = 0 ; j <= (i/2); j++){
                dp[i] = Math.max(dp[i],Math.max(j * (i - j),j * dp[i - j]));
            }
        }
        return dp[n];
    }
    public int cuttingRope(int n){
        if(n<=3) return n-1;
        int a = n / 3;
        int b = n % 3;
        if(b==0) return (int)Math.pow(3,a);
        if(b==1) return (int)Math.pow(3,a-1)*4;
        return (int)Math.pow(3,a)*2;
    }
}
