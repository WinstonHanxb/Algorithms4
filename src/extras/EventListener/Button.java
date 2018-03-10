package extras.EventListener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button {
    private MouseListener mouseListener;
    public void addMouseListener(MouseListener l) {
        mouseListener = l;
    }
    public void doClick(){
/*
* 这里的new Component() {} 就是 event.getEventSource() 得到的事件源 source
*/
        MouseEvent event = new MouseEvent(new Component() {}, 1, 1, 1,2,3,4,false);
//event.getEventSource();
        this.mouseListener.mouseClicked(event);
    }
}
