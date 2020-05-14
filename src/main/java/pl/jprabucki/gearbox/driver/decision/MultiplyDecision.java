package pl.jprabucki.gearbox.driver.decision;

import java.util.ArrayList;
import java.util.List;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;

public class MultiplyDecision implements ChangeStateDecision {

	List<ChangeStateDecision> changeStateDecisions = new ArrayList<ChangeStateDecision>();

	@Override
	public void execute(GearboxFacade gearboxFacade, ExternalSystemsFacade externalSystemsFacade) {
		for (ChangeStateDecision changeStateDecision : this.changeStateDecisions) {
			changeStateDecision.execute(gearboxFacade, externalSystemsFacade);
		}

	}

	@Override
	public void addDecision(ChangeStateDecision changeStateDecision) {
		this.changeStateDecisions.add(changeStateDecision);
	}

}
