package spacepi.demonstration;

import java.io.IOException;

public class SmallWalk {

	final static MotorDriver motor = new MotorDriver();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		motor.init();
		
		
		walkForXDistance(7990);
		
		turnRight();
		
		walkForXDistance(6610);
		
		turnRight();
		
		walkForXDistance(13700);
		
		turnRight();
		
		walkForXDistance(5400);
		
		turnRight();
		
		walkForXDistance(5300);
		
		
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
