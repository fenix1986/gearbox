package pl.jprabucki.gearbox.driver.facade.gearbox.changegear;

import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Gear;

class ParkChangeGearPolicy implements ChangeGearPolicy {

	@SuppressWarnings("unused")
	private GearboxFacade gearboxFacade;

	ParkChangeGearPolicy(GearboxFacade gearboxFacade) {
		super();
		this.gearboxFacade = gearboxFacade;
	}

	@Override
	public boolean isGearValid(Gear gear) {
		if (gear == null) {
			return false;
		}

		return gear.isNeutral();
	}
}
