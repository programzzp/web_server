package com.program;

import com.program.message.RequestMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {

    RequestMessage message=new RequestMessage();
    private Selector selector;

    public void init() throws IOException {
        this.selector=Selector.open();
        //创建ServerSocketChannel
        ServerSocketChannel channel=ServerSocketChannel.open();
        //设置非阻塞
        channel.configureBlocking(false);

        ServerSocket serverSocket=channel.socket();

        InetSocketAddress address=new InetSocketAddress(8080);
        System.out.println("------------port--------8080------------------------");
        serverSocket.bind(address);

        channel.register(this.selector, SelectionKey.OP_ACCEPT);//注册监听事件
    }

    public void start() throws IOException {
        while(true){
            //此方法会阻塞，直到至少有一个已注册的事件发生
            this.selector.select();
            //获取发生事件集合对象
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                final SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();
                //如果为注册
                if (key.isAcceptable()){
                    accept(key);
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    public void accept(SelectionKey key) throws IOException {
        ServerSocketChannel server= (ServerSocketChannel) key.channel();
        //接收链接
        SocketChannel accept = server.accept();
        //设置为非阻塞
        accept.configureBlocking(false);
        //创建读取缓冲区
        accept.register(this.selector,SelectionKey.OP_READ);
    }

    public void read(SelectionKey key) throws IOException {
        SocketChannel channel =(SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 读取数据
        int read = channel.read(buffer);
        if (read>0){
            String request = new String(buffer.array()).trim();
            SocketChannel channel1 = message.request_init(request, buffer, channel);
            channel1.close();
        }else{
            System.out.println("客户端关闭");
            key.cancel();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("-----------------------start---------------------------------------");
        Server server=new Server();
        server.init();
        server.start();
    }
}
