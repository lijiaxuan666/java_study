package Internet.TCP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPUtils {

    public static void close(Socket client, DataInputStream dis) {
        close(client,dis,null);
    }

    public static void close(Socket client, DataOutputStream dos, BufferedReader reader) {
        close(client,null,dos);
        if(reader!=null){
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Socket client,DataInputStream dis,DataOutputStream dos) {
        if(client!=null){
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(dis!=null){
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(dos!=null){
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
