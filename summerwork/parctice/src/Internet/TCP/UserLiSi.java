package Internet.TCP;

import java.io.IOException;
import java.net.Socket;

public class UserLiSi {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8888);
        /**
         * 用户应该能同时收发消息,所以需要用到多线程
         * Sender为发送者线程
         * Receiver为接收者线程
         */
        new Thread(new TCPSender(client,"李四")).start();
        new Thread(new TCPReceiver(client)).start();
    }
}
