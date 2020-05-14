package pl.jprabucki.gearbox.driver.facade.gearbox.changegear;

import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.GearboxState;

public class ChangeGearPolicyFactory {

	public static ChangeGearPolicy createChangeGearPolicy(GearboxFacade gearboxFacade) {
		GearboxState gearboxState = gearboxFacade.getGearboxState();

		switch (gearboxState) {
			case Drive:
				return new DriveChangeGearPolicy(gearboxFacade);
			case Neutral:
				return new NeutralChangeGearPolicy(gearboxFacade);
			case Park:
				return new ParkChangeGearPolicy(gearboxFacade);
			case Reverse:
				return new ReverseChangeGearPolicy(gearboxFacade);
			default:
				throw new IllegalArgumentException();
		}
	}

}
