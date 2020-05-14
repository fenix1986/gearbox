package pl.jprabucki.gearbox.driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pl.jprabucki.gearbox.ExternalSystems;
import pl.jprabucki.gearbox.SoundModule;
import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.model.BreakThreshold;
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Rpm;

class ExternalSystemsFacadeTest {

	@Test
	void testGetCurrentRpm() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentRpm()).thenReturn(1000d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertEquals(new Rpm(1000d), externalSystemsFacade.getCurrentRpm());
	}

	@Test
	void testGetCurrentInvalidRpm() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentRpm()).thenReturn(-1000d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);

		assertThrows(IllegalArgumentException.class, () -> externalSystemsFacade.getCurrentRpm());
	}

	@Test
	void testGetGasThreshold() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentGasThreshold()).thenReturn(0.5d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertEquals(new GasThreshold(0.5d), externalSystemsFacade.getCurrentGasThreshold());
	}

	@Test
	void testGetOverMaxGasThreshold() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentGasThreshold()).thenReturn(1.5d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertThrows(IllegalArgumentException.class, () -> externalSystemsFacade.getCurrentGasThreshold());
	}

	@Test
	void testGetUnderMinGasThreshold() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentGasThreshold()).thenReturn(-0.5d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertThrows(IllegalArgumentException.class, () -> externalSystemsFacade.getCurrentGasThreshold());
	}

	@Test
	void testGetBreakThreshold() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentBreakThreshold()).thenReturn(0.5d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertEquals(new BreakThreshold(0.5d), externalSystemsFacade.getCurrentBreakThreshold());
	}

	@Test
	void testGetOverMaxBreakThreshold() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentBreakThreshold()).thenReturn(1.5d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertThrows(IllegalArgumentException.class, () -> externalSystemsFacade.getCurrentBreakThreshold());
	}

	@Test
	void testGetUnderMinBreakThreshold() {
		ExternalSystems externalSystems = Mockito.mock(ExternalSystems.class);
		SoundModule soundModule = Mockito.mock(SoundModule.class);
		Mockito.when(externalSystems.getCurrentBreakThreshold()).thenReturn(-0.5d);

		ExternalSystemsFacade externalSystemsFacade = new ExternalSystemsFacade(externalSystems, soundModule);
		assertThrows(IllegalArgumentException.class, () -> externalSystemsFacade.getCurrentBreakThreshold());
	}
}
