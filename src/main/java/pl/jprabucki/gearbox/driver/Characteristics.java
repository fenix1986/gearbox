package pl.jprabucki.gearbox.driver;

import pl.jprabucki.gearbox.driver.model.AngularSpeed;
import pl.jprabucki.gearbox.driver.model.GasThreshold;
import pl.jprabucki.gearbox.driver.model.Rpm;

public class Characteristics {
	public static final Rpm ECO_RPM_GEAR_UP;
	public static final Rpm ECO_RPM_GEAR_DOWN;
	public static final Rpm COMFORT_RPM_GEAR_UP;
	public static final Rpm COMFORT_RPM_GEAR_DOWN;
	public static final Rpm SPORT_RPM_GEAR_UP;
	public static final Rpm SPORT_RPM_GEAR_DOWN;

	public static final GasThreshold COMFORT_KICKDOWN;
	public static final GasThreshold SPORT_KICKDOWN;

	public static final AngularSpeed ANGULAR_SPEED_LEVEL;

	/*
		0 - tryb ECO - rpm czy podbić bieg przy przyspieszeniu
		1 - tryb ECO - rpm czy redukować bieg przy przyspieszeniu
		2 - tryb COMFORT - rpm czy redukować bieg przy przyspieszeniu
		3 - tryb COMFORT - threshold naciśnięcia pedału gazu, żeby jeszcze nie było kickdown
		4 - tryb COMFORT - rpm czy podbić bieg przy przyspieszeniu
		5 - tryb COMFORT - rpm czy zrzucić bieg w kickdown
		6 - tryb SPORT - rpm czy zrzucić bieg przy przyspieszeniu
		7 - tryb SPORT - threshold naciśnięcia pedału gazu, żeby czy lekko przyspieszamy
		8 - tryb SPORT - rpm czy zwiększamy bieg w lekkim przyspieszeniu
		9 - tryb SPORT - threshold naciśnięcia pedału gazu, żeby czy lekki kickdown
		10 - tryb SPORT - rpm czy redukcja w lekkim kickdown
		11 - tryb SPORT -  rpm czy zrzucić 2 biegi w MOCNYM kickdown
		12 - tryb ECO - rpm czy zrzucić bieg przy hamowaniu
		13 - tryb COMFORT - rpm czy zrzucić bieg przy hamowaniu
		14 - tryb SPORT - rpm czy zrzucić bieg przy hamowaniu
		15 - ???
		17 - tryb HIDDEN MODE - kiedy podbić bieg przy przyspieszaniu
		18 - tryb HIDDEN MODE - kiedy redukować bieg przy przyspieszaniu w hidden mode
		19 - tryb HIDDEN MODE - kiedy redukować bieg przy hamowaniu w hidden mode
	*/
	private static Object[] characteristics = new Object[] {2000d, 1000d, 1000d, 0.5d, 2500d, 4500d, 1500d, 0.5d, 5000d, 0.7d, 5000d, 5000d, 1500d, 2000d, 3000d, 6500d, 14d};
	//                                                      0      1      2      3     4      5      6      7     8      9     10     11     12     13     14     15     16

	static {
		ECO_RPM_GEAR_UP = new Rpm((double) characteristics[0]);
		ECO_RPM_GEAR_DOWN = new Rpm((double) characteristics[1]);
		COMFORT_RPM_GEAR_UP = new Rpm((double) characteristics[4]);
		COMFORT_RPM_GEAR_DOWN = new Rpm((double) characteristics[13]);
		SPORT_RPM_GEAR_UP = new Rpm((double) characteristics[8]);
		SPORT_RPM_GEAR_DOWN = new Rpm((double) characteristics[14]);

		COMFORT_KICKDOWN = new GasThreshold((double) characteristics[3]);
		SPORT_KICKDOWN = new GasThreshold((double) characteristics[9]);

		ANGULAR_SPEED_LEVEL = new AngularSpeed(50.0d);
	}
}
