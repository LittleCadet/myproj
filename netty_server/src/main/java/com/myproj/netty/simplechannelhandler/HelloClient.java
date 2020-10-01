package com.myproj.netty.simplechannelhandler;

/**
 * @author shenxie
 * @date 2020/9/30
 */
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class HelloClient {

    public static void main(String args[]) throws InterruptedException {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        HelloClientHandler helloClientHandlerV1 = new HelloClientHandler();
        HelloServerHandlerV2 helloServerHandlerV2 = new HelloServerHandlerV2();


        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(/*helloClientHandlerV1 ,*/ helloServerHandlerV2);
            }
        });
        // 连接到本地的8000端口的客户端
        bootstrap.connect(new InetSocketAddress(
                "127.0.0.1", 8000));

        //睡眠的目的： 让netty先与服务端建立连接 ， 才能发送消息给服务端。否则报错
        Thread.sleep(500L);
        try {
            helloServerHandlerV2.sendMsg();
        } catch (Exception e) {
            System.out.println("拦截到异常：" );
        }
        System.out.println("客户端 启动了");
    }

    private static class HelloClientHandler extends SimpleChannelHandler {


        /**
         * 当绑定到服务端 后 触发 【不需要绑定成功】，打印"Hello world, I'm client."
         *
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx,
                                     ChannelStateEvent e) {
            System.out.println("Hello world, I'm client.");
        }
    }


    private static class HelloServerHandlerV2 extends SimpleChannelHandler {

        private Channel channel;

        @Override
        public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            final Channel channel = e.getChannel();

            System.out.println("channel:" + channel);

            this.channel = channel;
        }

        /**
         * 想想三次握手
         * @param ctx
         * @param e
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("客户端 发送的连接请求");
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            System.out.println("客户端 收到信息 ");
        }

        /**
         * 先是 断开连接 -> 【当TCP包发送完后，】关闭通道   【想想四次挥手】
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("服务端 断开连接");
        }

        // ChannelClose event may also happen when the other party close socket
        // first and Disconnected occurs
        // Should consider that.
        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("服务端 通道关闭");
        }


        void sendMsg() throws Exception {
            System.out.println("客户端 开始发送消息");
            ChannelFutureListener listener = new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {

                    System.out.println(future.isSuccess()? "客户端 请求成功"  : "客户端请求失败");
                }
            };

            Msg msg = new Msg();
            msg.setBody("这是一条来自客户端的信息");


            //TODO NETTY发送消息，可能牵涉到加解密
            ChannelBuffer buffer = ChannelBuffers.buffer(8);
            buffer.writeShort(1);
            //buffer.writeInt(0);
            buffer.writeInt(-1);


            ChannelFuture future = channel.write(buffer);
            listener.operationComplete(future);
            System.out.println("客户单 已经发送消息");
        }
    }
}
