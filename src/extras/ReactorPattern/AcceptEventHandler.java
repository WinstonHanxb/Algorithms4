package extras.ReactorPattern;


public class AcceptEventHandler extends EventHandler {
    private SynchronousEventDemux synchronousEventDemux;

    public AcceptEventHandler(SynchronousEventDemux synchronousEventDemux) {
        this.synchronousEventDemux = synchronousEventDemux;
    }

    @Override
    public void handle_event(Event event) {
        switch (event.getType()){
            case ACCEPT:
                System.out.println("Accept!");
                event.setType(EventType.READ);
                synchronousEventDemux.addEvent(event);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case READ:
                System.out.println("Read!");
                event.setType(EventType.WRITE);
                synchronousEventDemux.addEvent(event);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case WRITE:
                System.out.println("Write!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                break;
            default:
                System.out.println("Error");
        }

    }


}
