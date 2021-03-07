package com.nhz.mycode.leetcode.editor.cn.ds;

/**
 * 循环队列
 * 解决数据搬移问题
 */
public class CircularQueue {

    private String[] items;
    private int capacity = 0;

    private int head = 0;
    private int tail = 0;

    public CircularQueue(int capacity) {
        items = new String[capacity];
        this.capacity = capacity;
    }
    //入队
    public boolean enqueue(String item) {
        if ((tail + 1) % capacity == head) {
            return false;
        }
        items[tail] = item;
        tail = (tail + 1) % capacity;
        return true;
    }

    //出队
    public String dequeue() {
        if (head == tail) {
            return null;
        }
        String ret = items[head];
        head = (head + 1) % capacity;
        return ret;
    }
}
