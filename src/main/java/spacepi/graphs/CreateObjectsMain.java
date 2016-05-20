package spacepi.graphs;

import java.io.IOException;

import com.google.gson.Gson;

import spacepi.model.map.RouteInitial;
import spacepi.model.map.enums.RouteDirectionType;
import spacepi.movement.PathHelper;

public class CreateObjectsMain {

	public static Gson gson = new Gson();

	public static void main(String[] args) throws IOException {

		RouteInitial routeInitial = gson.fromJson(CreatePointModel.getSampleMapAsJson(), RouteInitial.class);

		//System.out.println(gson.toJson(routeInitial));
		//System.out.println(gson.toJson(PathHelper.dijkstra(routeInitial.getRouteMaps().get("testMap"), "P1")));
		//System.out.println(PathHelper.shortestPath(routeInitial.getRouteMaps().get("testMap"),  "P4", "P3"));
		System.out.println(PathHelper.movementRoute(routeInitial.getRouteMaps().get("testMap"),  "P4", "P3", RouteDirectionType.NORTH));
		
	}

	
	
	
	

}
