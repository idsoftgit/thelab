package id.thelab.event;

import id.thelab.entity.Entity;
import id.thelab.event.EventService.EVENT_TYPE;

public class CollisionEvent extends BasicEvent implements Event {
	private Entity firstEntity;
	private Entity secondEntity;

	public CollisionEvent(Entity entity1, Entity entity2) {
		eventType = EVENT_TYPE.COLLISION;
		this.firstEntity = entity1;
		this.secondEntity = entity2;
	}

	public Entity getFirstEntity() {
		return firstEntity;
	}

	public Entity getSecondEntity() {
		return secondEntity;
	}

}
