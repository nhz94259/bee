package com.nhz.mycode.leetcode.editor.cn.sword;

import java.util.*;

public class MaxQueue {
    Queue<Integer> q;
    Deque<Integer> d;
    public MaxQueue() {
        q = new LinkedList<>();
        d = new LinkedList<>();
    }
    public int max_value() {
        if (d.isEmpty()) {
            return -1;
        }
        return d.peekFirst();
    }

    public void push_back(int value) {
        while (!d.isEmpty() && d.peekLast() < value) {
            d.pollLast();
        }
        d.offerLast(value);
        q.offer(value);
    }

    public int pop_front() {
        if (q.isEmpty()) {
            return -1;
        }
        int ans = q.poll();
        if (ans == d.peekFirst()) {
            d.pollFirst();
        }
        return ans;
    }

    public static void main(String[] args) {
        Deque<Integer> d = new LinkedList<>();
        d.add(1);
        d.add(2);
        d.add(3);
        System.out.println(d.peekFirst());
        System.out.println(d.peekLast());
    }
}
