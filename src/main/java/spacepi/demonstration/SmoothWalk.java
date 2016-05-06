package spacepi.demonstration;

import java.io.IOException;

public class SmoothWalk {

	final static MotorDriver motor = new MotorDriver();
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		motor.init();
		
		
		walkForXDistance(1500);
		
		turnSmoothRight();
		
		walkForXDistance(2860);
		
		stop();
		
		turnRight();
		
		
		turnRight();
		
		walkForXDistance(2860);
		
		turnSmoothLeft();
		
		walkForXDistance(1500);
		
		stop();
		
	}
	
	private static void walkForXDistance(int distance) throws IOException, InterruptedException{
		motor.resetEncoders();		
		
		motor.setSpeedToBothMotor(156);
		
		while ( motor.getEncoderLeft() <= distance ||  motor.getEncoderRight() <= distance){
			Thread.sleep(100);
		}		
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
	
	private static void turnSmoothLeft() throws IOException, InterruptedException{		
		motor.resetEncoders();
		
		while (motor.getEncoderLeft() <= 980){
			motor.setMotorsSpeed(145, 156);
			Thread.sleep(10);
		}
	}
	
	private static void turnSmoothRight() throws IOException, InterruptedException{		
		motor.resetEncoders();
		
		while (motor.getEncoderRight() <= 980){
			motor.setMotorsSpeed(156, 145);
			Thread.sleep(10);
		}
	}
	
	private static void stop() throws IOException, InterruptedException{
		motor.setSpeedToBothMotor(128);
	}
}
