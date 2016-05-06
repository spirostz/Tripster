package spacepi.model;

import java.io.Serializable;

public class CommandRQ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1990739926972127633L;
	
	private CommandType commandType;
	private Integer leftMotorSpeed;
	private Integer rightMotorSpeed;
	
	public CommandRQ(CommandType commandType) {
		super();
		this.commandType = commandType;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	@Override
	public String toString() {
		return "CommandRQ [commandType=" + commandType + "]";
	}

	public Integer getLeftMotorSpeed() {
		return leftMotorSpeed;
	}

	public void setLeftMotorSpeed(Integer leftMotorSpeed) {
		this.leftMotorSpeed = leftMotorSpeed;
	}

	public Integer getRightMotorSpeed() {
		return rightMotorSpeed;
	}

	public void setRightMotorSpeed(Integer rightMotorSpeed) {
		this.rightMotorSpeed = rightMotorSpeed;
	}
	
}
