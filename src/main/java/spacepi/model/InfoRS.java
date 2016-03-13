package spacepi.model;

import java.io.Serializable;

public class InfoRS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1713314764101414391L;

	private String infoText;

	public InfoRS(String infoText) {
		this.infoText = infoText;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	@Override
	public String toString() {
		return "InfoRS [infoText=" + infoText + "]";
	}

	
}
