package pl.jprabucki.gearbox;

public class ExternalSystems {
	private double currentRpm;
	private double angularSpeed;
	private Ligths lights = new Ligths();

	ExternalSystems() {

	}

	public double getCurrentRpm() {
		// ściągnij RPM z dostępnego miejsca
		return this.currentRpm;
	}

	public void setCurrentRpm(double currentRpm) {
		this.currentRpm = currentRpm;
	}

	public double getAngularSpeed() {
		return this.angularSpeed;
	}

	public void setAngularSpeed(double angularSpeed) {
		this.angularSpeed = angularSpeed;
	}

	public Ligths getLights() {
		return this.lights;
	}

	// --------------------------------------------------------------------
	// Moje rozszerzenie

	private double currentGasThreshold;
	private double currentBreakThreshold;

	public double getCurrentGasThreshold() {
		return this.currentGasThreshold;
	}

	public double getCurrentBreakThreshold() {
		return this.currentBreakThreshold;
	}
}