package com.qunar.fin.nio.zeroCopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/6 18:28
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.socket().bind(new InetSocketAddress(6666));

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

        while (true) {
            SocketChannel channel = socketChannel.accept();

            int count = 0;
            while (count != -1) {
                count = channel.read(byteBuffer);
                byteBuffer.rewind();
            }
        }

    }
}
