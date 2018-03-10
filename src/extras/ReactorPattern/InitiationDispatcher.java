package extras.ReactorPattern;


import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InitiationDispatcher {
    private Map<EventType,EventHandler> handlers = new ConcurrentHashMap<>();
    SynchronousEventDemux synchronousEventDemux;

    public InitiationDispatcher(SynchronousEventDemux synchronousEventDemux) {
        this.synchronousEventDemux = synchronousEventDemux;
    }

    public void handle_events() {
        while(true){
            List<Event> events = synchronousEventDemux.select();
            events.forEach(event -> {
                handlers.get(event.getType()).handle_event(event);
            });
        }
    }

    public void registerHandler(EventType type,EventHandler handler) {
        handlers.put(type,handler);
    }

    public void removeHandler(EventHandler handler) {
        handlers.remove(handler);
    }
}
