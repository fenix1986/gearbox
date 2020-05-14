package pl.jprabucki.gearbox.driver.decision;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;

public interface ChangeStateDecision {

	void execute(GearboxFacade gearboxFacade, ExternalSystemsFacade externalSystemsFacade);

	void addDecision(ChangeStateDecision changeStateDecision);
}
