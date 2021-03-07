//给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: 1->2->3->4->5->NULL, k = 2
//输出: 4->5->1->2->3->NULL
//解释:
//向右旋转 1 步: 5->1->2->3->4->NULL
//向右旋转 2 步: 4->5->1->2->3->NULL
// 
//
// 示例 2: 
//
// 输入: 0->1->2->NULL, k = 4
//输出: 2->0->1->NULL
//解释:
//向右旋转 1 步: 2->0->1->NULL
//向右旋转 2 步: 1->2->0->NULL
//向右旋转 3 步: 0->1->2->NULL
//向右旋转 4 步: 2->0->1->NULL 
// Related Topics 链表 双指针

package com.nhz.mycode.leetcode.editor.cn;

import com.nhz.mycode.leetcode.editor.cn.model.ListNode;

public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        if( head==null||head.next==null||k==0){
            return head;
        }
        ListNode node = head;
        int len = 1;
        while(node.next!=null){
            node = node.next;
            ++len;
        }

        k = k % len;
        if (k == 0) {
            return head;
        }
        ListNode quick = head;
        ListNode slow  = head;
        int count = 0;
        while(quick.next!=null){
            if(count >= k){
                slow = slow.next;
            }else{
                count++;
            }
            quick = quick.next ;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        quick.next = head;
        head = newHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0, new ListNode(1));
        rotateRight(head, 2);
    }
}