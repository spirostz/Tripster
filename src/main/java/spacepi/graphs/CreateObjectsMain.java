package spacepi.graphs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

import spacepi.model.map.RouteInitial;
import spacepi.model.map.enums.RouteDirectionType;
import spacepi.movement.PathHelper;

public class CreateObjectsMain {

	public static Gson gson = new Gson();

	public static void main(String[] args) throws IOException {

		//RouteInitial routeInitial = gson.fromJson(CreatePointModel.getSampleMapAsJson(), RouteInitial.class);

	    String content = new String(Files.readAllBytes(Paths.get("/home/stzoras/projects/maps.json")));
		RouteInitial routeInitial = gson.fromJson(content, RouteInitial.class);
		
		//System.out.println(gson.toJson(routeInitial));
		//System.out.println(gson.toJson(PathHelper.dijkstra(routeInitial.getRouteMaps().get("testMap"), "P1")));
		//System.out.println(PathHelper.shortestPath(routeInitial.getRouteMaps().get("testMap"),  "P4", "P3"));
		System.out.println(PathHelper.movementRoute(routeInitial.getRouteMaps().get("testMap"),  "P4", "P3", RouteDirectionType.NORTH));
		
		//System.out.println(CreatePointModel.getSampleMapAsJson());
	}

	
	
	
	

}
