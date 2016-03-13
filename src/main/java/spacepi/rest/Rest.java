package spacepi.rest;

import static spark.Spark.*;

import com.google.gson.Gson;

import spacepi.model.CommandRQ;
import spacepi.model.InfoRS;
import spacepi.rest.transformer.JsonTransformer;

public class Rest {

	public static void main(String[] args) {
		listen();
	}
	
	// non blocking
	// POST a json to http://localhost:4567/command
	public static void listen() {
		Gson gson = new Gson();
		
		post("/command", "application/json", (request, response) -> {
			CommandRQ commandRq = gson.fromJson(request.body(), CommandRQ.class);
			System.out.println(commandRq);
			return new InfoRS("Gotted!!!");
		} , new JsonTransformer());
	}

}