package pl.jprabucki.gearbox.driver.modepolicy.rpm;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.Rpm;

public interface RpmPolicy {

	Optional<ChangeStateDecision> decide(Rpm rpm);

	Rpm rpmGearDown();

	Rpm rpmGearUp();
}
