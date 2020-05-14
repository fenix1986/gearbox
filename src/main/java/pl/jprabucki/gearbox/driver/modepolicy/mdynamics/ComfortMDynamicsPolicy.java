package pl.jprabucki.gearbox.driver.modepolicy.mdynamics;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.Characteristics;
import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.decision.DontChangeGearDecision;
import pl.jprabucki.gearbox.driver.model.AngularSpeed;

public class ComfortMDynamicsPolicy implements MDynamicsPolicy {

	@Override
	public Optional<ChangeStateDecision> decide(AngularSpeed angularSpeed) {
		if (Characteristics.ANGULAR_SPEED_LEVEL.compareTo(angularSpeed) <= 0) {
			return Optional.of(new DontChangeGearDecision());
		}

		return Optional.empty();
	}

}
