package pl.jprabucki.gearbox.driver.model;

public class BreakThreshold {
	private double threshold;

	public BreakThreshold(double threshold) {
		super();

		if (threshold < 0.0d || threshold > 1.0d) {
			throw new IllegalArgumentException();
		}

		this.threshold = threshold;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.threshold);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		BreakThreshold other = (BreakThreshold) obj;
		if (Double.doubleToLongBits(this.threshold) != Double.doubleToLongBits(other.threshold))
			return false;
		return true;
	}
}
