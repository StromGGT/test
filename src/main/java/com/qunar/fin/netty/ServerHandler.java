package com.qunar.fin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/9/17 14:24
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server is active...");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

//        ctx.channel().eventLoop().execute(() -> {
//            try {
//                Thread.sleep(10000);
//                ByteBuf in = (ByteBuf) msg;
//                System.out.print("msg from client: " + in.toString(CharsetUtil.UTF_8));
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client...", CharsetUtil.UTF_8));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });



//        ctx.executor().execute(() -> {
//            try {
//                Thread.sleep(10000);
//                ByteBuf in = (ByteBuf) msg;
//                System.out.print("msg from client: " + in.toString(CharsetUtil.UTF_8));
//                ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client...", CharsetUtil.UTF_8));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        ctx.channel().eventLoop().schedule(() -> {
            ByteBuf in = (ByteBuf) msg;
            System.out.print("msg from client: " + in.toString(CharsetUtil.UTF_8));
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, client...", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);

        System.out.println("channel read...");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel read complete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
