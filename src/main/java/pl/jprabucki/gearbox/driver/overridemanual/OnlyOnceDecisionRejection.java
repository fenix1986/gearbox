package pl.jprabucki.gearbox.driver.overridemanual;

public class OnlyOnceDecisionRejection implements OverrideManualChangeGearPolicy {

	private boolean canOverride = true;

	@Override
	public boolean canOverrideManualChangeGear() {
		if (! this.canOverride) {
			this.canOverride = true;

			return false;
		}

		return true;
	}

	@Override
	public void manualChangedGear() {
		this.canOverride = false;
	}

}
