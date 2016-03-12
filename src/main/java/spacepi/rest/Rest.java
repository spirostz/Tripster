package spacepi.rest;

import static spark.Spark.*;

import spacepi.rest.transformer.JsonTransformer;

public class Rest {
	public static void main(String[] args) {

		listen();
	}

	//non blocking
	//POST a json to http://localhost:4567/command
	public static void listen() {
		post("/command", "application/json", (request, response) -> {
			System.out.println(request.body());

			return new MyMessage("Hello World");
		} , new JsonTransformer());
	}

}