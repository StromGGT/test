package com.qunar.fin.nio.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 监听客户端事件
 * 读取客户端消息
 * 转发客户端消息给其他客户端
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/6 12:02
 */
public class ChatServer {

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

//    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private static final Integer PORT = 6666;

    public ChatServer() throws IOException {
        this.serverSocketChannel = ServerSocketChannel.open();
        this.serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        this.serverSocketChannel.configureBlocking(false);
        this.selector = Selector.open();
        this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    private void listen() throws Exception {
        System.out.println("监听线程: " + Thread.currentThread().getName());
        while (true) {
            int selectCount = selector.select();
            if (selectCount <= 0) {
                System.out.println("等待事件发送....");
                continue;
            }

            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    accept();
                } else if (selectionKey.isReadable()) {
                    read(selectionKey);
                }
                keyIterator.remove();
            }
        }
    }

    private void accept() throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(socketChannel.getRemoteAddress().toString().substring(1) + " is connected...");
    }

    private void read(SelectionKey selectionKey) throws IOException {
        SocketChannel channel = (SocketChannel) selectionKey.channel();
        try {
//            byteBuffer.flip();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count <= 0) {
                return;
            }
            String msg = new String(buffer.array());
            System.out.println(channel.getRemoteAddress().toString().substring(1) + " 发送消息：" + msg);
            //向其他客户端转发消息
            sendMsgToOthers(channel, msg);
        } catch (IOException e) {
            System.out.println(channel.getRemoteAddress() + " 客户端离线了...");
            selectionKey.cancel();
            channel.close();
        }
    }

    private void sendMsgToOthers(SocketChannel channel, String msg) throws IOException {
        for (SelectionKey selectionKey : selector.keys()) {
            SelectableChannel selectableChannel = selectionKey.channel();
            if (selectableChannel instanceof SocketChannel && !channel.equals(selectableChannel)) {
                ((SocketChannel) selectableChannel).write(ByteBuffer.wrap(msg.getBytes()));
                System.out.println("send msg to " + ((SocketChannel) selectableChannel).getRemoteAddress() + ": " + msg);
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatServer chatServer = new ChatServer();
            chatServer.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
