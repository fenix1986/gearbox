package pl.jprabucki.gearbox.driver.model;

public class AngularSpeed implements Comparable<AngularSpeed> {

	double angularSpeed;

	public AngularSpeed(double angularSpeed) {
		super();

		this.angularSpeed = angularSpeed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(this.angularSpeed);
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
		AngularSpeed other = (AngularSpeed) obj;
		if (Double.doubleToLongBits(this.angularSpeed) != Double.doubleToLongBits(other.angularSpeed))
			return false;
		return true;
	}

	@Override
	public int compareTo(AngularSpeed angularSpeed) {
		return Double.compare(this.angularSpeed, angularSpeed.angularSpeed);
	}

}
