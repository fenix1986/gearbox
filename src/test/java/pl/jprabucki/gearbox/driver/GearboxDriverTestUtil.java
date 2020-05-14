package pl.jprabucki.gearbox.driver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import pl.jprabucki.gearbox.driver.facade.externalsystems.ExternalSystemsFacade;
import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.AngularSpeed;
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Gear;
import pl.jprabucki.gearbox.driver.model.GearboxState;

public class GearboxDriverTestUtil {

	public static ExternalSystemsFacade gearboxInDriveMode(GearboxFacade gearboxFacade) {
		ExternalSystemsFacade externalSystemsFacade = mock(ExternalSystemsFacade.class);
		when(gearboxFacade.getGearboxState()).thenReturn(GearboxState.Drive);
		when(gearboxFacade.getMaxGear()).thenReturn(new Gear(8));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.1));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));
		return externalSystemsFacade;
	}

	public static ExternalSystemsFacade gearboxInParkMode(GearboxFacade gearboxFacade) {
		ExternalSystemsFacade externalSystemsFacade = mock(ExternalSystemsFacade.class);
		when(gearboxFacade.getGearboxState()).thenReturn(GearboxState.Park);
		when(gearboxFacade.getMaxGear()).thenReturn(new Gear(8));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.1));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));
		return externalSystemsFacade;
	}

	public static ExternalSystemsFacade gearboxInNeutralMode(GearboxFacade gearboxFacade) {
		ExternalSystemsFacade externalSystemsFacade = mock(ExternalSystemsFacade.class);
		when(gearboxFacade.getGearboxState()).thenReturn(GearboxState.Neutral);
		when(gearboxFacade.getMaxGear()).thenReturn(new Gear(8));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.1));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));
		return externalSystemsFacade;
	}

	public static ExternalSystemsFacade gearboxInReverseMode(GearboxFacade gearboxFacade) {
		ExternalSystemsFacade externalSystemsFacade = mock(ExternalSystemsFacade.class);
		when(gearboxFacade.getGearboxState()).thenReturn(GearboxState.Reverse);
		when(gearboxFacade.getMaxGear()).thenReturn(new Gear(8));
		when(externalSystemsFacade.getCurrentGasThreshold()).thenReturn(new GasThreshold(0.1));
		when(externalSystemsFacade.getAngularSpeed()).thenReturn(new AngularSpeed(0));
		return externalSystemsFacade;
	}
}
