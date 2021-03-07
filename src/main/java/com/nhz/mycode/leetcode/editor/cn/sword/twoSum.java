package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.HashMap;
import java.util.Map;

public class twoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num:nums){
            if(map.containsKey(target-num)){
                return new int[]{target-num,num};
            }
            map.put(num,num);
        }
        return new int[]{-1,-1};
    }
}
