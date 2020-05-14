package pl.jprabucki.gearbox.driver.modepolicy.trailer;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.DriveDirection;
import pl.jprabucki.gearbox.driver.model.GasThreshold;

public interface TrailerPolicy {

	Optional<ChangeStateDecision> decide(DriveDirection driveDirection, GasThreshold gasThreshold);

}
