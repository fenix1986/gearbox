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
import pl.jprabucki.gearbox.driver.model.AngularSpeed;
import pl.jprabucki.gearbox.driver.model.Rpm;

class GearboxDriverMDynamicsTest {

	@Test
	void ecoModeStayOnGear() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void ecoModeGearUp() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2100));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void ecoModeGearDown() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void comfortModeStayOnGear() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2400));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void comfortModeGearUp() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2600));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void comfortModeGearDown() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void sportModeStayOnGear() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(4900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeGearUp() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(5100));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeGearDown() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void ecoModeStayOnGearHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1500));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void ecoModeGearUpHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2100));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, times(1)).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void ecoModeGearDownHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Eco);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, times(1)).changeGearDown();
	}

	@Test
	void comfortModeStayOnGearHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2400));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void comfortModeGearUpHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2600));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void comfortModeGearDownHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(1900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Comfort);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeStayOnGearHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(4900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();
		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeGearUpHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(5100));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

	@Test
	void sportModeGearDownHighAngularSpeed() {
		GearboxFacade gearboxFacade = mock(GearboxFacade.class);
		ExternalSystemsFacade externalSystemsFacade = gearboxInDriveMode(gearboxFacade);
		when(externalSystemsFacade.getCurrentRpm()).thenReturn(new Rpm(2900));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(60));

		GearboxDriver gearboxDriver = new GearboxDriver(gearboxFacade, externalSystemsFacade);
		gearboxDriver.changeGearboxMode(DriveMode.Sport);
		gearboxDriver.turnOnMDynamics();

		gearboxDriver.decide();

		verify(gearboxFacade, never()).changeGearUp();
		verify(gearboxFacade, never()).changeGearDown();
	}

}
