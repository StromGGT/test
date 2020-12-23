package com.qunar.fin.netty.http;

import com.qunar.fin.nio.NioServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.channels.SocketChannel;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/20 19:50
 */
public class HttpServer {

    public static void main(String[] args) {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpInitializer());

            ChannelFuture sync = bootstrap.bind(6666).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            workGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }
    }
}
