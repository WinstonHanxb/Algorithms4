package extras.ReactorPattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventFactory implements Runnable{
    private int port; // server socket port
    private SynchronousEventDemux synchronousEventDemux;

    // 代表 serversocket，通过LinkedBlockingQueue来模拟外部输入请求队列
    private BlockingQueue<EventSource> eventSourceQueue = new LinkedBlockingQueue<EventSource>();

    EventFactory(SynchronousEventDemux synchronousEventDemux, int port) {
        this.synchronousEventDemux = synchronousEventDemux;
        this.port = port;
    }

    //外部有输入请求后，需要加入到请求队列中
    public void addNewConnection(EventSource eventSource) {
        eventSourceQueue.offer(eventSource);
    }

    public int getPort() {
        return this.port;
    }

    public void run() {
        while (true) {
            EventSource eventSource = null;
            try {
                // 相当于 serversocket.accept()，接收输入请求，该例从请求队列中获取输入请求
                eventSource = eventSourceQueue.take();
            } catch (InterruptedException e) {
                // ignore it;
            }
            //接收到InputSource后将接收到event设置type为ACCEPT，并将source赋值给event
            if (eventSource != null) {
                Event acceptEvent = new Event();
                acceptEvent.setEventSource(eventSource);
                acceptEvent.setType(EventType.ACCEPT);
                synchronousEventDemux.addEvent(acceptEvent);
            }

        }
    }
}

