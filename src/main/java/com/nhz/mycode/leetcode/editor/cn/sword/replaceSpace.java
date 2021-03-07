package com.nhz.mycode.leetcode.editor.cn.sword;

public class replaceSpace {

    public String replaceSpace(String s) {
        StringBuilder result = new StringBuilder();
        for(char c:s.toCharArray()){
            if(c == ' '){
                result.append("%20");
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }
}
