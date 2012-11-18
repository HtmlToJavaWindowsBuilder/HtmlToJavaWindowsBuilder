package html2windows.dom;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventException;
import org.w3c.dom.events.EventListener;

public interface EventTarget {
    public void addEventListener(String type, EventListener listener, boolean useCapture);
    public void removeEventListener(String type, EventListener listener, boolean useCapture);
    public boolean dispatchEvent(Event evt) throws EventException;
}
