package Internet.UDP;

public class User_LiSi {
    public static void main(String[] args) {
        new Thread(new UDPSend("127.0.0.1",5678)).start();
        new Thread(new UDPReceive(6789,"李四")).start();
    }
}
