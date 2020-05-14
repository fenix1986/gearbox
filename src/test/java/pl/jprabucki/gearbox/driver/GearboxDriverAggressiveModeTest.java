package pl.jprabucki.gearbox.driver;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.jprabucki.gearbox.driver.GearboxDriverTestUtil.gearboxInDriveMode;

import org.junit.jupiter.api.Test;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Decibel;
import pl.jprabucki.gearbox.driver.model.Rpm;

class GearboxDriverAggressiveModeTest {

	@Test
	void ecoModeGearUp() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2100));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.changeAggressiveMode(AggressiveMode.Mode_2);

		gearboxDriver.decide();
		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
		verify(externalSystemsFacade, never()).makeNoise(any(Decibel.class));
	}

	@Test
	void comfortModeStayOnGear() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2600));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.changeAggressiveMode(AggressiveMode.Mode_2);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
		verify(externalSystemsFacade, never()).makeNoise(any(Decibel.class));
	}

	@Test
	void comfortModeChangeAggressiveMode() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2600));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.changeAggressiveMode(AggressiveMode.Mode_1);

		gearboxDriver.decide();
		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();

		gearboxDriver.changeAggressiveMode(AggressiveMode.Mode_2);

		gearboxDriver.decide();
		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
		verify(externalSystemsFacade, never()).makeNoise(any(Decibel.class));
	}

	@Test
	void comfortAggressiveMode3() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2600));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.changeAggressiveMode(AggressiveMode.Mode_3);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
		verify(externalSystemsFacade, times(1)).makeNoise(any(Decibel.class));
	}
}
