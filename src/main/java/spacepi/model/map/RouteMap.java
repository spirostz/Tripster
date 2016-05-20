package spacepi.model.map;

import java.io.Serializable;
import java.util.Map;

public class RouteMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 765463181543480171L;

	Map<String, RoutePoint> routePoints;

	public Map<String, RoutePoint> getRoutePoints() {
		return routePoints;
	}

	public void setRoutePoints(Map<String, RoutePoint> routePoints) {
		this.routePoints = routePoints;
	}

	@Override
	public String toString() {
		return "RouteMap [routePoints=" + routePoints + "]";
	}

}
