package pl.jprabucki.gearbox.driver.modepolicy.kickdown;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.GasThreshold;

public class EcoKickdownPolicy implements KickdownPolicy {

	@Override
	public Optional<ChangeStateDecision> decide(GasThreshold gasThreshold) {
		return Optional.empty();
	}
}
