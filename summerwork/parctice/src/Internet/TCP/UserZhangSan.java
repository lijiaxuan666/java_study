package Internet.TCP;

import java.io.*;
import java.net.Socket;

public class UserZhangSan {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);
        /**
         * 用户应该能同时收发消息,所以需要用到多线程
         * Sender为发送者线程
         * Receiver为接收者线程
         */
        new Thread(new TCPSender(client,"张三")).start();
        new Thread(new TCPReceiver(client)).start();
    }
}

