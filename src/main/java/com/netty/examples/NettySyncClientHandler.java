package com.netty.examples;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @description:
 * @author: wuwk
 * @create: 2021-11-19 13:37
 **/
public class NettySyncClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        String serverMsg0= byteBuf.toString(CharsetUtil.UTF_8);
        String serverMsg = "客户端:" +serverMsg0;
        System.out.println("客户端接收：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}
