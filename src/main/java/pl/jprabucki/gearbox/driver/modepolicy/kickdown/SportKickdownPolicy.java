package pl.jprabucki.gearbox.driver.modepolicy.kickdown;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.Characteristics;
import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.decision.GearDownDecision;
import pl.jprabucki.gearbox.driver.model.GasThreshold;

public class SportKickdownPolicy implements KickdownPolicy {

	@Override
	public Optional<ChangeStateDecision> decide(GasThreshold gasThreshold) {
		if (Characteristics.SPORT_KICKDOWN.compareTo(gasThreshold) < 0) {
			return Optional.of(new GearDownDecision());
		}

		return Optional.empty();
	}

}
