package org.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * RedisChannelServer
 *
 * @author IIIDelay
 * @createTime 2023年03月20日 16:30:00
 */
public class RedisChannelServer {
    private static ArrayList<SocketChannel> socketList = new ArrayList<>();
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) throws IOException {
        System.out.println("---------RedisServerNIO 启动等待中......");
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 6379));
        serverSocket.configureBlocking(false);// 设置为非阻塞模式

        while (true) {
            for (SocketChannel element : socketList) {
                int read = element.read(byteBuffer);
                if (read > 0) {
                    System.out.println("-----读取数据: " + read);
                    byteBuffer.flip();
                    byte[] bytes = new byte[read];
                    byteBuffer.get(bytes);
                    System.out.println(new String(bytes));
                    byteBuffer.clear();
                }
            }

            SocketChannel socketChannel = serverSocket.accept();
            if (socketChannel != null) {
                System.out.println("-----成功连接: ");
                socketChannel.configureBlocking(false);//设置为非阻塞模式
                socketList.add(socketChannel);
                System.out.println("-----socketList size: " + socketList.size());
            }
        }
    }
}
