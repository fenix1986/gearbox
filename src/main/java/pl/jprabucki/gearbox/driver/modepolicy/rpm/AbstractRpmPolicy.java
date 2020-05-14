package pl.jprabucki.gearbox.driver.modepolicy.rpm;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.decision.GearDownDecision;
import pl.jprabucki.gearbox.driver.decision.GearUpDecision;
import pl.jprabucki.gearbox.driver.model.Rpm;

public abstract class AbstractRpmPolicy implements RpmPolicy {

	@Override
	public Optional<ChangeStateDecision> decide(Rpm rpm) {
		if (this.rpmGearUp() != null && this.rpmGearUp().compareTo(rpm) <= 0) {
			return Optional.of(new GearUpDecision());
		}

		if (this.rpmGearUp() != null && this.rpmGearDown().compareTo(rpm) >= 0) {
			return Optional.of(new GearDownDecision());
		}

		return Optional.empty();
	}

}
