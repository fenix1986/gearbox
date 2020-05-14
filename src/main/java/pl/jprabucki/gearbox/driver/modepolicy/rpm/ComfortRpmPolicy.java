package pl.jprabucki.gearbox.driver.modepolicy.rpm;

import pl.jprabucki.gearbox.driver.Characteristics;
import pl.jprabucki.gearbox.driver.model.Rpm;

public class ComfortRpmPolicy extends AbstractRpmPolicy implements RpmPolicy {

	@Override
	public Rpm rpmGearDown() {
		return Characteristics.COMFORT_RPM_GEAR_DOWN;
	}

	@Override
	public Rpm rpmGearUp() {
		return Characteristics.COMFORT_RPM_GEAR_UP;
	}

}
