package extras.MultiThread.DesignPattern.FuturePattern;

class Client {

    //Client先返回一个假的结果，在内部启用一个私有进程来获取真正的结果
    public Data request(final String request){
        final FutureData futureData = new FutureData();
        //私自启动一个线程来获取需要的数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                RealData realData = new RealData(request);
                futureData.setRealData(realData);
            }
        }).start();
        return futureData;
    }

}
