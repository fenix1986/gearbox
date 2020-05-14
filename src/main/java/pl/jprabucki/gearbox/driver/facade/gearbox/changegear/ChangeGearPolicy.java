package pl.jprabucki.gearbox.driver.facade.gearbox.changegear;

import pl.jprabucki.gearbox.driver.model.Gear;

public interface ChangeGearPolicy {
	boolean isGearValid(Gear gear);
}
