package com.nhz.mycode.leetcode.editor.cn.sword;

public class movingCount {
    public int movingCount(int m, int n, int k) {
        if(k==0) return 1;
        int ans = 0;
        boolean[][] visited = new boolean[m][n];
        ans = dfs(0,0,k,m,n,visited);
        return ans;
    }

    private int dfs(int i,int j,int k,int m ,int n,boolean[][] visited ){
        if(i<0||j<0||i>=m||j>=n||visited[i][j]||!valid(i,j,k)) return 0;
        visited[i][j] = true;
        return 1 + dfs(i+1,j,k,m,n,visited) + dfs(i-1,j,k,m,n,visited) + dfs(i,j+1,k,m,n,visited) +  dfs(i,j-1,k,m,n,visited);
    }
    private boolean valid(int i ,int j ,int k){
        int sum = 0;
        while(i!=0){
            sum +=i%10;
            i = i/10;
        }
        while(j!=0){
            sum +=j%10;
            j = j/10;
        }
        return !(sum>k);
    }
}
