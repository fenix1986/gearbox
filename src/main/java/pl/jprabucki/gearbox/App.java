package pl.jprabucki.gearbox;

import pl.jprabucki.gearbox.driver.GearboxDriver;
import pl.jprabucki.gearbox.driver.GearboxDriverFactory;

public class App {
	public static Object[] characteristics = new Object[] {2000d, 1000d, 1000d, 0.5d, 2500d, 4500d, 1500d, 0.5d, 5000d, 0.7d, 5000d, 5000d, 1500d, 2000d, 3000d, 6500d, 14d};

	public static void main(String[] args) {

		Gearbox gearbox = new Gearbox();
		ExternalSystems externalSystems = new ExternalSystems();
		SoundModule soundModule = new SoundModule();

		GearboxDriver gearboxDriver = GearboxDriverFactory.createGearboxDriver(gearbox, externalSystems, soundModule);
		gearboxDriver.decide();
	}
}
