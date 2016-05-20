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

	public RouteReference() {
		super();
	}

	public RouteReference(RouteDirectionType routeDirectionType, double distance, DistanceUnitType distanceUnit) {
		super();
		this.routeDirectionType = routeDirectionType;
		this.distance = distance;
		this.distanceUnit = distanceUnit;
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

	@Override
	public String toString() {
		return "RouteElement [routeDirectionType=" + routeDirectionType + ", distance=" + distance + ", distanceUnit="
				+ distanceUnit + "]";
	}

}
