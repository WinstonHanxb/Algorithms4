package extras.ReactorPattern;

public class Server {
    SynchronousEventDemux synchronousEventDemux = new SynchronousEventDemux();
    InitiationDispatcher eventLooper = new InitiationDispatcher(synchronousEventDemux);
    EventFactory eventFactory;

    Server(int port) {
        eventFactory = new EventFactory(synchronousEventDemux, port);
    }

    public void start() {
        eventLooper.registerHandler(EventType.ACCEPT, new AcceptEventHandler(synchronousEventDemux));
        eventLooper.registerHandler(EventType.WRITE, new AcceptEventHandler(synchronousEventDemux));
        eventLooper.registerHandler(EventType.READ, new AcceptEventHandler(synchronousEventDemux));
        new Thread(eventFactory, "EventFactory-" + eventFactory.getPort()).start();
        eventLooper.handle_events();
    }

    public static void main(String[] args) {
        Server server = new Server(1080);
        server.eventFactory.addNewConnection(new EventSource(new Object(),123L));
        server.start();

    }
}

