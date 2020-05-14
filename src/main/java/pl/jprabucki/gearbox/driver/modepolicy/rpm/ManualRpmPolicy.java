package pl.jprabucki.gearbox.driver.modepolicy.rpm;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.Rpm;

public class ManualRpmPolicy extends AbstractRpmPolicy implements RpmPolicy {

	@Override
	public Optional<ChangeStateDecision> decide(Rpm rpm) {
		return Optional.empty();
	}

	@Override
	public Rpm rpmGearDown() {
		return null;
	}

	@Override
	public Rpm rpmGearUp() {
		return null;
	}

}
