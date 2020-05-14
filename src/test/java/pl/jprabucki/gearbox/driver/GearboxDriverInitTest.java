package pl.jprabucki.gearbox.driver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static pl.jprabucki.gearbox.driver.GearboxDriverTestUtil.gearboxInDriveMode;
import static pl.jprabucki.gearbox.driver.GearboxDriverTestUtil.gearboxInNeutralMode;
import static pl.jprabucki.gearbox.driver.GearboxDriverTestUtil.gearboxInParkMode;
import static pl.jprabucki.gearbox.driver.GearboxDriverTestUtil.gearboxInReverseMode;

import org.junit.jupiter.api.Test;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Gear;

class GearboxDriverInitTest {

	@Test
	void gearboxDriveModeInit() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);

		new GearboxDriver(gearboxFacade, externalSystemsFacade);

		verify(gearboxFacade, times(1)).changeGear(Gear.FIRST);
	}

	@Test
	void gearboxParkModeInit() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInParkMode(gearboxFacade);

		new GearboxDriver(gearboxFacade, externalSystemsFacade);

		verify(gearboxFacade, times(1)).changeGear(Gear.NEUTRAL);
	}

	@Test
	void gearboxNeutralModeInit() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInNeutralMode(gearboxFacade);

		new GearboxDriver(gearboxFacade, externalSystemsFacade);

		verify(gearboxFacade, times(1)).changeGear(Gear.NEUTRAL);
	}

	@Test
	void gearboxReverseModeInit() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInReverseMode(gearboxFacade);

		new GearboxDriver(gearboxFacade, externalSystemsFacade);

		verify(gearboxFacade, times(1)).changeGear(Gear.REVERSE);
	}
}
