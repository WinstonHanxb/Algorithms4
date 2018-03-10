package extras.ReactorPattern;

public abstract class EventHandler {
    public abstract void handle_event(Event event);
    private EventSource eventSource;

    public EventSource getEventSource() {
        return eventSource;
    }

    public void setEventSource(EventSource eventSource) {
        this.eventSource = eventSource;
    }
}
