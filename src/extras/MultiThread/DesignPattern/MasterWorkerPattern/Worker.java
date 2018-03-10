package extras.MultiThread.DesignPattern.MasterWorkerPattern;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
class Worker implements Runnable{
    private ConcurrentLinkedQueue<Task> workQueue;
    private ConcurrentHashMap<String, Object> resultMap;
    private Worker worker;

    public void setworker(Worker myself) {
        this.worker = myself;
    }

    public void setWorkQueue(ConcurrentLinkedQueue<Task> workQueue) {
        this.workQueue = workQueue;
    }

    public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while(!this.workQueue.isEmpty()){
            Task input = this.workQueue.poll();
            Object output = this.handle(input);
            this.resultMap.put(Integer.toString(input.getId()),output);
        }
    }

    Object handle(Task input) {
        return null;
    }

 }
