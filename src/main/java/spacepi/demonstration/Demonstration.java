package spacepi.demonstration;

import java.io.IOException;
import java.util.Date;

public class Demonstration {

	final static int MIN_THRESHOLD = 0;

	final static int FIRST_GEAR = MIN_THRESHOLD + 20;
	final static int SECOND_GEAR = FIRST_GEAR + 20;
	final static int THIRD_GEAR = SECOND_GEAR + 20;
	final static int STOPPED_GEAR = THIRD_GEAR + 20;
	final static int FRONT_GEAR = STOPPED_GEAR + 30;

	final static int MAX_THRESHOLD = MIN_THRESHOLD + FIRST_GEAR + SECOND_GEAR + THIRD_GEAR;

	final static MotorDriver motor = new MotorDriver();
	static RangeDriver range = new RangeDriver();

	volatile static GearType gearSelected = GearType.stopped;
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		demonstrationLoop();
	}

	public static void demonstrationLoop() throws InterruptedException, IOException {
		motor.init();
		range.init();
		rangeValues();
		GearType oldGearSelected = null;
		long now = 0;
		while (true) {
			if (!gearSelected.equals(oldGearSelected) || new Date().getTime() - now  > 10000){
				now = new Date().getTime();
				oldGearSelected = gearSelected;
				System.out.println(gearSelected.toString() + " " + range.getDistance());
			}
			motor.updateSpeed(gearSelected);
		}
		
	}

	private static void rangeValues() {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					double distance = range.getDistance();
					if (distance > MIN_THRESHOLD && distance < MAX_THRESHOLD) {

						if (distance >= MIN_THRESHOLD && distance < FIRST_GEAR) {
							//System.out.println("INSIDE distance: " + GearType.first + " " + distance);
							gearSelected = GearType.first;
						} else if (distance >= FIRST_GEAR && distance < SECOND_GEAR) {
							//System.out.println("INSIDE distance: " + GearType.second + " " + distance);
							gearSelected = GearType.second;

						} else if (distance >= SECOND_GEAR && distance < THIRD_GEAR) {
							//System.out.println("INSIDE distance: " + GearType.third + " " + distance);
							gearSelected = GearType.third;
						}
						 else if (distance >= THIRD_GEAR && distance < STOPPED_GEAR) {
								//System.out.println("INSIDE distance: " + GearType.third + " " + distance);
								gearSelected = GearType.stopped;
						}
						 else if (distance >= STOPPED_GEAR && distance < FRONT_GEAR) {
							//System.out.println("INSIDE distance: " + GearType.third + " " + distance);
							gearSelected = GearType.front;
						}
						
						else{
							//System.out.println("INSIDE distance: OUT" + distance);
							gearSelected = GearType.stopped;
						}
						
					} else {
						//System.out.println("OUTSIDE: " + distance);
						gearSelected = GearType.stopped;
					}

				}
				
			}
		}).start();
	}

}
