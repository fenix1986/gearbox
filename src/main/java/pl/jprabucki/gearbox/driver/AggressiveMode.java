package pl.jprabucki.gearbox.driver;

public enum AggressiveMode {
	Mode_1(1.0d), Mode_2(1.2d), Mode_3(1.3d);

	double multiplier;

	AggressiveMode(double d) {
		this.multiplier = d;
	}

	public double getMultiplier() {
		return this.multiplier;
	}
}
