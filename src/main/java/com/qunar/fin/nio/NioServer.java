package com.qunar.fin.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Set;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/5 22:43
 */
public class NioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了1秒中。。。");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(selectionKey -> {
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = null;
                    try {
                        socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println("accept a client");
                        System.out.println("key set: " + selector.keys().size());
                        System.out.println("selection Key: " + selector.selectedKeys().size());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (selectionKey.isReadable()) {
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    try {
                        channel.read(buffer);
                        System.out.println("from client:　" + new String(buffer.array()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                selectionKeys.remove(selectionKey);
            });


        }
    }
}
