package id.thelab.resource;

public abstract class Resource {
	private final String type;
	protected Object resource;

	public String getType() {
		return type;
	}

	public Object getResourceData() {
		return resource;
	}

	public Resource(final String type) {
		this.type = type;
	}
}
