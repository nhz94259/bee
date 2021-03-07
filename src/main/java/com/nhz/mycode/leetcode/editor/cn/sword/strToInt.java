package com.nhz.mycode.leetcode.editor.cn.sword;

public class strToInt {

    public int strToInt(String str) {
        boolean negative= false;
        str = str.trim();
        char[] chars = str.toCharArray();
        if(str.startsWith("-")) {
            negative = true;
        }
        StringBuilder sb = new StringBuilder();
        int i= (negative ==true) ?1:0;
        for(;i<chars.length;i++){
            if(chars[i]>'9'||chars[i]<'0') break;
            sb.append(chars[i]);
        }
        long value = Long.valueOf(sb.toString());
        if(value>Math.pow(2,31)-1) return negative?(-1*Integer.MAX_VALUE):Integer.MAX_VALUE;

        return (int) ((negative)? -1*value:value);
    }

    public static void main(String[] args) {
        strToInt method = new strToInt();
        System.out.println(method.strToInt("words and 987"));
    }

}
