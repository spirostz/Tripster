package spacepi.movement;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import spacepi.model.map.RouteMap;
import spacepi.model.map.RouteReference;
import spacepi.model.map.enums.BasicDirectionType;
import spacepi.model.map.enums.RouteDirectionType;
import spacepi.model.map.physical.Movement;

public class PathHelper {

	public static List<Movement> movementRoute(RouteMap routeMap, String initialPointId, String finalPointId,
			RouteDirectionType currentDirection) {
		List<Movement> movements = new LinkedList<>();
		List<String> shortestPath = shortestPath(routeMap, initialPointId, finalPointId);
		
		for (int i = 0; i < shortestPath.size() - 1; i++) {
			Movement movement = new Movement();
			movement.setCurrentPointId(shortestPath.get(i));
			movement.setNextPointId(shortestPath.get(i + 1));
			movement.setFinalPointId(shortestPath.get(shortestPath.size() - 1));

			RouteReference nextReference = routeMap.getRoutePoints().get(movement.getCurrentPointId())
					.getReferencePoints().get(movement.getNextPointId());

			movement.setDistanceToNextPoint(nextReference.getDistance());

			movement.setDirectionType(findDirection(currentDirection, nextReference.getRouteDirectionType()));
			currentDirection = nextReference.getRouteDirectionType();
			movements.add(movement);
		}

		Movement finalMovement = new Movement();
		finalMovement.setCurrentPointId(shortestPath.get(shortestPath.size() - 1));
		return movements;

	}

	private static BasicDirectionType findDirection(RouteDirectionType currentDirection,
			RouteDirectionType toDirection) {

		// TODO: optimize by doing with maths

		switch (currentDirection) {
		case NORTH:

			switch (toDirection) {
			case NORTH:
				return BasicDirectionType.FRONT;
			case WEST:
				return BasicDirectionType.LEFT;
			case EAST:
				return BasicDirectionType.RIGHT;
			case SOUTH:
				return BasicDirectionType.BACK;
			}

		case WEST:

			switch (toDirection) {
			case NORTH:
				return BasicDirectionType.RIGHT;
			case WEST:
				return BasicDirectionType.FRONT;
			case EAST:
				return BasicDirectionType.BACK;
			case SOUTH:
				return BasicDirectionType.LEFT;
			}

		case EAST:

			switch (toDirection) {
			case NORTH:
				return BasicDirectionType.LEFT;
			case WEST:
				return BasicDirectionType.BACK;
			case EAST:
				return BasicDirectionType.FRONT;
			case SOUTH:
				return BasicDirectionType.RIGHT;
			}

		case SOUTH:

			switch (toDirection) {
			case NORTH:
				return BasicDirectionType.BACK;
			case WEST:
				return BasicDirectionType.RIGHT;
			case EAST:
				return BasicDirectionType.LEFT;
			case SOUTH:
				return BasicDirectionType.FRONT;
			}

		default:
			break;
		}
		return null;

	}

	public static List<String> shortestPath(RouteMap routeMap, String initialPointId, String finalPointId) {

		Map<String, String> previousOneByOne = dijkstra(routeMap, initialPointId);
		List<String> ordererPath = new LinkedList<>();

		String pre = finalPointId;
		ordererPath.add(finalPointId);
		
		while(!pre.equals(initialPointId)){
			pre = previousOneByOne.get(pre);
			ordererPath.add(pre);
		}
		Collections.reverse(ordererPath);

		return ordererPath;
	}

	public static Map<String, String> dijkstra(RouteMap routeMap, String source) {

		Set<String> vertexSet = new HashSet<String>();
		vertexSet.addAll(routeMap.getRoutePoints().keySet());
		
		Map<String, Double> dist = new HashMap<>();
		Map<String, String> prev = new HashMap<>();
		
		for (String vertex : vertexSet) {
			dist.put(vertex, Double.POSITIVE_INFINITY);
			prev.put(vertex, null);
		}
		
		dist.put(source, 0.0);
		
		while (!vertexSet.isEmpty()) {
			String u = PathHelper.minHash(dist, vertexSet);
			vertexSet.remove(u);
			
			for (Entry<String,RouteReference> v : routeMap.getRoutePoints().get(u).getReferencePoints().entrySet()) {
				double alt =dist.get(u) + v.getValue().getDistance();
				if (alt < dist.get(v.getKey())) {
					dist.put(v.getKey(), alt);
					prev.put(v.getKey(), u);
				}
			}
		}
		return prev;

	}
	
	private static String minHash(Map<String, Double> map, Set<String> set) {
		if (set.isEmpty()) {
			return null;
		}
		String minKey = "";
		double minimum = Double.POSITIVE_INFINITY;;
		for (Entry<String, Double> element : map.entrySet()) {
			if (element.getValue() < minimum && set.contains(element.getKey())) {
				minimum = element.getValue();
				minKey = element.getKey(); 
			}
		}
		return minKey;
	}
}
