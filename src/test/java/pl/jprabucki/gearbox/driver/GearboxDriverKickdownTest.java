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
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Rpm;

class GearboxDriverKickdownTest {

	@Test
	void ecoModeKickdownMaxGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(1.0d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void ecoModeKickdownMinGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.0d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void ecoModeKickdownHalfGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.5d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void comfortModeKickdownMaxGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2400));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(1.0d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void comfortModeKickdownMinGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2400));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.0d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void comfortModeKickdownHalfGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2400));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.5d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeKickdownMaxGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(4900));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(1.0d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void sportModeKickdownMinGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(4900));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.0d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeKickdownHalfGasTreshold() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(4900));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.5d));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}
}
