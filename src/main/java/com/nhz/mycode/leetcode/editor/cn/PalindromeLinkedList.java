//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针

package com.nhz.mycode.leetcode.editor.cn;


public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(0);
        ListNode node2 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = null;
        System.out.println(isPalindrome(head));
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode firstHead = head;
        ListNode secondHead = head;
        ListNode firstTail = head;
        ListNode p = null;
        int length = 0;
        while (firstHead != null) {
            firstHead = firstHead.next;
            ++length;
        }
        int mid = length / 2;
        while (mid > 1) {
            firstTail = firstTail.next;
            --mid;
        }
        secondHead = length % 2 == 0 ? firstTail.next : firstTail.next.next;
        firstTail.next = null;
        while (head != null) {
            p = head;
            head = head.next;
            p.next = firstHead;
            firstHead = p;
        }
        while (firstHead != null) {
            if (firstHead.val == secondHead.val) {
                firstHead = firstHead.next;
                secondHead = secondHead.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode newHead = null;
        ListNode p = null;
        while (head != null) {
            p = head;
            head = head.next;
            p.next = newHead;
            newHead = p;
        }
        return newHead;
    }


    //leetcode submit region end(Prohibit modification and deletion)
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}