package extras.MultiThread.DesignPattern.MasterWorkerPattern;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Future;

class Main {
    public static void main(String[] args) {
        Master master = new Master(new MyWorker(),Runtime.getRuntime().availableProcessors());
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            Task t = new Task();
            t.setId(i);
            t.setName("任务"+i);
            t.setPrice(r.nextInt(100));
            master.submit(t);
        }
        master.execute();
        long startTime=System.currentTimeMillis();
        while(true){
            if(master.isComplete()){
                int res = master.getResult();
                long endTime=System.currentTimeMillis(); //获取结束时间
                System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
                System.out.println("计算结果" + res);
                break;
            }
        }

    }
}
