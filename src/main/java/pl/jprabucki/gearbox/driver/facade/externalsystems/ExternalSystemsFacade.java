package pl.jprabucki.gearbox.driver.facade.externalsystems;

import pl.jprabucki.gearbox.ExternalSystems;
import pl.jprabucki.gearbox.Ligths;
import pl.jprabucki.gearbox.SoundModule;
import pl.jprabucki.gearbox.driver.model.AngularSpeed;
import pl.jprabucki.gearbox.driver.model.BreakThreshold;
import pl.jprabucki.gearbox.driver.model.Decibel;
import pl.jprabucki.gearbox.driver.model.DriveDirection;
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Rpm;

public class ExternalSystemsFacade {
	private ExternalSystems externalSystems;
	private SoundModule soundModule;

	public ExternalSystemsFacade(ExternalSystems externalSystems, SoundModule soundModule) {
		super();
		this.externalSystems = externalSystems;
		this.soundModule = soundModule;
	}

	public Rpm getCurrentRpm() {
		return new Rpm(this.externalSystems.getCurrentRpm());
	}

	public GasThreshold getCurrentGasThreshold() {
		return new GasThreshold(this.externalSystems.getCurrentGasThreshold());
	}

	public BreakThreshold getCurrentBreakThreshold() {
		return new BreakThreshold(this.externalSystems.getCurrentBreakThreshold());
	}

	public void makeNoise(Decibel decibel) {
		this.soundModule.makeSound(decibel.toIntValue());
	}

	public AngularSpeed getAngularSpeed() {
		return new AngularSpeed(this.externalSystems.getAngularSpeed());
	}

	public boolean isTrailerAttached() {
		return true;
	}

	public DriveDirection getDriveDirection() {
		Ligths lights = this.externalSystems.getLights();

		if (lights != null) {
			Integer lightsPosition = lights.getLightsPosition();

			if (lightsPosition != null) {
				if (lightsPosition >= 1 && lightsPosition <= 3) {
					return DriveDirection.Down;
				}

				if (lightsPosition >= 7 && lightsPosition <= 10) {
					return DriveDirection.Up;
				}
			}
		}

		return DriveDirection.Horizontal;
	}

}
