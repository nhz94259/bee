package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.*;

public class permutation_duodo {
    public static void main(String[] args) {
        permutation_duodo method = new permutation_duodo();
        String[] result = method.permutation("abc");
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public String[] permutation(String s) {
        if(s.length() == 0) return new String[0];
        char[] chars = s.toCharArray();
        Deque<Character> path = new LinkedList<>();
        Set<String> res = new HashSet<>();
        int len = s.length();
        boolean[] used = new boolean[len];
        dfs(0,chars,len,path,res,used);
        return res.toArray(new String[res.size()]);
    }
    private void dfs(int depth, char[] chars, int len, Deque<Character> path , Set<String> result,boolean[] used){
        if(depth == len){
            Iterator<Character> iterator = path.iterator();
            StringBuilder stringBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                stringBuilder.append(iterator.next());
            }
            result.add(stringBuilder.toString());
            return;
        }
        for(int i = 0 ; i< len ; i++){
            if(used[i]) continue;
            path.addLast(chars[i]);
            used[i] = true;
            dfs(depth+1,chars,len,path,result,used);
            path.removeLast();
            used[i] = false;
        }
    }

}
