package com.nhz.mycode.completed.javaLayout;


import java.util.concurrent.TimeUnit;

public class JavaLayoutLab {


    private static volatile JavaLayoutLab INSTANCE;

    private JavaLayoutLab() {
    }
    public static JavaLayoutLab getInstance() {
        // 业务代码
        if (INSTANCE == null) {
            // 双重检查
            synchronized (JavaLayoutLab.class) {
                if (INSTANCE == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new JavaLayoutLab();
                }
            }
        }
        return  INSTANCE;
    }
    public void m() {
        System.out.println("m");
    }
    public static void main(String[] args) {
        for (int i = 0; i< 1000; i++) {
            new Thread(() ->
                    System.out.println(JavaLayoutLab.getInstance().hashCode())
            ).start();
        }
    }
}
