package spacepi.model.map;

import java.io.Serializable;

import spacepi.model.map.enums.DistanceUnitType;

public class RouteElement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -829912803632453811L;

	String referencePointUniqueId;
	double distance;
	DistanceUnitType distanceUnit;

	public String getReferencePointUniqueId() {
		return referencePointUniqueId;
	}

	public void setReferencePointUniqueId(String referencePointUniqueId) {
		this.referencePointUniqueId = referencePointUniqueId;
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
		return "RouteElement [referencePointUniqueId=" + referencePointUniqueId + ", distance=" + distance
				+ ", distanceUnit=" + distanceUnit + "]";
	}

}
