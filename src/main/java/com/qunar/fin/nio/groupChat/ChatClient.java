package com.qunar.fin.nio.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/6 12:47
 */
public class ChatClient {

    private SocketChannel socketChannel;

    private Selector selector;

//    private static ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(1024);

    private static final String ADD = "127.0.0.1";

    private static final Integer PORT = 6666;

    public ChatClient() throws IOException {
        this.socketChannel = SocketChannel.open(new InetSocketAddress(ADD, PORT));
        this.socketChannel.configureBlocking(false);
        this.selector = Selector.open();
        this.socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(socketChannel.getLocalAddress().toString().substring(1) + " is ok...");
    }

    private void send(String msg) throws IOException {
//        socketChannel.write(BYTE_BUFFER.put(msg.getBytes()));
        socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

    private void read() throws IOException {
        int selectCount = selector.select();
        if (selectCount > 0) {
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext())  {
                SelectionKey key = keyIterator.next();
                if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    channel.read(byteBuffer);
                    System.out.println("from server: " + new String(byteBuffer.array()));
                }
                keyIterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatClient chatClient = new ChatClient();
            ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
            executor.execute(() -> {
                while (true) {
                    try {
                        chatClient.read();
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });


            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                chatClient.send(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
