//给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。 
//
// 示例： 
//
// 给定一个链表: 1->2->3->4->5, 和 n = 2.
//
//当删除了倒数第二个节点后，链表变为 1->2->3->5.
// 
//
// 说明： 
//
// 给定的 n 保证是有效的。 
//
// 进阶： 
//
// 你能尝试使用一趟扫描实现吗？ 
// Related Topics 链表 双指针

package com.nhz.mycode.leetcode.editor.cn;


public class RemoveNthNodeFromEndOfList {

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            int length = 0;
            ListNode p = head;
            while (null != p) {
                length++;
                if (p.next != null) {
                    p = p.next;
                } else {
                    break;
                }
            }
            int sub = length - n;
            if (sub == 0 && length == 1) {
                return null;
            }
            p = head;
            if (sub == 0) {
                return head.next;
            }
            while (sub > 1) {
                p = p.next;
                sub--;
            }
            p.next = p.next.next;
            return head;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static final class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}