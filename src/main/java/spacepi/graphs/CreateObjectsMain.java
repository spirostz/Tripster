package spacepi.graphs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import spacepi.model.map.RouteInitial;
import spacepi.model.map.RouteMap;
import spacepi.model.map.RoutePoint;
import spacepi.model.map.RouteReference;

public class CreateObjectsMain {

	public static Gson gson = new Gson();

	public static void main(String[] args) throws IOException {

		RouteInitial routeInitial = gson.fromJson(CreatePointModel.getSampleMapAsJson(), RouteInitial.class);

		//System.out.println(gson.toJson(routeInitial));
		System.out.println(gson.toJson(dijkstra(routeInitial.getRouteMaps().get("testMap"), "P1")));
	}

	public static Map<RoutePoint, Double> dijkstra(RouteMap routeMap, String initialPointId) {

		int nodeCount = routeMap.getRoutePoints().size();

		List<RoutePoint> exploredPoints = new ArrayList<>();
		exploredPoints.add(routeMap.getRoutePoints().get(initialPointId));

		Map<RoutePoint, Double> distancesMap = new HashMap<>();
		distancesMap.put(routeMap.getRoutePoints().get(initialPointId), 0.0);

		Map<String, String> previous = new HashMap<>();
		
		while (nodeCount != exploredPoints.size()) {
			double minimum = Double.POSITIVE_INFINITY;
			RoutePoint tempPoint = null;
			RoutePoint tempPrevious = null;

			for (RoutePoint routePoint : exploredPoints) {
				for (Entry<String, RouteReference> entry : routePoint.getReferencePoints().entrySet()) {

					RoutePoint point = routeMap.getRoutePoints().get(entry.getKey());
					double distance = entry.getValue().getDistance();

					if (!exploredPoints.contains(point)) {
						if (distancesMap.get(routePoint) + distance < minimum) {
							minimum = distancesMap.get(routePoint) + distance;
							tempPoint = point;
							tempPrevious = routePoint;
						}
					}
				}
			}
			
			previous.put(tempPoint.getUniqueId(), tempPrevious.getUniqueId());
			exploredPoints.add(tempPoint);
			distancesMap.put(tempPoint, minimum);
		}

		System.out.println(gson.toJson(previous));
		
		return distancesMap;
	}

}
