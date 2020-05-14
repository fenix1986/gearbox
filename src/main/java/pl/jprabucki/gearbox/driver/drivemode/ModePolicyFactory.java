package pl.jprabucki.gearbox.driver.drivemode;

import pl.jprabucki.gearbox.driver.AggressiveMode;
import pl.jprabucki.gearbox.driver.DriveMode;
import pl.jprabucki.gearbox.driver.modepolicy.kickdown.ComfortKickdownPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.kickdown.EcoKickdownPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.kickdown.ManualKickdownPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.kickdown.SportKickdownPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.mdynamics.ComfortMDynamicsPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.mdynamics.EcoMDynamicsPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.mdynamics.ManualMDynamicsPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.mdynamics.SportMDynamicsPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.rpm.AggressiveModePolicy;
import pl.jprabucki.gearbox.driver.modepolicy.rpm.ComfortRpmPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.rpm.EcoRpmPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.rpm.ManualRpmPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.rpm.SportRpmPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.trailer.GeneralTrailerPolicy;
import pl.jprabucki.gearbox.driver.modepolicy.trailer.ManualTrailerPolicy;

public class ModePolicyFactory {

	public static DriveModePolicy createDecisionPolicy(DriveMode gearboxMode, AggressiveMode aggressiveMode) {
		switch(gearboxMode) {
			case Eco:
				return new DriveModePolicy(
					new EcoKickdownPolicy(),
					new EcoRpmPolicy(),
					new EcoMDynamicsPolicy(),
					new GeneralTrailerPolicy()
				);

			case Comfort:
				return new DriveModePolicy(
					new ComfortKickdownPolicy(),
					new AggressiveModePolicy(new ComfortRpmPolicy(), aggressiveMode),
					new ComfortMDynamicsPolicy(),
					new GeneralTrailerPolicy()
				);

			case Sport:
				return new DriveModePolicy(
					new SportKickdownPolicy(),
					new AggressiveModePolicy(new SportRpmPolicy(), aggressiveMode),
					new SportMDynamicsPolicy(),
					new GeneralTrailerPolicy()
				);

			case Manual:
				return new DriveModePolicy(
					new ManualKickdownPolicy(),
					new ManualRpmPolicy(),
					new ManualMDynamicsPolicy(),
					new ManualTrailerPolicy()
				);

			default:
				throw new IllegalArgumentException();
		}
	}

}
