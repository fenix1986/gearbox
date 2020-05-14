package pl.jprabucki.gearbox.driver.init;

import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.GearboxState;

public class InitPolicyFactory {

	public static GearboxInitPolicy createGearboxInitPolicy(GearboxFacade gearboxFacade) {
		GearboxState gearboxState = gearboxFacade.getGearboxState();

		switch (gearboxState) {
			case Drive:
				return new DriveInitPolicy(gearboxFacade);
			case Neutral:
				return new NeutralInitPolicy(gearboxFacade);
			case Park:
				return new ParkInitPolicy(gearboxFacade);
			case Reverse:
				return new ReverseInitPolicy(gearboxFacade);
			default:
				throw new IllegalArgumentException();
		}
	}
}
