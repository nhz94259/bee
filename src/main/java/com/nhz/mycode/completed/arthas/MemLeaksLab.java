package com.nhz.mycode.completed.arthas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *  启动参数 -Xmx256m -XX:+HeapDumpOnOutOfMemoryError
 */
public class MemLeaksLab {

    private static final int THREAD_LOOP_SIZE = 500;
    private static final int MOCK_DIB_DATA_LOOP_SIZE = 10000;
    public static int M =  1024 * 1024;
    public static int K =  1024;

    /**
     * VM option -Xmx50m 分配50m内存
     */
    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //添加一个大对象
                threadLocal.set(new MemLeaksLab().addBigList());
                System.out.println(Thread.currentThread().getName());
                //threadLocal.remove(); // 执行结束后不手动 remove
            }
        };

        for (int i = 0; i < 500; i++) {
            executorService.execute(runnable);
            TimeUnit.SECONDS.sleep(1);
        }
        executorService.shutdown();
    }

    private List<User> addBigList() {
        List<User> params = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);
        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            params.add(new User("nahongze", "password" + i, "男", i));
        }
        return params;
    }

    public class User {
        private String userName;
        private String password;
        private String sex;
        private int age;
        private byte[] array;
        public User(String userName, String password, String sex, int age) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
            this.age = age;
            this.array = new byte[1 * K];
        }
    }
}
