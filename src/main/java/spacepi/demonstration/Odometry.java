package spacepi.demonstration;

import java.io.IOException;

public class Odometry {
	
	final static MotorDriver motor = new MotorDriver();
	
	public static void main(String[] args) throws InterruptedException, IOException {
		motor.init();
		motor.resetEncoders();
		System.out.println("Encoder Left: " + motor.getEncoderLeft());
		System.out.println("Encoder Right: " + motor.getEncoderRight());
		
		motor.updateSpeed(GearType.first);
		Thread.sleep(3000);
		System.out.println("Encoder Left: " + motor.getEncoderLeft());
		System.out.println("Encoder Right: " + motor.getEncoderRight());
		
	}

}
