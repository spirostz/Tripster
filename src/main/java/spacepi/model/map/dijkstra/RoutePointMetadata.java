package spacepi.model.map.dijkstra;

import java.io.Serializable;

public class RoutePointMetadata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6727167552210348199L;

	String currentPointId;

	String initilPointId;
	String previousPointId;
	double distanceFromInitial;

	public RoutePointMetadata() {
		super();
	}

	public RoutePointMetadata(String currentPointId, String initilPointId, String previousPointId,
			double distanceFromInitial) {
		super();
		this.currentPointId = currentPointId;
		this.initilPointId = initilPointId;
		this.previousPointId = previousPointId;
		this.distanceFromInitial = distanceFromInitial;
	}

	public String getCurrentPointId() {
		return currentPointId;
	}

	public void setCurrentPointId(String currentPointId) {
		this.currentPointId = currentPointId;
	}

	public String getInitilPointId() {
		return initilPointId;
	}

	public void setInitilPointId(String initilPointId) {
		this.initilPointId = initilPointId;
	}

	public String getPreviousPointId() {
		return previousPointId;
	}

	public void setPreviousPointId(String previousPointId) {
		this.previousPointId = previousPointId;
	}

	public double getDistanceFromInitial() {
		return distanceFromInitial;
	}

	public void setDistanceFromInitial(double distanceFromInitial) {
		this.distanceFromInitial = distanceFromInitial;
	}

	@Override
	public String toString() {
		return "RoutePointMetadata [currentPointId=" + currentPointId + ", initilPointId=" + initilPointId
				+ ", previousPointId=" + previousPointId + ", distanceFromInitial=" + distanceFromInitial + "]";
	}

}
