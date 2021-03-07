package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.ArrayList;

public class findContinuousSequence {

    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 1 ; i<=(target+1)/2;i++){
            int j = i+1;
            while(sum(i,j)<target){
                j++;
            }
            if(sum(i,j)==target){
                int cur = i;
                int[] subArray = new int[j-i+1];
                for(int n = 0 ;n<j-i+1;n++){
                    subArray[n] = cur++;
                }
                list.add(subArray);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
    public int sum(int start,int end){
        int sum = 0 ;
        for(int i = start ; i<=end;i++){
            sum = sum + i;
        }
        return sum;
    }

    public static void main(String[] args) {
        findContinuousSequence method = new findContinuousSequence();
        System.out.println(method.findContinuousSequence(3));
    }
}
