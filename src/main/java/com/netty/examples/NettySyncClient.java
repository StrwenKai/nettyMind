package com.netty.examples;


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @description: 基于netty实现的同步客户端
 * @author: wuwk
 * @create: 2021-11-19 11:22
 **/
public class NettySyncClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap=new Bootstrap();
        bootstrap.group(bossGroup)
                 .channel(NioSocketChannel.class)
                  .handler(new ChannelInitializer<NioSocketChannel>() {
                      @Override
                      protected void initChannel(NioSocketChannel localChannel) throws Exception {
                          localChannel.pipeline().addLast(
                                  new NettySyncClientHandler());
                      }
                  });
        Channel ch = bootstrap.connect("127.0.0.1",9000).sync().channel();
        System.out.println("Enter text (quit to end)");
        ChannelFuture lastWriteFuture = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (;;) {
            String line = in.readLine();
            if (line == null || "quit".equalsIgnoreCase(line)) {
                break;
            }

            // Sends the received line to the server.
            byte[] bytes = line.getBytes("UTF-8");
            ByteBuf buf = Unpooled.wrappedBuffer(bytes);

            lastWriteFuture = ch.writeAndFlush(buf);
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }

        }






    }




}
