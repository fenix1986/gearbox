package pl.jprabucki.gearbox.driver;

import java.util.Optional;

import pl.jprabucki.gearbox.driver.decision.ChangeStateDecision;
import pl.jprabucki.gearbox.driver.drivemode.DriveModePolicy;
import pl.jprabucki.gearbox.driver.drivemode.ModePolicyFactory;
import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.init.GearboxInitPolicy;
import pl.jprabucki.gearbox.driver.init.InitPolicyFactory;
import pl.jprabucki.gearbox.driver.model.GearboxState;
import pl.jprabucki.gearbox.driver.overridemanual.OnlyOnceDecisionRejection;
import pl.jprabucki.gearbox.driver.overridemanual.OverrideManualChangeGearPolicy;

public class GearboxDriver {
	private GearboxFacade gearboxFacade;
	private ExternalSystemsFacade externalSystemsFacade;

	private DriveMode gearboxMode;
	private AggressiveMode aggressiveMode;

	private boolean mDynamicsMode;

	private OverrideManualChangeGearPolicy overrideManualChangeGearPolicy = new OnlyOnceDecisionRejection();

	GearboxDriver(GearboxFacade gearboxFacade, ExternalSystemsFacade externalSystemsFacade) {
		super();
		this.gearboxFacade = gearboxFacade;
		this.externalSystemsFacade = externalSystemsFacade;

		this.init();
	}

	private void init() {
		GearboxInitPolicy gearboxInitPolicy = InitPolicyFactory.createGearboxInitPolicy(this.gearboxFacade);

		gearboxInitPolicy.init();

		this.changeGearboxMode(DriveMode.Eco);
		this.changeAggressiveMode(AggressiveMode.Mode_1);
		this.turnOffMDynamics();
	}

	public void decide() {
		// Jeśli nie mogę nadpisać akcji użytkownika, to kończę
		if (! this.overrideManualChangeGearPolicy.canOverrideManualChangeGear()) {
			return;
		}

		GearboxState gearboxState = this.gearboxFacade.getGearboxState();

		// Decyzję podejmujemy wyłącznie w trybie Drive
		if (! GearboxState.Drive.equals(gearboxState)) {
			return;
		}

		DriveModePolicy decisionPolicy = ModePolicyFactory.createDecisionPolicy(this.gearboxMode, this.aggressiveMode);

		// Decyzja oparta o przyczepę
		if (this.externalSystemsFacade.isTrailerAttached()) {
			Optional<ChangeStateDecision> changeStateDecision = decisionPolicy.trailerDecision(this.externalSystemsFacade.getDriveDirection(), this.externalSystemsFacade.getCurrentGasThreshold());

			if (changeStateDecision.isPresent()) {
				changeStateDecision.get().execute(this.gearboxFacade, this.externalSystemsFacade);
				return;
			}
		}

		// Decyzja oparta o MDynamics
		if (this.mDynamicsMode) {
			Optional<ChangeStateDecision> changeStateDecision = decisionPolicy.mDynamicsDecision(this.externalSystemsFacade.getAngularSpeed());

			if (changeStateDecision.isPresent()) {
				changeStateDecision.get().execute(this.gearboxFacade, this.externalSystemsFacade);
				return;
			}
		}

		// Decyzja oparta o kickdown
		{
			Optional<ChangeStateDecision> changeStateDecision = decisionPolicy.kickdownDecision(this.externalSystemsFacade.getCurrentGasThreshold());

			if (changeStateDecision.isPresent()) {
				changeStateDecision.get().execute(this.gearboxFacade, this.externalSystemsFacade);
				return;
			}
		}

		// Decyzja operata o rpm
		{
			Optional<ChangeStateDecision> changeStateDecision = decisionPolicy.rpmDecision(this.externalSystemsFacade.getCurrentRpm());

			if (changeStateDecision.isPresent()) {
				changeStateDecision.get().execute(this.gearboxFacade, this.externalSystemsFacade);
				return;
			}
		}
	}

	public void changeGearboxMode(DriveMode gearboxMode) {
		if (gearboxMode == null) {
			throw new IllegalArgumentException();
		}

		this.gearboxMode = gearboxMode;
	}

	public void changeAggressiveMode(AggressiveMode aggressiveMode) {
		if (aggressiveMode == null) {
			throw new IllegalArgumentException();
		}

		this.aggressiveMode = aggressiveMode;
	}

	public void changeGearDown() {
		this.overrideManualChangeGearPolicy.manualChangedGear();

		this.gearboxFacade.changeGearDown();
	}

	public void changeGearUp() {
		this.overrideManualChangeGearPolicy.manualChangedGear();

		this.gearboxFacade.changeGearUp();
	}

	public void turnOnMDynamics() {
		this.mDynamicsMode = true;
	}

	public void turnOffMDynamics() {
		this.mDynamicsMode = false;
	}
}
