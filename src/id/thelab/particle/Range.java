package id.thelab.particle;

public class Range {
	private float min, max;

	public Range(float min, float max) {
		this.min = min;
		this.max = max;
	}

	public Range(float val) {
		this.min = val;
		this.max = val;
	}

	public float getRandom() {
		float r;
		if (min == max) {
			r = min;
		} else {
			r = (float) (min - (Math.random() * (max - min)));
		}
		return r;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}
}
