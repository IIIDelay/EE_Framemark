package org.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * RedisClent01
 *
 * @author IIIDelay
 * @createTime 2023年03月20日 16:50:00
 */
public class RedisClent01 {
    public static void main(String[] args) throws IOException {
        System.out.println("------RedisClient01 start");
        Socket socket = new Socket("127.0.0.1", 6379);
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String string = scanner.next();
            if (string.equalsIgnoreCase("quit")) {
                break;
            }
            socket.getOutputStream().write(string.getBytes());
            System.out.println("------input quit keyword to finish......");
        }
        outputStream.close();
        socket.close();
    }
}
