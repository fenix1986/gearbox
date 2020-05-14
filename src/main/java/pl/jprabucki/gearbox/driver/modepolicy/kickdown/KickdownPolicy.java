package pl.jprabucki.gearbox.driver.modepolicy.kickdown;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.GasThreshold;

public interface KickdownPolicy {

	Optional<ChangeStateDecision> decide(GasThreshold gasThreshold);

}
