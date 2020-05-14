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
import pl.jprabucki.gearbox.driver.model.Rpm;

class GearboxDriverManualTest {

	@Test
	void manualModeLowRpmAutoDecisionOff() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1000));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Manual);

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void manualModeHighRpmAutoDecisionOff() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(5000));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Manual);

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void manualModeMediumRpmAutoDecisionOff() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2500));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Manual);

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void manualModeGearUp() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Manual);

		gearboxDriver.changeGearUp();

		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void manualModeGearDown() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Manual);

		gearboxDriver.changeGearDown();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void ecoModeHighRpmManualChange() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(5000));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);

		// Ręczna zmiana
		gearboxDriver.changeGearDown();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();

		// Bieg nie został zmieniony
		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();

		// Za drugim razem skrzynia zwiększyła bieg
		gearboxDriver.decide();

		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

}
