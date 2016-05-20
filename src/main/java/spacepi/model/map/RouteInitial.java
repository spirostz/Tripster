package spacepi.model.map;

import java.io.Serializable;
import java.util.Map;

public class RouteInitial implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1439148681526156405L;

	Map<String, RouteMap> routeMaps;

	public Map<String, RouteMap> getRouteMaps() {
		return routeMaps;
	}

	public void setRouteMaps(Map<String, RouteMap> routeMaps) {
		this.routeMaps = routeMaps;
	}

	@Override
	public String toString() {
		return "RouteDetails [routeMaps=" + routeMaps + "]";
	}

}
