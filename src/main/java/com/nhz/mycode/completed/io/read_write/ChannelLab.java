package com.nhz.mycode.completed.io.read_write;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ChannelLab {

    public static void main(String[] args) throws IOException {
        //1.创建一个RandomAccessFile（随机访问文件）对象，
        RandomAccessFile raf = new RandomAccessFile("/Users/nahongze/Documents/code/mycode/src/main/resources/niodata.txt", "rw");
        //通过RandomAccessFile对象的getChannel()方法。FileChannel是抽象类。
        FileChannel inChannel = raf.getChannel();
        //2.创建一个读数据缓冲区对象
        ByteBuffer buf = ByteBuffer.allocate(48);
        //3.从通道中读取数据
        int bytesRead = inChannel.read(buf);
        //创建一个写数据缓冲区对象
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        buf2.put("file channel test".getBytes());//写入数据
        buf2.flip();//将写模式转为读模式
        //inChannel.write(buf2);
        inChannel.write(buf);
        //将数据存到buffer里，然后通过FileChannel write 写入
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            //Buffer有两种模式，写模式和读模式。在写模式下调用flip()之后，Buffer从写模式变成读模式。
            buf.flip();
            //如果还有未读内容
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            //清空缓存区
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        //关闭RandomAccessFile（随机访问文件）对象
        raf.close();
        inChannel.close();
    }

}
