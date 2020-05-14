package pl.jprabucki.gearbox.driver.modepolicy.rpm;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.AggressiveMode;
import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.decision.MakeNoiseDecision;
import pl.jprabucki.gearbox.driver.decision.MultiplyDecision;
import pl.jprabucki.gearbox.driver.model.Rpm;

public class AggressiveModePolicy extends AbstractRpmPolicy implements RpmPolicy {

	private RpmPolicy baseRpmPolicy;
	private AggressiveMode aggressiveMode;

	public AggressiveModePolicy(RpmPolicy rpmPolicy, AggressiveMode aggressiveMode) {
		super();
		this.baseRpmPolicy = rpmPolicy;
		this.aggressiveMode = aggressiveMode;
	}

	@Override
	public Rpm rpmGearDown() {
		Rpm rpmGearDown = this.baseRpmPolicy.rpmGearDown();

		if (rpmGearDown != null) {
			return rpmGearDown.multiply(this.aggressiveMode.getMultiplier());
		}

		return null;
	}

	@Override
	public Rpm rpmGearUp() {
		Rpm rpmGearUp = this.baseRpmPolicy.rpmGearUp();

		if (rpmGearUp != null) {
			return rpmGearUp.multiply(this.aggressiveMode.getMultiplier());
		}

		return null;
	}

	@Override
	public Optional<ChangeStateDecision> decide(Rpm rpm) {
		 Optional<ChangeStateDecision> changeStateDecision = super.decide(rpm);

		 if (AggressiveMode.Mode_3.equals(this.aggressiveMode)) {
			 if (changeStateDecision.isPresent()) {
				 ChangeStateDecision multiplyDecision = new MultiplyDecision();
				 multiplyDecision.addDecision(changeStateDecision.get());
				 multiplyDecision.addDecision(new MakeNoiseDecision());

				 return Optional.of(multiplyDecision);
			 }
		 }

		 return changeStateDecision;
	}

}
