package spacepi.demonstration;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import spacepi.RangeSensor;

public class RangeDriver {
	final static GpioController gpio = GpioFactory.getInstance();

	// range sensor pins
	static GpioPinDigitalOutput sensor_trigger = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Sensor Trigger",
			PinState.LOW);

	static GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, "Sensor Result",
			PinPullResistance.PULL_DOWN);

	// Create the range sensor
	static RangeSensor rangesensor = new RangeSensor(sensor_trigger, sensor_result);

	public volatile double distance;

	public void init() {

		new Thread(new Runnable() {
			public void run() {
				System.out.println("In Thread");
				while (true) {
					sensor_trigger.high();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sensor_trigger.low();
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					rangeLoop();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	public void rangeLoop() throws InterruptedException {
		do {
			// Get the range
			double d = rangesensor.getRange();
			if (d > 0) {
				distance = d;
			}
		} while (true);
	}

	public double getDistance() {
		return distance;
	}

}
