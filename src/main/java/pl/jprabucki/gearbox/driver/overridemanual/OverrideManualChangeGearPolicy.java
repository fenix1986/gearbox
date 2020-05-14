package pl.jprabucki.gearbox.driver.overridemanual;

public interface OverrideManualChangeGearPolicy {
	public boolean canOverrideManualChangeGear();

	public void manualChangedGear();
}
