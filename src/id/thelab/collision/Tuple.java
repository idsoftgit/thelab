package id.thelab.collision;

public class Tuple<X, Y> {
	protected final X x;
	protected final Y y;

	public Tuple(final X x, final Y y) {
		this.x = x;
		this.y = y;
	}

	public X get1() {
		return x;
	}

	public Y get2() {
		return y;
	}
}
