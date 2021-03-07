package com.nhz.mycode.leetcode.editor.cn.sword;

import com.nhz.mycode.leetcode.editor.cn.model.ListNode;

public class getKthFromEnd {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head , later = head;
        for(int i = 0 ; i< k ; i++){
            former = former.next;
        }
        while(former !=null){
            former = former.next;
            later = later.next;
        }
        return later;
    }
}
