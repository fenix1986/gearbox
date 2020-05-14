package pl.jprabucki.gearbox.driver.decision;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Decibel;

public class MakeNoiseDecision extends AbstractDecision implements ChangeStateDecision {

	@Override
	public void execute(GearboxFacade gearboxFacade, ExternalSystemsFacade externalSystemsFacade) {
		externalSystemsFacade.makeNoise(new Decibel(70));

	}
}
