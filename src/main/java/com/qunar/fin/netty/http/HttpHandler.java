package com.qunar.fin.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author guotao.gou
 * @version 1.0
 * @date 2020/12/20 19:51
 */
public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //判断是HttpRequest
        if (msg instanceof HttpRequest) {
            System.out.println("msg 类型： " + msg.getClass());
            System.out.println("msg 地址：" + ctx.channel().remoteAddress());

//            HttpRequest httpRequest = (HttpRequest) msg;
//            URI uri = new URI(httpRequest.getUri());
//            System.out.println(uri.getPath());

            System.out.println("pipeline hashcode: " + ctx.pipeline().hashCode());
            System.out.println("channel hashcode: " + ctx.pipeline().channel().hashCode());

            ByteBuf byteBuf = Unpooled.copiedBuffer("hello, 我是服务器", CharsetUtil.UTF_8);

            //构造response
            DefaultFullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK, byteBuf);
            httpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
            httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            //返回response
            ctx.writeAndFlush(httpResponse);
        }
    }
}
