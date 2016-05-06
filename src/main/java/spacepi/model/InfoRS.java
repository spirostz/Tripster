package spacepi.model;

import java.io.Serializable;

public class InfoRS implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1713314764101414391L;

	private CommandType commandTypeCalled;
	private Integer encoderLeftDistance;
	private Integer encoderRightDistance;
	private Double batteryVolts;
	private Double leftMotorAmps;
	private Double rightMotorAmps;

	public Integer getEncoderLeftDistance() {
		return encoderLeftDistance;
	}

	public void setEncoderLeftDistance(Integer encoderLeftDistance) {
		this.encoderLeftDistance = encoderLeftDistance;
	}

	public Integer getEncoderRightDistance() {
		return encoderRightDistance;
	}

	public void setEncoderRightDistance(Integer encoderRightDistance) {
		this.encoderRightDistance = encoderRightDistance;
	}

	public Double getBatteryVolts() {
		return batteryVolts;
	}

	public void setBatteryVolts(Double batteryVolts) {
		this.batteryVolts = batteryVolts;
	}

	public Double getLeftMotorAmps() {
		return leftMotorAmps;
	}

	public void setLeftMotorAmps(Double leftMotorAmps) {
		this.leftMotorAmps = leftMotorAmps;
	}

	public Double getRightMotorAmps() {
		return rightMotorAmps;
	}

	public void setRightMotorAmps(Double rightMotorAmps) {
		this.rightMotorAmps = rightMotorAmps;
	}

	public CommandType getCommandTypeCalled() {
		return commandTypeCalled;
	}

	public void setCommandTypeCalled(CommandType commandTypeCalled) {
		this.commandTypeCalled = commandTypeCalled;
	}

	@Override
	public String toString() {
		return "InfoRS [commandTypeCalled=" + commandTypeCalled + ", encoderLeftDistance=" + encoderLeftDistance
				+ ", encoderRightDistance=" + encoderRightDistance + ", batteryVolts=" + batteryVolts
				+ ", leftMotorAmps=" + leftMotorAmps + ", rightMotorAmps=" + rightMotorAmps + "]";
	}

}
