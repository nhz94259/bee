package com.nhz.mycode.leetcode.editor.cn.sword;

import com.nhz.mycode.leetcode.editor.cn.model.ListNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class getIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        while(cur!=null){
            set.add(cur);
            cur = cur.next;
        }
        cur = headB;
        while(cur!=null){
            if(set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }
}
