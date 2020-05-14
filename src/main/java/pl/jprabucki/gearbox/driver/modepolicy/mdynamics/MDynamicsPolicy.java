package pl.jprabucki.gearbox.driver.modepolicy.mdynamics;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.AngularSpeed;

public interface MDynamicsPolicy {

	Optional<ChangeStateDecision> decide(AngularSpeed angularSpeed);

}
