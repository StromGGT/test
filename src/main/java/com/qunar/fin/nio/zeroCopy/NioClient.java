package com.qunar.fin.nio.zeroCopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/6 18:38
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 6666));

        FileChannel fileChannel = new FileInputStream("protoc-3.6.1-win32.zip").getChannel();

        long startTime = System.currentTimeMillis();

        fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数：" + fileChannel.size() + ", 总耗时：" + (System.currentTimeMillis()-startTime));
        fileChannel.close();

    }
}
