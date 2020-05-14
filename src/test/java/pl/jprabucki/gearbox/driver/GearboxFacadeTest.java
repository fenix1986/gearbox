package pl.jprabucki.gearbox.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import pl.jprabucki.gearbox.Gearbox;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Gear;

class GearboxFacadeTest {

	@Test
	void driveModeChangeToForwardValidGear() {
		Gearbox gearbox = gearboxInDriveState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		int validGear = 1;

		gearboxFacade.changeGear(new Gear(validGear));
		verify(gearbox, times(1)).setCurrentGear(validGear);
	}

	@Test
	void driveModeChangeToForwardInalidGear() {
		Gearbox gearbox = gearboxInDriveState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		int invalidGear = 100;

		gearboxFacade.changeGear(new Gear(invalidGear));
		verify(gearbox, never()).setCurrentGear(invalidGear);
	}

	@Test
	void driveModeChangeToBackwardValidGear() {
		Gearbox gearbox = gearboxInDriveState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		int invalidGear = -1;

		gearboxFacade.changeGear(new Gear(invalidGear));
		verify(gearbox, never()).setCurrentGear(invalidGear);
	}

	@Test
	void reverseModeChangeToBackwardValidGear() {
		Gearbox gearbox = gearboxInReverseState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		int validGear = -1;

		gearboxFacade.changeGear(new Gear(validGear));
		verify(gearbox, times(1)).setCurrentGear(validGear);
	}

	@Test
	void reverseModeChangeToBackwardInvalidGear() {
		Gearbox gearbox = gearboxInReverseState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		int invalidGear = -2;

		gearboxFacade.changeGear(new Gear(invalidGear));
		verify(gearbox, never()).setCurrentGear(invalidGear);
	}

	@Test
	void reverseModeChangeToForwardValidGear() {
		Gearbox gearbox = gearboxInReverseState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		int validGear = 1;

		gearboxFacade.changeGear(new Gear(validGear));
		verify(gearbox, never()).setCurrentGear(validGear);
	}

	@Test
	void driveModeGearUpOnLowGear() {
		Gearbox gearbox = gearboxInDriveState();

		int gear = 1;

		when(gearbox.getCurrentGear()).thenReturn(gear);

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);
		gearboxFacade.changeGearUp();
		verify(gearbox, times(1)).setCurrentGear(gear + 1);
	}

	@Test
	void driveModeGearUpOnMaxGear() {
		Gearbox gearbox = gearboxInDriveState();

		int gear = gearbox.getMaxDrive();

		when(gearbox.getCurrentGear()).thenReturn(gear);

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);
		gearboxFacade.changeGearUp();
		verify(gearbox, never()).setCurrentGear(gear + 1);
	}

	@Test
	void driveModeGearDownOnLowGear() {
		Gearbox gearbox = gearboxInDriveState();

		int gear = 1;

		when(gearbox.getCurrentGear()).thenReturn(gear);

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);
		gearboxFacade.changeGearDown();
		verify(gearbox, never()).setCurrentGear(gear - 1);
	}

	@Test
	void driveModeGearDownOnMaxGear() {
		Gearbox gearbox = gearboxInDriveState();

		int gear = gearbox.getMaxDrive();

		when(gearbox.getCurrentGear()).thenReturn(gear);

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);
		gearboxFacade.changeGearDown();
		verify(gearbox, times(1)).setCurrentGear(gear - 1);
	}

	@Test
	void parkStateSetForwardGear() {
		Gearbox gearbox = gearboxInParkState();

		int gear = 1;

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(gear));
		verify(gearbox, never()).setCurrentGear(gear);
	}

	@Test
	void parkStateSetBackwardGear() {
		Gearbox gearbox = gearboxInParkState();

		int gear = -1;

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(gear));
		verify(gearbox, never()).setCurrentGear(gear);
	}

	@Test
	void setNullGearInDriveMode() {
		Gearbox gearbox = gearboxInDriveState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(null);
		verify(gearbox, never()).setCurrentGear(anyInt());
	}

	@Test
	void setNullGearInNeutralMode() {
		Gearbox gearbox = gearboxInNeutralState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(null);
		verify(gearbox, never()).setCurrentGear(anyInt());
	}

	@Test
	void setNullGearInParkMode() {
		Gearbox gearbox = gearboxInParkState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(null);
		verify(gearbox, never()).setCurrentGear(anyInt());
	}

	@Test
	void setNullGearInReverseMode() {
		Gearbox gearbox = gearboxInReverseState();

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(null);
		verify(gearbox, never()).setCurrentGear(anyInt());
	}

	@Test
	void setNeutralGearInDriveMode() {
		Gearbox gearbox = gearboxInDriveState();
		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(0));

		verify(gearbox, never()).setCurrentGear(anyInt());
	}

	@Test
	void setNeutralGearInNeutralMode() {
		Gearbox gearbox = gearboxInNeutralState();
		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(0));

		verify(gearbox, times(1)).setCurrentGear(0);
	}

	@Test
	void setNeutralGearInParkMode() {
		Gearbox gearbox = gearboxInParkState();
		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(0));

		verify(gearbox, times(1)).setCurrentGear(0);
	}

	@Test
	void setNeutralGearInReverseMode() {
		Gearbox gearbox = gearboxInReverseState();
		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(0));

		verify(gearbox, never()).setCurrentGear(anyInt());
	}

	@Test
	void neutralStateSetForwardGear() {
		Gearbox gearbox = gearboxInNeutralState();

		int gear = 1;

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(gear));
		verify(gearbox, never()).setCurrentGear(gear);
	}

	@Test
	void neutralNextGear() {
		assertTrue(new Gear(0).getNextGear().isEmpty());
	}

	@Test
	void neutralPreviousGear() {
		assertTrue(new Gear(0).getPreviousGear().isEmpty());
	}

	@Test
	void backwardNextLowGear() {
		Gear gear = new Gear(-1);

		assertTrue(gear.getNextGear().isPresent());
		assertEquals(-2, gear.getNextGear().get().toIntValue());
	}

	@Test
	void backwardPreviousLowGear() {
		Gear gear = new Gear(-1);

		assertTrue(gear.getPreviousGear().isEmpty());
	}

	@Test
	void backwardNextHighGear() {
		Gear gear = new Gear(-2);

		assertTrue(gear.getNextGear().isPresent());
		assertEquals(-3, gear.getNextGear().get().toIntValue());
	}

	@Test
	void backwardPreviousHighGear() {
		Gear gear = new Gear(-2);

		assertTrue(gear.getNextGear().isPresent());
		assertEquals(-1, gear.getPreviousGear().get().toIntValue());
	}

	@Test
	void forwardNextLowGear() {
		Gear gear = new Gear(1);

		assertTrue(gear.getNextGear().isPresent());
		assertEquals(2, gear.getNextGear().get().toIntValue());
	}

	@Test
	void forwardPreviousLowGear() {
		Gear gear = new Gear(1);

		assertTrue(gear.getPreviousGear().isEmpty());
	}

	@Test
	void forwardNextHighGear() {
		Gear gear = new Gear(2);

		assertTrue(gear.getNextGear().isPresent());
		assertEquals(3, gear.getNextGear().get().toIntValue());
	}

	@Test
	void forwardPreviousHighGear() {
		Gear gear = new Gear(2);

		assertTrue(gear.getNextGear().isPresent());
		assertEquals(1, gear.getPreviousGear().get().toIntValue());
	}

	@Test
	void neutralStateSetBackwardGear() {
		Gearbox gearbox = gearboxInNeutralState();

		int gear = -1;

		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		gearboxFacade.changeGear(new Gear(gear));
		verify(gearbox, never()).setCurrentGear(gear);
	}

	@Test
	void invalidGearboxState() {
		Gearbox gearbox = gearboxInInvalidState();
		GearboxFacade gearboxFacade = new GearboxFacade(gearbox);

		assertThrows(IllegalStateException.class, () -> gearboxFacade.getGearboxState());
	}

	private static Gearbox gearboxInDriveState() {
		Gearbox gearbox = mock(Gearbox.class);
		when(gearbox.getState()).thenReturn(1);
		when(gearbox.getMaxDrive()).thenReturn(8);
		return gearbox;
	}

	private static Gearbox gearboxInParkState() {
		Gearbox gearbox = mock(Gearbox.class);
		when(gearbox.getState()).thenReturn(2);
		when(gearbox.getMaxDrive()).thenReturn(8);
		return gearbox;
	}

	private static Gearbox gearboxInReverseState() {
		Gearbox gearbox = mock(Gearbox.class);
		when(gearbox.getState()).thenReturn(3);
		when(gearbox.getMaxDrive()).thenReturn(8);
		return gearbox;
	}

	private static Gearbox gearboxInNeutralState() {
		Gearbox gearbox = mock(Gearbox.class);
		when(gearbox.getState()).thenReturn(4);
		when(gearbox.getMaxDrive()).thenReturn(8);
		return gearbox;
	}

	private static Gearbox gearboxInInvalidState() {
		Gearbox gearbox = mock(Gearbox.class);
		when(gearbox.getState()).thenReturn(5);
		when(gearbox.getMaxDrive()).thenReturn(8);
		return gearbox;
	}
}
