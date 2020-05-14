package pl.jprabucki.gearbox.driver.modepolicy.mdynamics;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.AngularSpeed;

public class EcoMDynamicsPolicy implements MDynamicsPolicy {

	@Override
	public Optional<ChangeStateDecision> decide(AngularSpeed angularSpeed) {
		return Optional.empty();
	}
}
