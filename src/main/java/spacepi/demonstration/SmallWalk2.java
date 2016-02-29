package spacepi.demonstration;

import java.io.IOException;

public class SmallWalk2 {

	final static MotorDriver motor = new MotorDriver();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		motor.init();
		
		
		walkForXDistance(8000);
		
		turnRight();
		
		walkForXDistance(1900);
		
		turnRight();
		
		walkForXDistance(8000);
				
		turnRight();
		turnRight();
		
		walkForXDistance(8000);
		
		turnRight();
		turnRight();
		turnRight();
		
		walkForXDistance(1900);
		
		turnRight();
		
		walkForXDistance(8000);

		
		
	}
	
	private static void walkForXDistance(int distance) throws IOException, InterruptedException{
		motor.resetEncoders();
		
		motor.setSpeedToBothMotor(130);
		Thread.sleep(500);
		motor.setSpeedToBothMotor(140);
		Thread.sleep(500);
		motor.setSpeedToBothMotor(160);
		Thread.sleep(500);
		motor.setSpeedToBothMotor(180);
		Thread.sleep(500);
		
		while ( motor.getEncoderLeft() <= distance ||  motor.getEncoderRight() <= distance){
			motor.setSpeedToBothMotor(200); //stop
			Thread.sleep(100);
		}
		motor.setSpeedToBothMotor(128); //stop
	}
	
	private static void turnRight() throws IOException, InterruptedException{
		Thread.sleep(1000);
		motor.resetEncoders();
		
		while (motor.getEncoderLeft() <= 325){
			motor.turnArround();
			Thread.sleep(10);
		}
		motor.setSpeedToBothMotor(128); //stop
	}
	
}
