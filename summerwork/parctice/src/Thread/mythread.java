package Thread;

public class mythread {
    public static void main(String[] args) {
        /*thread run =new thread();
        Thread th1=new Thread(run);
        Thread th2=new Thread(run);
        Thread th3=new Thread(run);

        th1.setName("A");
        th2.setName("B");
        th3.setName("C");

        th1.start();
        th2.start();
        th3.start();*/
        thread run =new thread();
        new Thread(run,"A").start();
        new Thread(run,"B").start();
        new Thread(run,"C").start();
    }
}
class thread implements Runnable{
    int number=1;
    Object obj=new Object();//锁对象
    @Override
    public void run() {
        while(number<101){
            synchronized (obj){
                if(number<101){
                    System.out.println(Thread.currentThread().getName()+":"+number);
                    number++;
                }
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}