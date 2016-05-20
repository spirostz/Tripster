package spacepi.graphs;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import spacepi.model.map.RouteReference;
import spacepi.model.map.RouteInitial;
import spacepi.model.map.RouteMap;
import spacepi.model.map.RoutePoint;
import spacepi.model.map.enums.DistanceUnitType;
import spacepi.model.map.enums.RouteDirectionType;

public class CreatePointModel {

	private static Gson gson = new Gson();

	public static void main(String[] args) {

		System.out.println(getSampleMapAsJson());
	}

	public static String getSampleMapAsJson() {
		RouteInitial initial = getAsampleMapOld();

		return gson.toJson(initial);
	}

	
	private static RouteInitial getAsampleMap() {
		RouteInitial initial = new RouteInitial();
		RouteMap testMap = new RouteMap();
		Map<String, RouteMap> routeMaps = new HashMap<>();
		routeMaps.put("testMap1", testMap);
		initial.setRouteMaps(routeMaps);

		RoutePoint routePointP1 = new RoutePoint(); // P1
		routePointP1.setUniqueId("0");
		Map<String, RouteReference> p1RefPoints = new HashMap<>();
		p1RefPoints.put("1", new RouteReference(RouteDirectionType.EAST, 1, DistanceUnitType.MotorClicks));
		p1RefPoints.put("2", new RouteReference(RouteDirectionType.NORTH, 4, DistanceUnitType.MotorClicks));
		routePointP1.setReferencePoints(p1RefPoints);

		RoutePoint routePointP2 = new RoutePoint(); // P2
		routePointP2.setUniqueId("1");
		Map<String, RouteReference> p2RefPoints = new HashMap<>();
		p2RefPoints.put("2", new RouteReference(RouteDirectionType.EAST, 2, DistanceUnitType.MotorClicks));
		p2RefPoints.put("3", new RouteReference(RouteDirectionType.NORTH, 6, DistanceUnitType.MotorClicks));
		p2RefPoints.put("0", new RouteReference(RouteDirectionType.WEST, 1, DistanceUnitType.MotorClicks));
		routePointP2.setReferencePoints(p2RefPoints);

		RoutePoint routePointP3 = new RoutePoint(); // P3
		routePointP3.setUniqueId("2");
		Map<String, RouteReference> p3RefPoints = new HashMap<>();
		p3RefPoints.put("3", new RouteReference(RouteDirectionType.WEST, 2, DistanceUnitType.MotorClicks));
		p3RefPoints.put("1", new RouteReference(RouteDirectionType.EAST, 2, DistanceUnitType.MotorClicks));
		p3RefPoints.put("0", new RouteReference(RouteDirectionType.EAST, 4, DistanceUnitType.MotorClicks));
		routePointP3.setReferencePoints(p3RefPoints);

		RoutePoint routePointP4 = new RoutePoint(); // P4
		routePointP4.setUniqueId("3");
		Map<String, RouteReference> p4RefPoints = new HashMap<>();
		p4RefPoints.put("2", new RouteReference(RouteDirectionType.EAST, 2, DistanceUnitType.MotorClicks));
		p4RefPoints.put("1", new RouteReference(RouteDirectionType.SOUTH, 6, DistanceUnitType.MotorClicks));
		routePointP4.setReferencePoints(p4RefPoints);
		
		
		Map<String, RoutePoint> points = new HashMap<>();
		points.put("0", routePointP1);
		points.put("1", routePointP2);
		points.put("2", routePointP3);
		points.put("3", routePointP4);
		testMap.setRoutePoints(points);
		return initial;
	}
	
	
	private static RouteInitial getAsampleMapOld() {
		RouteInitial initial = new RouteInitial();
		RouteMap testMap = new RouteMap();
		Map<String, RouteMap> routeMaps = new HashMap<>();
		routeMaps.put("testMap", testMap);
		initial.setRouteMaps(routeMaps);

		RoutePoint routePointP1 = new RoutePoint(); // P1
		routePointP1.setUniqueId("P1");
		Map<String, RouteReference> p1RefPoints = new HashMap<>();
		p1RefPoints.put("P2", new RouteReference(RouteDirectionType.EAST, 100, DistanceUnitType.MotorClicks));
		p1RefPoints.put("P4", new RouteReference(RouteDirectionType.NORTH, 30, DistanceUnitType.MotorClicks));
		routePointP1.setReferencePoints(p1RefPoints);

		RoutePoint routePointP2 = new RoutePoint(); // P2
		routePointP2.setUniqueId("P2");
		Map<String, RouteReference> p2RefPoints = new HashMap<>();
		p2RefPoints.put("P3", new RouteReference(RouteDirectionType.EAST, 50, DistanceUnitType.MotorClicks));
		p2RefPoints.put("P5", new RouteReference(RouteDirectionType.NORTH, 60, DistanceUnitType.MotorClicks));
		p2RefPoints.put("P1", new RouteReference(RouteDirectionType.WEST, 100, DistanceUnitType.MotorClicks));
		routePointP2.setReferencePoints(p2RefPoints);

		RoutePoint routePointP3 = new RoutePoint(); // P3
		routePointP3.setUniqueId("P3");
		Map<String, RouteReference> p3RefPoints = new HashMap<>();
		p3RefPoints.put("P2", new RouteReference(RouteDirectionType.WEST, 50, DistanceUnitType.MotorClicks));
		routePointP3.setReferencePoints(p3RefPoints);

		RoutePoint routePointP4 = new RoutePoint(); // P4
		routePointP4.setUniqueId("P4");
		Map<String, RouteReference> p4RefPoints = new HashMap<>();
		p4RefPoints.put("P5", new RouteReference(RouteDirectionType.EAST, 20, DistanceUnitType.MotorClicks));
		p4RefPoints.put("P1", new RouteReference(RouteDirectionType.SOUTH, 30, DistanceUnitType.MotorClicks));
		routePointP4.setReferencePoints(p4RefPoints);
		
		RoutePoint routePointP5 = new RoutePoint(); // P5
		routePointP5.setUniqueId("P5");
		Map<String, RouteReference> p5RefPoints = new HashMap<>();
		p5RefPoints.put("P4", new RouteReference(RouteDirectionType.WEST, 20, DistanceUnitType.MotorClicks));
		p5RefPoints.put("P2", new RouteReference(RouteDirectionType.SOUTH, 60, DistanceUnitType.MotorClicks));
		routePointP5.setReferencePoints(p5RefPoints);

		Map<String, RoutePoint> points = new HashMap<>();
		points.put("P1", routePointP1);
		points.put("P2", routePointP2);
		points.put("P3", routePointP3);
		points.put("P4", routePointP4);
		points.put("P5", routePointP5);
		testMap.setRoutePoints(points);
		return initial;
	}

}
