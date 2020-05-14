package pl.jprabucki.gearbox.driver.facade.gearbox.changegear;

import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Gear;

class ReverseChangeGearPolicy implements ChangeGearPolicy {

	@SuppressWarnings("unused")
	private GearboxFacade gearboxFacade;

	ReverseChangeGearPolicy(GearboxFacade gearboxFacade) {
		super();
		this.gearboxFacade = gearboxFacade;
	}

	@Override
	public boolean isGearValid(Gear gear) {
		if (gear == null) {
			return false;
		}

		if (! gear.isBackward()) {
			return false;
		}

		// Dopuszczalny tylko bieg -1
		if (new Gear(-1).compareTo(gear) != 0) {
			return false;
		}

		return true;
	}
}
