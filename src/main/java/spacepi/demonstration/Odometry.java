package spacepi.demonstration;

import java.io.IOException;
import java.util.Scanner;

public class Odometry {

	final static MotorDriver motor = new MotorDriver();

	public static void main(String[] args) throws InterruptedException, IOException {
		/*
		 * motor.init(); motor.resetEncoders(); System.out.println(
		 * "Encoder Left: " + motor.getEncoderLeft()); System.out.println(
		 * "Encoder Right: " + motor.getEncoderRight());
		 * 
		 * motor.updateSpeed(GearType.first); Thread.sleep(3000);
		 * System.out.println("Encoder Left: " + motor.getEncoderLeft());
		 * System.out.println("Encoder Right: " + motor.getEncoderRight());
		 * 
		 * motor.setSpeedToBothMotor(128); Thread.sleep(3000);
		 * System.out.println("Encoder Left: " + motor.getEncoderLeft());
		 * System.out.println("Encoder Right: " + motor.getEncoderRight());
		 * 
		 * 
		 * motor.setSpeedToBothMotor(138); Thread.sleep(3000);
		 * System.out.println("Encoder Left: " + motor.getEncoderLeft());
		 * System.out.println("Encoder Right: " + motor.getEncoderRight());
		 * 
		 * motor.setSpeedToBothMotor(118); Thread.sleep(3000);
		 * System.out.println("Encoder Left: " + motor.getEncoderLeft());
		 * System.out.println("Encoder Right: " + motor.getEncoderRight());
		 */

		System.out.print("Enter your sentence: ");
		Scanner sc = new Scanner(System.in);

		motor.init();
		motor.resetEncoders();
		System.out.println("Encoder Left: " + motor.getEncoderLeft());
		System.out.println("Encoder Right: " + motor.getEncoderRight());

		while (sc.hasNext() == true) {
			String s1 = sc.next();

			if (s1.equalsIgnoreCase("w")) {
				motor.setSpeedToBothMotor(200);
			}

			if (s1.equalsIgnoreCase("l")) {
				System.out.println("Encoder Left: " + motor.getEncoderLeft());
			}

			if (s1.equalsIgnoreCase("r")) {
				System.out.println("Encoder Right: " + motor.getEncoderRight());
			}

			if (s1.equalsIgnoreCase("b")) {
				System.out.println("Encoder Left: " + motor.getEncoderLeft());
				System.out.println("Encoder Right: " + motor.getEncoderRight());
			}

			if (s1.equalsIgnoreCase("t")) {
				motor.turnArround();
			}

			if (s1.equalsIgnoreCase(" ")) {
				motor.resetEncoders();
			}

			if (s1.equalsIgnoreCase("s")) {
				motor.setSpeedToBothMotor(128);
			}
			try {
				turnRight(Integer.parseInt(s1));
			} catch (Exception e) {
			}
			
		}

	}

	private static void turnRight(int howMuch) throws IOException, InterruptedException {
		motor.resetEncoders();

		while (motor.getEncoderLeft() <= howMuch) {
			motor.turnArround();
			Thread.sleep(10);
		}
		motor.setSpeedToBothMotor(135); // stop
		motor.setSpeedToBothMotor(128); // stop
	}

}
