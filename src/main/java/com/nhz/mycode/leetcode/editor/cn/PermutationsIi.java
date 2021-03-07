//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法

package com.nhz.mycode.leetcode.editor.cn;

import java.util.*;

public class PermutationsIi {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        Deque<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backTrace(0,len,result,path,nums,used);
        return result;
    }

    private void backTrace(int idx,int len ,List<List<Integer>> result, Deque<Integer> path,int[] nums,boolean[] used){
        if(idx==len){
            result.add(new ArrayList(path));
            return;
        }
        for(int i = 0 ; i < len ;i++){
            if(used[i]||(i>0&&nums[i-1]==nums[i])&&used[i - 1])
                continue;
            path.addLast(nums[i]);
            used[i] = true;
            backTrace(idx+1,len,result,path,nums,used);
            used[i] = false;
            path.removeLast();
        }
    }

}