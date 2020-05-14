package pl.jprabucki.gearbox.driver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static pl.jprabucki.gearbox.driver.GearboxDriverTestUtil.gearboxInDriveMode;

import org.junit.jupiter.api.Test;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.DriveDirection;
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Rpm;

class GearboxDriverTrailerTest {

	@Test
	void ecoModeWithTrailerNoGas() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0));
		when(externalSystemsFacade.isTrailerAttached()).thenReturn(true);
		when(externalSystemsFacade.getDriveDirection()).thenReturn(DriveDirection.Down);

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void ecoModeWithTrailerWithGas() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.1));
		when(externalSystemsFacade.isTrailerAttached()).thenReturn(true);
		when(externalSystemsFacade.getDriveDirection()).thenReturn(DriveDirection.Down);

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void manualModeWithTrailerNoGas() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0));
		when(externalSystemsFacade.isTrailerAttached()).thenReturn(true);
		when(externalSystemsFacade.getDriveDirection()).thenReturn(DriveDirection.Down);

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Manual);

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

}
