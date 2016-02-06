package spacepi;

import java.util.Date;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;

public class RangeSensor {

	GpioPinDigitalOutput firepulse;
	GpioPinDigitalInput result_pin;

	long startMs = new Date().getTime();
	long elapsedMs = 0;
	
	public static final int MS_THRESHOLD = 200;
	int step = 0;
	long lessValue = Long.MAX_VALUE;
	
	
	public RangeSensor(GpioPinDigitalOutput trigger, GpioPinDigitalInput result_pin) {
		this.firepulse = trigger;
		this.result_pin = result_pin;
	}

	
	/**
	 * Trigger the Range Sensor and return the result
	 * @throws InterruptedException 
	 */
	public double getRange() throws InterruptedException {
		
		long start = 0;
		long diff = 0;
		
		/*
		 * firepulse.high(); Thread.sleep(10); firepulse.low();
		 */

		while (result_pin.isLow()) {
			start = System.nanoTime();
		}

		while (result_pin.isHigh()) {
			//block
		}

		diff = (System.nanoTime() - start) / 58000;
		if (lessValue > diff){
			lessValue = diff;
		}
		
		
		
		step++;
		double result = -1; 
		
		elapsedMs = new Date().getTime() - startMs;
		if (elapsedMs >= MS_THRESHOLD){

			startMs = new Date().getTime();
			elapsedMs = 0;
			
			result = lessValue * 1;
			lessValue = Long.MAX_VALUE;
			return result;
		}
		return -1;
	}

}