package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class singleNumbers_56_2_redo {

    public int singleNumber(int[] nums) {
        if( nums == null|| nums.length == 0) return 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0 ; i < nums.length ; i++){
            int a = map.getOrDefault(nums[i],0);
            map.put(nums[i],a+1);
        }
        for(Integer key : map.keySet()){
            if(map.get(key)==1){
                return key;
            }
        }
        return -1;
    }
}
