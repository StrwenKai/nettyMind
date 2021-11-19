package com.netty.examples;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @description: 基于netty实现的同步客户端
 * @author: wuwk
 * @create: 2021-11-19 11:22
 **/
public class NettySyncServer {
   final static private int port=9000;

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup=new NioEventLoopGroup(1);
        NioEventLoopGroup workGroup=new NioEventLoopGroup(2);

        ServerBootstrap bootstrap=new ServerBootstrap();
        bootstrap.group(bossGroup,workGroup).channel(NioServerSocketChannel.class)
                 .option(ChannelOption.SO_BACKLOG,1024)
                 .childHandler(new ChannelInitializer<SocketChannel>() {
                     protected void initChannel(SocketChannel socketChannel) throws Exception {
                         socketChannel.pipeline().addLast(
                                 new NettySyncServerHandler());
                     }
                 });
        try {
            ChannelFuture cf=bootstrap.bind(port).sync();

            System.out.println("NettySyncServer start...");

        }catch (InterruptedException e){
            e.printStackTrace();
        }




    }






}
