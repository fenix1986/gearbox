package pl.jprabucki.gearbox.driver.drivemode;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.model.AngularSpeed;
import pl.jprabucki.gearbox.driver.model.DriveDirection;
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Rpm;
import pl.jprabucki.gearbox.driver.modepolicy.kickdown.KickdownPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.mdynamics.MDynamicsPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.rpm.RpmPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.trailer.TrailerPolicy;

public class DriveModePolicy {

	private KickdownPolicy kickdownPolicy;
	private RpmPolicy rpmPolicy;
	private MDynamicsPolicy mDynamicsPolicy;
	private TrailerPolicy trailerPolicy;

	DriveModePolicy(KickdownPolicy kickdownPolicy, RpmPolicy rpmPolicy, MDynamicsPolicy mDynamicsPolicy, TrailerPolicy trailerPolicy) {
		super();
		this.kickdownPolicy = kickdownPolicy;
		this.rpmPolicy = rpmPolicy;
		this.mDynamicsPolicy = mDynamicsPolicy;
		this.trailerPolicy = trailerPolicy;
	}

	public Optional<ChangeStateDecision> kickdownDecision(GasThreshold gasThreshold) {
		return this.kickdownPolicy.decide(gasThreshold);
	}

	public Optional<ChangeStateDecision> rpmDecision(Rpm rpm) {
		return this.rpmPolicy.decide(rpm);
	}

	public Optional<ChangeStateDecision> mDynamicsDecision(AngularSpeed angularSpeed) {
		return this.mDynamicsPolicy.decide(angularSpeed);
	}

	public Optional<ChangeStateDecision> trailerDecision(DriveDirection driveDirection, GasThreshold gasThreshold) {
		return this.trailerPolicy.decide(driveDirection, gasThreshold);
	}

}