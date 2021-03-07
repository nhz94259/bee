package com.nhz.mycode.leetcode.editor.cn.sword;

import com.nhz.mycode.leetcode.editor.cn.model.ListNode;

public class reverseList {

    public ListNode  reverse(ListNode head){
        if(head==null) return head;
        ListNode newHead = null;
        ListNode node ;
        while(head!=null){
            node = head;
            head  = head.next;
            node.next = newHead;
            newHead = node;
        }
        return newHead;
    }
}
