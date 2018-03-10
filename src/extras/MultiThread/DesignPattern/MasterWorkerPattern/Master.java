package extras.MultiThread.DesignPattern.MasterWorkerPattern;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

class Master {

    private ConcurrentLinkedQueue<Task> workQueue = new ConcurrentLinkedQueue<>();

    //key 表示每一个worker的名字， value表示线程执行对象
    private HashMap<String, Thread> workers = new HashMap<>();

    private ConcurrentHashMap<String, Object> resultMap = new ConcurrentHashMap<>();

    public Master(Worker worker,int workerCount){
        worker.setResultMap(resultMap);
        worker.setWorkQueue(workQueue);
        //初始化节点
        for (int i = 0; i < workerCount; i++) {
            workers.put("子节点"+i, new Thread(worker));
        }
    }

    //提交任务队列
    public void submit(Task task){
        this.workQueue.add(task);
    }

    //使用一个方法启动worker执行
    public void execute(){
        for (Map.Entry<String,Thread> me : workers.entrySet()){
            me.getValue().start();
        }
    }

    public boolean isComplete() {
        for (Map.Entry<String,Thread> me : workers.entrySet()){
            if(me.getValue().getState() != Thread.State.TERMINATED){
                return false;
            }
        }
        return true;
    }

    public int getResult() {
        int result = 0;
        for (Map.Entry<String,Object> resultMap : resultMap.entrySet()){
            result += (Integer)resultMap.getValue();
        }
        return result;
    }
}
