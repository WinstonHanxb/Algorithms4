package extras.MultiThread.DesignPattern.FuturePattern;

import java.util.Random;

public class RealData implements Data {
    private String result;
    @Override
    public String getRequest() {
        return result;
    }

    public RealData(String request) {
        Random r = new Random();
        System.out.println("根据" + request + "进行查询，这项工作可能非常耗时");
        try{
            Thread.sleep(r.nextInt(500));
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("查询完毕，获取到了结果");
        result = "查询结果";
    }
}
