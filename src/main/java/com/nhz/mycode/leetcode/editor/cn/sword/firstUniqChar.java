package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.LinkedHashMap;
import java.util.Map;

public class firstUniqChar {
    public char firstUniqChar(String s) {
        Map<Character,Boolean> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for(char ch :chars){
            map.put(ch,!map.containsKey(ch));
        }
        for(Map.Entry<Character,Boolean> d : map.entrySet()){
            if(d.getValue()) return d.getKey();
        }
        return ' ';
    }
}
