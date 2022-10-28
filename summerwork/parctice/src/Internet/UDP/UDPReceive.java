package Internet.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceive implements Runnable{
    DatagramSocket socket=null;
    private int portFrom;
    private String messageFrom;

    public UDPReceive(int fromPort, String fromMessage) {
        this.portFrom = fromPort;
        this.messageFrom = fromMessage;
        try {
            socket=new DatagramSocket(fromPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                byte[] bytes=new byte[1024];
                DatagramPacket packet=new DatagramPacket(bytes,0,bytes.length);
                socket.receive(packet);
                String Message=new String(packet.getData(),0,packet.getLength());
                System.out.println(messageFrom +":"+Message);
                if(Message.equals("quit"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
