package spacepi.rest;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import spacepi.model.CommandRQ;
import spacepi.model.CommandType;
import spacepi.model.InfoRS;

public class RestClient {
	public static void main(String[] args) throws UnirestException {
		send();
	}

	public static void send() throws UnirestException {
		Gson gson = new Gson();
		String jsonRq = gson.toJson(new CommandRQ(CommandType.Front));

		// Object to Json
		HttpResponse<String> postResponse = Unirest.post("http://localhost:4567/command")
				.header("accept", "application/json").header("Content-Type", "application/json").body(jsonRq)
				.asString();

		
		InfoRS responseMessage = gson.fromJson(postResponse.getBody(), InfoRS.class);
		System.out.println(responseMessage);
	}
}
