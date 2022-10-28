package Thread;

public class DeadLock2 {
    public static void main(String[] args) {
        new Thread(new DieLock(true)).start();
        new Thread(new DieLock(false)).start();
    }
}

class DieLock implements Runnable{
    private boolean flag;
    public static Object obj1=new Object();
    public static Object obj2=new Object();

    DieLock(boolean flag){
        this.flag=flag;
    }
    public void run() {
        if(flag) {
            while(true) {
                synchronized(obj1) {
                    System.out.println(Thread.currentThread().getName()+"获取了1号锁对象");
                    System.out.println(Thread.currentThread().getName()+"正在请求2号锁对象.......");
                    synchronized(obj2) {
                        System.out.println(Thread.currentThread().getName()+"成功获取了2号锁对象");

                    }
                }
            }
        }
        else {
            while(true){
                synchronized(obj2) {
                    System.out.println(Thread.currentThread().getName()+"获取了2号锁对象");
                    System.out.println(Thread.currentThread().getName()+"正在请求1号锁对象.......");
                    synchronized(obj1) {
                        System.out.println(Thread.currentThread().getName()+"成功获取了1号锁对象");

                    }
                }
            }
        }

    }
}
