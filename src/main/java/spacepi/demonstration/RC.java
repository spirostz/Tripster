package spacepi.demonstration;

import static spark.Spark.post;

import java.io.IOException;

import com.google.gson.Gson;

import spacepi.model.CommandRQ;
import spacepi.model.CommandType;
import spacepi.model.InfoRS;
import spacepi.rest.transformer.JsonTransformer;

public class RC {

	final static MotorDriver motor = new MotorDriver();

	public static void main(String[] args) throws IOException {
		motor.init();
		listen();
	}

	public static void listen() {
		Gson gson = new Gson();

		post("/command", "application/json", (request, response) -> {
			CommandRQ commandRq = gson.fromJson(request.body(), CommandRQ.class);
			return action(commandRq);
		} , new JsonTransformer());
	}

	public static InfoRS action(CommandRQ commandRq) throws IOException, InterruptedException {
		String infoText = walk(commandRq.getCommandType());
		System.out.println(commandRq);
		return new InfoRS(infoText);
	}

	private static String walk(CommandType commandType) throws IOException, InterruptedException {

		switch (commandType) {
		case Front:
			motor.setSpeedToBothMotor(255);
			break;

		case Back:
			motor.setSpeedToBothMotor(1);
			break;

		case Left:
			motor.turnArroundLeft();
			break;

		case Right:
			motor.turnArroundRight();
			break;
			
		case Reset:
			motor.resetEncoders();
			break;

		case Stop:
			motor.setSpeedToBothMotor(128);
			break;

		default:
			motor.setSpeedToBothMotor(128);
			break;
		}

		return "Left: " + motor.getEncoderLeft() + " Right: " + motor.getEncoderRight();
	}

	private static void turnRight() throws IOException, InterruptedException {
		Thread.sleep(1000);
		motor.resetEncoders();

		while (motor.getEncoderLeft() <= 325) {
			motor.turnArround();
			Thread.sleep(10);
		}
		motor.setSpeedToBothMotor(128); // stop
	}

}
