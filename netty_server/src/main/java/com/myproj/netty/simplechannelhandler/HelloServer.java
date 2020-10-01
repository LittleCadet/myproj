package com.myproj.netty.simplechannelhandler;

/**
 * @author shenxie
 * @date 2020/9/30
 */
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import lombok.SneakyThrows;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class HelloServer {
    public static void main(String args[]) {
        // Server服务启动器
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        HelloServerHandler helloServerHandlerV1 = new HelloServerHandler();
        HelloServerHandlerV2 helloServerHandlerV2 = new HelloServerHandlerV2();
        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
                    @Override
                    public ChannelPipeline getPipeline()
                            throws Exception {
                        return Channels
                                .pipeline(/*helloServerHandlerV1  ,*/ helloServerHandlerV2);
                    }
                });
        // 开放8000端口供客户端访问。
        bootstrap.bind(new InetSocketAddress(8000));
        System.out.println("服务端启动了");
    }

    private static class HelloServerHandler extends SimpleChannelHandler {

        /**
         * 当有客户端 需要 绑定到服务端的时候触发，打印"Hello world, I'm server."
         */
        @SneakyThrows
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            /*if(1 ==1 ){
                throw new Throwable();
            }*/

            System.out.println("Hello world, I'm server.");

        }
    }


    private static class HelloServerHandlerV2 extends SimpleChannelHandler {

        /**
         * 【想想三次握手】
         * @param ctx
         * @param e
         */
        @Override
        public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
            System.out.println("服务端收到客户端的连接请求");
        }

        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
            System.out.println("服务端 收到信息 ");
        }


        /**
         * 先是 断开连接 -> 【当TCP包发送完后，】 关闭通道 【想想四次挥手】
         * @param ctx
         * @param e
         * @throws Exception
         */
        @Override
        public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("客户端 断开连接");
        }

        // ChannelClose event may also happen when the other party close socket
        // first and Disconnected occurs
        // Should consider that.
        @Override
        public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
            System.out.println("客户端 通道关闭");
        }
    }
}
