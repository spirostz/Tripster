package spacepi.model.map;

import java.io.Serializable;
import java.util.List;

public class RouteMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 765463181543480171L;

	List<RoutePoint> routePoints;

	public List<RoutePoint> getRoutePoints() {
		return routePoints;
	}

	public void setRoutePoints(List<RoutePoint> routePoints) {
		this.routePoints = routePoints;
	}

	@Override
	public String toString() {
		return "RouteMap [routePoints=" + routePoints + "]";
	}

}
