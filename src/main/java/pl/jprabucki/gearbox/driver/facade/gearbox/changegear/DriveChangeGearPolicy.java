package pl.jprabucki.gearbox.driver.facade.gearbox.changegear;

import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Gear;

class DriveChangeGearPolicy implements ChangeGearPolicy {

	private GearboxFacade gearboxFacade;

	DriveChangeGearPolicy(GearboxFacade gearboxFacade) {
		super();
		this.gearboxFacade = gearboxFacade;
	}

	@Override
	public boolean isGearValid(Gear gear) {
		if (gear == null) {
			return false;
		}

		if (! gear.isForward()) {
			return false;
		}

		if (gear.compareTo(this.gearboxFacade.getMaxGear()) > 0) {
			return false;
		}

		return true;
	}
}
