package Internet.TCP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TCPServer {
    //用该容器存储与用户建立的连接
    static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();
    List<String> list = new ArrayList();
    //以下两个常量用来区分 系统和用户
    private final static boolean system = true;
    private final static boolean user = false;

    public static void main(String[] args) throws IOException {
        //创建服务端
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务端建立完毕,端口号:" + server.getLocalPort());
        //准备连接
        Channel c;
        //接受客户端
        while (true){
            //监听到连接后,为该客户端开辟一个线程
            Socket client = server.accept();
            c = new Channel(client);
            new Thread(c).start();
            //加入到容器中
            all.add(c);
            System.out.println("玩家:"+c.name+ " 已连接上...");
        }
    }
    //通信管道,一个用户对应一个管道(内部类),封装了流和用户信息
    static class Channel implements Runnable{
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning;

        private String name;

        //此构造方法用来初始化流和用户昵称
        public Channel(Socket client){
            this.client = client;
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());
                //默认处于连接状态,用于收发信息
                isRunning = true;
                //初始化姓名
                name = dis.readUTF();
                //+1为包括自身
                this.send("欢迎进入聊天室，当前在线玩家数: "+(all.size()+1)+"\r\n"+
                        "指令:ls(查询当前在线用户),@XX:*****(向玩家XX发送消息***),exit(退出聊天室)");
            }catch (IOException e) {
                System.out.println("初始化失败");
                //释放资源
                release();
            }
        }
        //run方法循环转发用户消息
        @Override
        public void run() {
            while (isRunning){
                String msg = receive(client);
                if(!msg.equals("")){
                    if (msg.equals("exit")){//断开连接
                        System.out.println("玩家:"+this.name+" 已断开连接");
                        sendOthers(name+"退出了群聊",system);//告诉所有人 此人离开了
                        release();
                        break;
                    }else if (msg.startsWith("@")){//私聊
                        int index = msg.indexOf(":");
                        if (index!= -1){
                            String targetName = msg.substring(1,index);
                            sendTarget(msg,targetName);
                        }else {
                            sendTarget(msg,"error");
                        }
                    }else if (msg.equals("ls")){//查询在线玩家
                        StringBuilder sb = searchOnline();
                        if (!sb.equals("]")) {
                            this.send("当前在线的玩家:"+sb.toString());
                        }
                    }
                    else {
                        sendOthers(name+"说:"+msg,user);//向所有人广播此消息,除了他自己
                    }
                }
            }
        }

        //接受信息
        public String receive(Socket client) {
            String msg = "";
            try {
                //阻塞,只有用户发送了信息,服务器才会接受并广播给其他用户
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("玩家:"+this.name+" 异常中断");
                //非输入exit退出,同样需要广播此用户的退出
                sendOthers(name+"退出了群聊",system);//告诉所有人 此人离开了
                //客户端强制退出时,释放资源
                release();
            }
            return msg;
        }

        //广播的最底层操作是调用Channel中的send方法
        public void send(String msg){
            try {
                dos.writeUTF(msg);
                dos.flush();
            }catch (IOException e){
                System.out.println("信息传出异常,连接已断开");
                release();
            }
        }

        //广播,调用时确认是系统还是用户
        public void sendOthers(String msg,boolean who){
            if (msg!=""){
                for (Channel c:all){
                    //任何消息不回反馈给发送者本身
                    if (c!=this) {
                        if (who == system) {
                            c.send("***系统消息: " + msg + "***");
                        } else {//用户
                            c.send(msg);
                        }
                    }
                }
            }
        }

        //私聊
        public void sendTarget(String msg,String name){
            boolean flag = true;
            for (Channel c:all){
                if (c.name.equals(name)){
                    c.send(this.name+"悄悄地对你说:"+msg.substring(msg.indexOf(":")+1));
                    flag = false;
                    break;
                }
            }
            if (flag){
                System.out.println(flag);
                if (name.equals("error")){
                    this.send("指令错误,请输入英文状态下的冒号");
                }else {
                    this.send("***找不到该用户***");
                }
            }
        }

        //查询其他在线的玩家
        public StringBuilder searchOnline(){
            StringBuilder sb = new StringBuilder("[");
            for (Channel c:all){
                sb.append(c.name+",");
            }
            sb.setCharAt(sb.length()-1,']');
            return sb;
        }

        //释放资源时从容器中剔除自身的引用(此Channel因各种原因断开),并关闭转发器(停止run方法中的while循环)
        private void release(){
            this.isRunning = false;
            all.remove(this);
            System.out.println("剩余人数"+all.size());
            TCPUtils.close(client,dis,dos);
        }
    }
}

