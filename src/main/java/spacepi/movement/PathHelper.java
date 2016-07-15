package spacepi.movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import spacepi.model.map.RouteMap;
import spacepi.model.map.RoutePoint;
import spacepi.model.map.RouteReference;
import spacepi.model.map.dijkstra.RoutePointMetadata;
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

		Map<String, RoutePointMetadata> allPaths = dijkstra(routeMap, initialPointId);

		List<String> ordererPath = new LinkedList<>();

		String referencePointId = allPaths.get(finalPointId).getCurrentPointId();
		RoutePointMetadata referencePointMetadata = allPaths.get(finalPointId);
		ordererPath.add(0, referencePointId);

		while (!referencePointId.equals(initialPointId)) {
			referencePointId = referencePointMetadata.getPreviousPointId();
			referencePointMetadata = allPaths.get(referencePointId);
			ordererPath.add(0, referencePointId);
		}

		return ordererPath;
	}

	public static Map<String, RoutePointMetadata> dijkstra(RouteMap routeMap, String initialPointId) {

		int nodeCount = routeMap.getRoutePoints().size();

		List<RoutePoint> exploredPoints = new ArrayList<>();
		exploredPoints.add(routeMap.getRoutePoints().get(initialPointId));

		Map<RoutePoint, Double> distancesMap = new HashMap<>();
		distancesMap.put(routeMap.getRoutePoints().get(initialPointId), 0.0);

		Map<String, RoutePointMetadata> responseData = new HashMap<>();

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

			exploredPoints.add(tempPoint);
			distancesMap.put(tempPoint, minimum);

			responseData.put(tempPoint.getUniqueId(), new RoutePointMetadata(tempPoint.getUniqueId(), initialPointId,
					tempPrevious.getUniqueId(), minimum));
		}

		return responseData;
	}
}
