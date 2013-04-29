package id.thelab.event;

import id.thelab.event.EventService.EVENT_TYPE;

public class BasicEvent implements Event {
	protected EVENT_TYPE eventType;

	@Override
	public EVENT_TYPE getEventType() {
		return eventType;
	}

}
