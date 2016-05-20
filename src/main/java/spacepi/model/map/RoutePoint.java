package spacepi.model.map;

import java.io.Serializable;
import java.util.Map;

public class RoutePoint implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4325072167622414101L;

	String uniqueId;
	Map<String, RouteReference> referencePoints;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Map<String, RouteReference> getReferencePoints() {
		return referencePoints;
	}

	public void setReferencePoints(Map<String, RouteReference> referencePoints) {
		this.referencePoints = referencePoints;
	}

	@Override
	public String toString() {
		return "RoutePoint [uniqueId=" + uniqueId + ", referencePoints=" + referencePoints + "]";
	}

}
