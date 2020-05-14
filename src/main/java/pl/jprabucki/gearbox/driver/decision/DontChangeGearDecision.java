package pl.jprabucki.gearbox.driver.decision;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;

public class DontChangeGearDecision extends AbstractDecision implements ChangeStateDecision {

	@Override
	public void execute(GearboxFacade gearboxFacade, ExternalSystemsFacade externalSystemsFacade) {
	}

}