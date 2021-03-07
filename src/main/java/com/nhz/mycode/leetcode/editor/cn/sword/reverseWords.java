package com.nhz.mycode.leetcode.editor.cn.sword;

public class reverseWords {

    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder ans  = new StringBuilder();
        for(int i = words.length-1;i>=0;i--){
            ans.append(words[i]).append(" ");
        }
        return ans.substring(0,ans.length()-1);
    }

    public String reverseWords2(String s) {
        String[] words = s.trim().split(" ");
        StringBuilder ans  = new StringBuilder();
        for(int i = words.length-1;i>=0;i--){
            if(words[i].equals("")) continue;
            ans.append(words[i]+" ");
        }
        return ans.toString().trim();
    }

    public static void main(String[] args) {
        String s =  "11";
        System.out.println(s.substring(0, s.length()-1));
    }

}
