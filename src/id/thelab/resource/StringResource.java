package id.thelab.resource;


public class StringResource extends Resource {
	public StringResource(final String str) {
		super("string");
		resource = new String(str);
	}
}
