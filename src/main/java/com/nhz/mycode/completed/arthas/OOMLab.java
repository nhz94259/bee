package com.nhz.mycode.completed.arthas;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashSet;

@Slf4j
public class OOMLab {

    public static int M =  1024 * 1024;

    private static HashSet<byte[]> hashSet = new HashSet();

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    byte[] array = new byte[1 * M];
                    hashSet.add(array);
                    System.out.println("添加了"+hashSet.size()+"M");
                    Thread.sleep(3000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"addHashSetThread").start();
        System.in.read();
    }
}
