package spacepi.model.map;

import java.io.Serializable;

import spacepi.model.map.enums.DistanceUnitType;
import spacepi.model.map.enums.RouteDirectionType;

public class RouteReference implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -829912803632453811L;

	RouteDirectionType routeDirectionType;
	double distance;
	DistanceUnitType distanceUnit;
	String name;

	public RouteReference() {
		super();
	}

	public RouteReference(RouteDirectionType routeDirectionType, double distance, DistanceUnitType distanceUnit,
			String name) {
		super();
		this.routeDirectionType = routeDirectionType;
		this.distance = distance;
		this.distanceUnit = distanceUnit;
		this.name = name;
	}

	public RouteDirectionType getRouteDirectionType() {
		return routeDirectionType;
	}

	public void setRouteDirectionType(RouteDirectionType routeDirectionType) {
		this.routeDirectionType = routeDirectionType;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public DistanceUnitType getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(DistanceUnitType distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RouteReference [routeDirectionType=" + routeDirectionType + ", distance=" + distance + ", distanceUnit="
				+ distanceUnit + ", name=" + name + "]";
	}

}
