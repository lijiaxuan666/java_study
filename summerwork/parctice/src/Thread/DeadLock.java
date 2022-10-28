package Thread;

public class DeadLock {
    private Object obj1 = new Object();
    private Object obj2 = new Object();

    public static void main(String[] args) {
        new DeadLock().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(() -> {
            synchronized (obj1) {
                System.out.println("线程1拿到了1号锁对象");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1正在请求2号锁对象.......");
                synchronized (obj2) {
                    System.out.println("线程1成功拿到2号锁对象");
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj2) {
                System.out.println("线程2拿到了2号锁对象");
                System.out.println("线程2正在请求1号锁对象.......");
                synchronized (obj1) {
                    System.out.println("线程2成功拿到了1号锁对象");
                }
            }
        });
        t1.start();
        t2.start();
    }

}

