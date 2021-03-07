package com.nhz.mycode.leetcode.editor.cn.sword;

import com.nhz.mycode.leetcode.editor.cn.model.ListNode;

public class deleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        if(head==null) return head;
        if(head.val == val) {
            head = head.next;
            return head;
        }
        ListNode cur = head.next;
        ListNode pre = head;
        while(cur!=null){
            if(cur.val == val){
                pre.next = cur.next;
                return head;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return head;
    }
}
