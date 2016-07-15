package spacepi.movement;

import java.io.Serializable;

import spacepi.model.map.enums.RouteDirectionType;

public class PlanRoute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7554231711997754399L;
	
	String mapName;
	String initialPointId;
	String finalPointId;
	RouteDirectionType currentDirection;
	
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public String getInitialPointId() {
		return initialPointId;
	}
	public void setInitialPointId(String initialPointId) {
		this.initialPointId = initialPointId;
	}
	public String getFinalPointId() {
		return finalPointId;
	}
	public void setFinalPointId(String finalPointId) {
		this.finalPointId = finalPointId;
	}
	public RouteDirectionType getCurrentDirection() {
		return currentDirection;
	}
	public void setCurrentDirection(RouteDirectionType currentDirection) {
		this.currentDirection = currentDirection;
	}
	@Override
	public String toString() {
		return "PlanRoute [mapName=" + mapName + ", initialPointId=" + initialPointId + ", finalPointId=" + finalPointId
				+ ", currentDirection=" + currentDirection + "]";
	}
	
	
	
}
