package Internet.UDP;

public class User_ZhangSan {
    public static void main(String[] args) {
        new Thread(new UDPSend("127.0.0.1",6789)).start();
        new Thread(new UDPReceive(5678,"张三")).start();
    }
}
