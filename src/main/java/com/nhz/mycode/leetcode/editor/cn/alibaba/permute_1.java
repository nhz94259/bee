package com.nhz.mycode.leetcode.editor.cn.alibaba;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class permute_1 {

    /*
    问题：打印全排列，输入正整数n，打印出1-n的全排列。比如：
    输入3
    打印：123 132 213 231 312 321
    */
    public static void main(String[] args) {
        int a[] = {1, 2, 3};
        permute(a);

    }
    public static List<List<Integer>> permute(int[]nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if(len==0) return res;
        boolean[] used = new boolean[len];
        Deque<Integer> path = new LinkedList<>();
        int depth = 0;
        dfs(nums,len,depth,used,path,res);
        return res;
    }

    private static void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if(depth==len){
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0 ; i < len ;i++){
            if(used[i])  continue;
            path.addLast(nums[i]);
            used[i] =true;
            dfs(nums, len, depth, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }

}
