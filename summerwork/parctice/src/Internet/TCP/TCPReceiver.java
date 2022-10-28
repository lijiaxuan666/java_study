package Internet.TCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPReceiver implements Runnable{
    private Socket client;
    private DataInputStream dis = null;

    //初始化流,用来接收服务器广播的消息
    public TCPReceiver(Socket client){
        this.client = client;
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            System.out.println("初始化失败");
            release();
        }
    }
    @Override
    public void run() {
        while (true){
            try {
                //当输入exit时,此操作异常,run方法终止
                System.out.println(dis.readUTF());
            } catch (IOException e) {
                System.out.println("与服务器断开连接");
                break;
            }
        }
        release();
    }

    //释放资源
    private void release(){
        TCPUtils.close(client,dis);
    }

}
