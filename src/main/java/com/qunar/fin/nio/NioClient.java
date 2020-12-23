package com.qunar.fin.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/5 23:01
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        if (!socketChannel.connect(address)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("client 连接需要时间，可以做其他工作");
            }
        }

        String str = "hello world!";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(buffer);
        System.in.read();
    }
}
