package extras.MultiThread.DesignPattern.MasterWorkerPattern;



class MyWorker extends Worker{


    @Override
    Object handle(Task input) {
        Object output = null;
        try {
            output = input.getPrice();
            Thread.sleep(500);
        } catch (Exception e){
            e.printStackTrace();
        }
        return output;
    }
}
