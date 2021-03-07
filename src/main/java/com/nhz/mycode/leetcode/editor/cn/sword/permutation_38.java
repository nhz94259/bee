package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.*;

public class permutation_38 {

    private char[] c ;
    private int len ;
    private List<String> result = new LinkedList<>();

    public String[] permutation(String s) {
        len = s.length();
        if (len == 0) {
            return new String[0];
        }
        c = s.toCharArray();
        dfs(0);
        return result.toArray(new String[result.size()]);
    }

    private void dfs(int start){
        if(start == len) {
            result.add(String.valueOf(c));
            return;
        }
        Set<Character> set= new HashSet<>();
        for(int i = start ; i < len ;i++){
            if(set.contains(c[i])) continue;
            set.add(c[i]);
            swap(i, start);
            dfs(start+1);
            swap(i, start);
        }
    }

    void swap(int i,int j){
        char temp = c[i];
        c[i]  = c[j];
        c[j] = temp;
    }


    public static void main(String[] args) {

    }
}
