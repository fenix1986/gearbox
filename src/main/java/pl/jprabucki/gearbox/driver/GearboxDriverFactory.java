package pl.jprabucki.gearbox.driver;

import pl.jprabucki.gearbox.ExternalSystems;
import pl.jprabucki.gearbox.Gearbox;
import pl.jprabucki.gearbox.SoundModule;
import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;

public class GearboxDriverFactory {
	public static GearboxDriver createGearboxDriver(Gearbox gearbox, ExternalSystems externalSystems, SoundModule soundModule) {
		return new GearboxDriver(
			new GearboxFacade(gearbox),
			new ExternalSystemsFacade(externalSystems, soundModule)
		);
	}
}
