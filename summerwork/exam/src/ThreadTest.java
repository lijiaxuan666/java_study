import sun.plugin2.applet.Applet2ClassLoader;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    ReentrantLock lock=new ReentrantLock();
    Condition condition1=lock.newCondition();
    Condition condition2=lock.newCondition();
    int i=0;

    public static void main(String[] args) {
        ThreadTest threadTest=new ThreadTest();
        Thread t1=new Thread(threadTest.new add());
        t1.setName("A");
        Thread t2=new Thread(threadTest.new add2());
        t2.setName("B");
        t1.start();
        t2.start();
    }

    class add implements Runnable{
        @Override
        public void run() {
            while(i<100){
                lock.lock();
                try {
                    i++;
                    System.out.println(Thread.currentThread().getName()+"__"+i);
                    condition2.signal();
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
    class add2 implements Runnable{
        @Override
        public void run() {
            while(i<100){
                lock.lock();
                try {
                    i++;
                    System.out.println(Thread.currentThread().getName()+"__"+i);
                    condition1.signal();
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        }
    }

}
