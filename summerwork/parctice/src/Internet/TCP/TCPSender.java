package Internet.TCP;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPSender implements Runnable{
    private Socket client=null;
    private DataOutputStream dos=null;
    private BufferedReader reader=null;

    //初始化流,并向服务器传递用户昵称,服务器在初始化链接(Channel)时接收昵称
    public TCPSender(Socket client,String name){
        this.client = client;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));
            dos.writeUTF(name);
        } catch (IOException e) {
            release();
            System.out.println("初始化流失败");
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                String msg = reader.readLine();
                if (msg.equals("exit")){
                    dos.writeUTF(msg);//输入exit主动断开连接
                    dos.flush();
                    break;
                }
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                release();
                System.out.println("发送异常");
            }
        }
        release();
    }
    //释放资源
    private void release(){
        TCPUtils.close(client,dos,reader);
    }
}
