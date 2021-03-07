package com.nhz.mycode.completed.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLab {
    public static void main(String[] args) {
        AtomicInteger num = new AtomicInteger();
        num.addAndGet(10);
        System.out.println(num.get());
    }
}
