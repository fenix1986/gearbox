package pl.jprabucki.gearbox.driver.facade.gearbox;

import pl.jprabucki.gearbox.Gearbox;
import pl.jprabucki.gearbox.driver.facade.gearbox.changegear.ChangeGearPolicy;
import pl.jprabucki.gearbox.driver.facade.gearbox.changegear.ChangeGearPolicyFactory;
import pl.jprabucki.gearbox.driver.model.Gear;
import pl.jprabucki.gearbox.driver.model.GearboxState;

public class GearboxFacade {
	private Gearbox gearbox;

	public GearboxFacade(Gearbox gearbox) {
		super();
		this.gearbox = gearbox;
	}

	public GearboxState getGearboxState() {
		Integer state = (Integer) this.gearbox.getState();

		switch(state) {
			case 1: return GearboxState.Drive;
			case 2: return GearboxState.Park;
			case 3: return GearboxState.Reverse;
			case 4: return GearboxState.Neutral;
			default: throw new IllegalStateException();
		}
	}

	public Gear getCurrentGear() {
		return new Gear((Integer) this.gearbox.getCurrentGear());
	}

	public Gear getMaxGear() {
		return new Gear(this.gearbox.getMaxDrive());
	}

	public void changeGear(Gear gear) {
		// Nadrzędne zabezpieczenie dopuszczalnego biegu
		ChangeGearPolicy changeGearPolicy = ChangeGearPolicyFactory.createChangeGearPolicy(this);

		if (changeGearPolicy.isGearValid(gear)) {
			this.setGear(gear);
		}
	}


	public void changeGearUp() {
		Gear currentGear = this.getCurrentGear();

		currentGear.getNextGear().ifPresent(gear -> this.changeGear(gear));
	}

	public void changeGearDown() {
		Gear currentGear = this.getCurrentGear();

		currentGear.getPreviousGear().ifPresent(gear -> this.changeGear(gear));
	}

	private void setGear(Gear gear) {
		this.gearbox.setCurrentGear(gear.toIntValue());
	}
}
