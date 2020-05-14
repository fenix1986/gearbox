package pl.jprabucki.gearbox.driver.init;

import pl.jprabucki.gearbox.driver.facade.gearbox.GearboxFacade;
import pl.jprabucki.gearbox.driver.model.Gear;

class NeutralInitPolicy implements GearboxInitPolicy {

	private GearboxFacade gearboxFacade;

	NeutralInitPolicy(GearboxFacade gearboxFacade) {
		super();
		this.gearboxFacade = gearboxFacade;
	}

	@Override
	public void init() {
		this.gearboxFacade.changeGear(Gear.NEUTRAL);
	}

}
