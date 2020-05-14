package pl.jprabucki.gearbox.driver.decision;

public abstract class AbstractDecision implements ChangeStateDecision {

	@Override
	public void addDecision(ChangeStateDecision changeStateDecision) {
		throw new IllegalStateException();
	}
}
