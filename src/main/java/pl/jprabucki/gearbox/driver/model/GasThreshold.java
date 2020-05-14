package pl.jprabucki.gearbox.driver.model;

public class GasThreshold implements Comparable<GasThreshold> {
	private double threshold;

	public GasThreshold(double threshold) {
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
		GasThreshold other = (GasThreshold) obj;
		if (Double.doubleToLongBits(this.threshold) != Double.doubleToLongBits(other.threshold))
			return false;
		return true;
	}

	@Override
	public int compareTo(GasThreshold gasThreshold) {
		return Double.compare(this.threshold, gasThreshold.threshold);
	}

	public boolean isZero() {
		return Double.compare(this.threshold, 0.0d) == 0;
	}
}
