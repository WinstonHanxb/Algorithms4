package extras.MultiThread.DesignPattern.FuturePattern;

public class FutureData implements Data {

    private RealData realData;
    private boolean isDone = false;
    @Override
    public synchronized String getRequest() {
        while(!isDone){
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return this.realData.getRequest();
    }

    public synchronized void setRealData(RealData realData){
        if(isDone) return;
        this.realData = realData;
        isDone = true;
        notify();
    }
}
