package com.netty.examples;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @description:
 * @author: wuwk
 * @create: 2021-11-19 13:37
 **/
public class NettySyncServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端已经连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String serverMsg0= byteBuf.toString(CharsetUtil.UTF_8);
        String serverMsg = serverMsg0+"！";
        System.out.println("服务端接收：" + byteBuf.toString(CharsetUtil.UTF_8));

        byte[] bytes = serverMsg.getBytes("UTF-8");
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端已经启动");
    }

}
