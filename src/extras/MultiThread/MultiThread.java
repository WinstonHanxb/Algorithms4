package extras.MultiThread;

public class MultiThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
    public static void main(String[] args) {
        MultiThread tt = new MultiThread();
        Thread t1 = new Thread(tt,"t1");
        Thread t2 = new Thread(tt,"t2");
        Thread t3 = new Thread(tt,"t3");
        Thread t4 = new Thread(tt,"t4");
        Thread t5 = new Thread(tt,"t5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
}
