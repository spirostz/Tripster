package spacepi.model.map.physical;

import java.io.Serializable;

import spacepi.model.map.enums.BasicDirectionType;
import spacepi.model.map.enums.DistanceUnitType;
import spacepi.model.map.enums.RouteDirectionType;

public class Movement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4299849068278405991L;

	String currentPointId;
	double distanceToNextPoint;
	BasicDirectionType directionType;

	String nextPointId;

	String finalPointId;

	public String getCurrentPointId() {
		return currentPointId;
	}

	public void setCurrentPointId(String currentPointId) {
		this.currentPointId = currentPointId;
	}

	public double getDistanceToNextPoint() {
		return distanceToNextPoint;
	}

	public void setDistanceToNextPoint(double distanceToNextPoint) {
		this.distanceToNextPoint = distanceToNextPoint;
	}

	public String getNextPointId() {
		return nextPointId;
	}

	public void setNextPointId(String nextPointId) {
		this.nextPointId = nextPointId;
	}

	public String getFinalPointId() {
		return finalPointId;
	}

	public void setFinalPointId(String finalPointId) {
		this.finalPointId = finalPointId;
	}

	public BasicDirectionType getDirectionType() {
		return directionType;
	}

	public void setDirectionType(BasicDirectionType directionType) {
		this.directionType = directionType;
	}

	@Override
	public String toString() {
		return "Movement [currentPointId=" + currentPointId + ", distanceToNextPoint=" + distanceToNextPoint
				+ ", directionType=" + directionType + ", nextPointId=" + nextPointId + ", finalPointId=" + finalPointId
				+ "]";
	}
	
}
