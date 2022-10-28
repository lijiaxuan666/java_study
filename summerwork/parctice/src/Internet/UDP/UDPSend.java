package Internet.UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSend implements Runnable{
    //创建一个socket
    DatagramSocket socket=null;
    //创建一个流用于读入键盘输入数据
    BufferedReader br=null;

    private String toIP;//要发送到的IP
    private int toPort;//要发送到的端口号

    public UDPSend(String toIP, int toPort) {
        this.toIP = toIP;
        this.toPort=toPort;
        try {
            socket=new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        br=new BufferedReader(new InputStreamReader(System.in));//从键盘把数据录入流中
    }

    @Override
    public void run() {
        while(true){//循环发送数据
            try {
                String message=br.readLine();//从流中读取数据
                byte[] bytes =message.getBytes();//把消息转化为字节数组
                InetAddress inet=InetAddress.getByName(toIP);
                DatagramPacket packet=new DatagramPacket(bytes,bytes.length,inet,toPort);
                socket.send(packet);
                if(message.equals("quit"))//如果输入quit表示停止发送
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(socket!=null){
            socket.close();
        }
        if(br!=null){
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
