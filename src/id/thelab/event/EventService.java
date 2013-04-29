package id.thelab.event;

import java.util.ArrayList;
import java.util.List;

public class EventService {
	public static enum EVENT_TYPE {
		IDLE, DEATH, COLLISION
	};

	private List<EventListener> listeners = new ArrayList<EventListener>();

	public void setEventListener(List<EventListener> listeners) {
		this.listeners = listeners;
	}

	public void addEventListener(EventListener listener) {
		listeners.add(listener);
	}

	public void removeEventListener(EventListener listener) {
		listeners.remove(listener);
	}

	public void emitEvent(Event event) {
		for (EventListener listener : listeners) {
			listener.event(event);
		}
	}

}
