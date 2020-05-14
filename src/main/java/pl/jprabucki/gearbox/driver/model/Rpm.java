package pl.jprabucki.gearbox.driver.model;

public class Rpm implements Comparable<Rpm> {
	private double rpm;

	public Rpm(double rpm) {
		super();

		if (rpm < 0.0d) {
			throw new IllegalArgumentException();
		}

		this.rpm = rpm;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.rpm);
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
		Rpm other = (Rpm) obj;
		if (Double.doubleToLongBits(this.rpm) != Double.doubleToLongBits(other.rpm))
			return false;
		return true;
	}

	@Override
	public int compareTo(Rpm rpm) {
		return Double.compare(this.rpm, rpm.rpm);
	}

	public Rpm multiply(double multiplier) {
		return new Rpm(this.rpm * multiplier);
	}
}
