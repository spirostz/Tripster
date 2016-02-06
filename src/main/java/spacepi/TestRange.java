package spacepi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class TestRange {

	final static GpioController gpio = GpioFactory.getInstance();

	// range sensor pins
	static GpioPinDigitalOutput sensor_trigger = gpio
			.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Sensor Trigger",
					PinState.LOW);

	static GpioPinDigitalInput sensor_result = gpio.provisionDigitalInputPin(
			RaspiPin.GPIO_03, "Sensor Result", PinPullResistance.PULL_DOWN);

	// Create the range sensor
	static RangeSensor rangesensor = new RangeSensor(sensor_trigger,
			sensor_result);

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Number of ms for samples: " + RangeSensor.MS_THRESHOLD + "ms");
		
		
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
		}).start();;
		
		do {
			// Get the range
			double distance = rangesensor.getRange();
			if (distance > 0){
				System.out.println("RangeSensorresult =" + distance + "cm");
			}
			
			
		} while (true);

		
		
	}

}
