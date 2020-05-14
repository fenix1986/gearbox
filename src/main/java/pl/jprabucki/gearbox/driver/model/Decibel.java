package pl.jprabucki.gearbox.driver.model;

import java.beans.Transient;

public class Decibel {
	int decibel;

	public Decibel(int decibel) {
		super();

		if (decibel < 0) {
			throw new IllegalArgumentException();
		}

		this.decibel = decibel;
	}

	@Transient
	public int toIntValue() {
		return this.decibel;
	}
}
