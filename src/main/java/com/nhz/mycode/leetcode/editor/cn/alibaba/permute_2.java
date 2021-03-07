package com.nhz.mycode.leetcode.editor.cn.alibaba;

import org.springframework.util.CollectionUtils;

import java.util.*;

public class permute_2 {

    /*
    问题：打印全排列，输入正整数n，打印出1-n的全排列。比如：
    输入3
    打印：123 132 213 231 312 321
    */
    public static void main(String[] args) {
        int a[] = {1, 2, 3};
        permute(a);
    }

    private static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        for (int num : nums) {
            path.add(num);
        }
        int len = nums.length;
        dfs(len,0,path,res);
        return res;
    }
    private static void dfs(int len ,int first,List<Integer> path ,List<List<Integer>> result){
        if (first == len) {
            result.add(new ArrayList(path));
            return;
        }
        for(int i = first;i<len;i++){
            Collections.swap(path,i,first);
            dfs(len,first+1,path,result);
            Collections.swap(path,first,i);
        }
    }


}
