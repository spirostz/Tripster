package spacepi.demonstration;

import static spark.Spark.post;

import java.io.IOException;

import com.google.gson.Gson;

import spacepi.model.CommandRQ;
import spacepi.model.CommandType;
import spacepi.model.InfoRS;
import spacepi.movement.PlanRoute;
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
		InfoRS response = new InfoRS();
		if (commandRq.getCommandType().equals(CommandType.Move)){
			move(commandRq.getPlanRoute());
		}
		else{
			walk(commandRq.getCommandType());
			response.setCommandTypeCalled(commandRq.getCommandType());
			loadStats(response);
			//////////////////////////////
			System.out.println(response);
			//////////////////////////////
		}
		return response;
	}

	private static void move(PlanRoute planRoute) {
		System.out.println(planRoute);
	}
	
	private static void walk(CommandType commandType) throws IOException, InterruptedException {

		switch (commandType) {
		case Front:
			motor.setSpeedToBothMotor(175);
			break;

		case Back:
			motor.setSpeedToBothMotor(31);
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

		case Read:
			break;

		default:
			motor.setSpeedToBothMotor(128);
			break;
		}
	}

	private static InfoRS loadStats(InfoRS infoRs) throws IOException {
		if (infoRs == null) {
			infoRs = new InfoRS();
		};
		infoRs.setBatteryVolts(motor.readBatteryStatus());
		infoRs.setEncoderLeftDistance(motor.getEncoderLeft());
		infoRs.setEncoderRightDistance(motor.getEncoderRight());
		infoRs.setLeftMotorAmps(motor.readLeftMotorCurrent());
		infoRs.setRightMotorAmps(motor.readRightMotorCurrent());
		return infoRs;
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
