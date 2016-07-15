package spacepi.demonstration;

import static spark.Spark.post;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import spacepi.model.CommandRQ;
import spacepi.model.CommandType;
import spacepi.model.InfoRS;
import spacepi.model.map.RouteInitial;
import spacepi.model.map.RouteMap;
import spacepi.model.map.physical.Movement;
import spacepi.movement.PathHelper;
import spacepi.movement.PlanRoute;
import spacepi.rest.transformer.JsonTransformer;

public class RC {

	final static MotorDriver motor = new MotorDriver();

	public static Gson gson = new Gson();

	static RouteInitial routeInitial;

	public static void main(String[] args) throws IOException {
		String content = new String(Files.readAllBytes(Paths.get("/home/pi/projects/Tripster/maps.json")));
		routeInitial = gson.fromJson(content, RouteInitial.class);
		motor.init();
		listen();
	}

	public static void listen() {
		Gson gson = new Gson();

		post("/command", "application/json", (request, response) -> {
			CommandRQ commandRq = gson.fromJson(request.body(), CommandRQ.class);
			return action(commandRq);
		}, new JsonTransformer());
	}

	public static InfoRS action(CommandRQ commandRq) throws IOException, InterruptedException {
		InfoRS response = new InfoRS();
		if (commandRq.getCommandType().equals(CommandType.Move)) {
			move(commandRq.getPlanRoute());
		} else {
			walk(commandRq.getCommandType());
			response.setCommandTypeCalled(commandRq.getCommandType());
			loadStats(response);
			//////////////////////////////
			System.out.println(response);
			//////////////////////////////
		}
		return response;
	}

	private static void move(PlanRoute planRoute) throws IOException, InterruptedException {
		RouteMap map = routeInitial.getRouteMaps().get(planRoute.getMapName());

		List<Movement> moves = PathHelper.movementRoute(map, planRoute.getInitialPointId(), planRoute.getFinalPointId(),
				planRoute.getCurrentDirection());
		moves.forEach(move -> {
			System.out.println("---------------------------------------");
			System.out.println(move);
		});

		for (Movement movement : moves) {
			movingToNode(movement);
			Thread.sleep(1000);
		}
	}

	private static void movingToNode(Movement move) throws IOException, InterruptedException {

		switch (move.getDirectionType()) {
		case FRONT:
			break;
		case LEFT:
			System.out.println("LEFT: " + 1);
			turnLeft();
			break;
		case RIGHT:
			System.out.println("RIGHT: " + 1);
			turnRight();
			break;
		case BACK:
			System.out.println("BACK: " + 1);
			turnRight();
			System.out.println("BACK: " + 2);
			turnRight();
			break;

		default:
			break;
		}

		System.out.println("AHEAD>>> : " + move.getDistanceToNextPoint());

		Thread.sleep(1000);
		motor.resetEncoders();

		long timer = 1000;
		while (true) {
			if (motor.getEncoderLeft() <= move.getDistanceToNextPoint()) {
				if (timer >= 1000) {
					motor.setSpeedToBothMotor(175);
					timer = 0;
				}
			} else {
				motor.setSpeedToBothMotor(128); // stop
				break;
			}
			timer += 10;
			Thread.sleep(10);
		}
		motor.setSpeedToBothMotor(128); // stop
	}

	private static void walk(CommandType commandType) throws IOException, InterruptedException {

		switch (commandType) {
		case Front:
			motor.setSpeedToBothMotor(175);
			break;

		case Back:
			motor.setSpeedToBothMotor(70);
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
		}
		;
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
			motor.turnArroundRight();
			Thread.sleep(10);
		}
		motor.setSpeedToBothMotor(128); // stop
	}

	private static void turnLeft() throws IOException, InterruptedException {
		Thread.sleep(1000);
		motor.resetEncoders();

		while (motor.getEncoderRight() <= 325) {
			motor.turnArroundLeft();
			Thread.sleep(10);
		}
		motor.setSpeedToBothMotor(128); // stop
	}
}
