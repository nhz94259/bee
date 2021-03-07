package com.nhz.mycode.completed.gc;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class FullGC {
    public static int M =  1024 * 1024;
    private static MemoryMXBean mxb = ManagementFactory.getMemoryMXBean();
    public static void main(String[] args) throws IOException {

        byte[] array1 = new byte[4 * M];
        printJvmUsed();
        array1 = null;
        System.gc();
        printJvmUsed();
        byte[] array2 = new byte[4 * M];
        printJvmUsed();
        byte[] array3 = new byte[4 * M];
        printJvmUsed();
       // byte[] array4 = new byte[4 * M];
        //byte[] array5 = new byte[4 * M];


        System.out.println(1388314624/M/1024);


       //System.in.read();

    }

   private static void printJvmUsed() {
        System.out.println("Max:" + mxb.getHeapMemoryUsage().getMax() / 1024 / 1024 + "MB");
        System.out.println("Init:" + mxb.getHeapMemoryUsage().getInit() / 1024 / 1024 + "MB");
        System.out.println("Committed:" + mxb.getHeapMemoryUsage().getCommitted() / 1024 / 1024 + "MB");
        System.out.println("Used:" + mxb.getHeapMemoryUsage().getUsed() / 1024 / 1024 + "MB");
        System.out.println(mxb.getHeapMemoryUsage().toString());
    }


}
