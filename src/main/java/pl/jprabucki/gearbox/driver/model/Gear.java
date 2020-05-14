package pl.jprabucki.gearbox.driver.model;

import java.beans.Transient;
import java.util.Optional;

public class Gear implements Comparable<Gear> {
	public static final Gear NEUTRAL = new Gear(0);
	public static final Gear FIRST = new Gear(1);
	public static final Gear REVERSE = new Gear(-1);

	private int gear;

	public Gear(int gear) {
		super();

		this.gear = gear;
	}

	@Override
	public int compareTo(Gear o) {
		return Integer.compare(this.gear, o.gear);
	}

	public boolean isForward() {
		return this.gear > 0;
	}

	public boolean isBackward() {
		return this.gear < 0;
	}

	public boolean isNeutral() {
		return this.gear == 0;
	}

	public Optional<Gear> getNextGear() {
		if (this.gear >= 1) {
			return Optional.of(new Gear(this.gear + 1));

		} else if (this.gear <= -1) {
			return Optional.of(new Gear(this.gear - 1));
		}

		return Optional.empty();
	}

	public Optional<Gear> getPreviousGear() {
		if (this.gear > 1) {
			return Optional.of(new Gear(this.gear - 1));

		} else if (this.gear < -1) {
			return Optional.of(new Gear(this.gear + 1));
		}

		return Optional.empty();
	}

	@Transient
	public int toIntValue() {
		return this.gear;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.gear;
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
		Gear other = (Gear) obj;
		if (this.gear != other.gear)
			return false;
		return true;
	}
}
