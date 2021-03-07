package com.nhz.mycode.completed.arthas;


public class GCOverheadLimit {

    public static int M =  1024*1024 ;
    public static void main(String[] args) throws InterruptedException {
        GCOverheadLimit xssMemoryLab = new GCOverheadLimit();
        xssMemoryLab.recursion(3);
    }

    private void recursion(int n) {
        if (n > 3) {
            return;
        }
        byte[] arr = new byte[1 * M];
        System.out.println("申请1024字节空间");

        recursion(n - 1);
    }

}
