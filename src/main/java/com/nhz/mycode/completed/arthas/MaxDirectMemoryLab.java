package com.nhz.mycode.completed.arthas;

import org.junit.Test;
import sun.misc.Cleaner;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class MaxDirectMemoryLab {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        System.out.println("maxMemoryValue:"+(sun.misc.VM.maxDirectMemory()>>10));

        ByteBuffer buffer=ByteBuffer.allocateDirect(1025*1024);
        Class<?> c = Class.forName("java.nio.Bits");
        Field maxMemory = c.getDeclaredField("maxMemory");
        maxMemory.setAccessible(true);
        synchronized (c) {
            Long maxMemoryValue = (Long)maxMemory.get(null);
            System.out.println("maxMemoryValue:"+(maxMemoryValue>>10));
        }
    }

    /**
     * DisableExplicitGC 关闭时不能调用System.gc 手动回收内存
     */
    @Test
    public void testAllocateDirector() throws Exception{
        ByteBuffer buffer=ByteBuffer.allocateDirect(1024*10241);
        Field cleanerField = buffer.getClass().getDeclaredField("cleaner");
        cleanerField.setAccessible(true);
        Cleaner cleaner = (Cleaner) cleanerField.get(buffer);
        cleaner.clean();
    }
}
