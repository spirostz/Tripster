package spacepi.model;

import java.io.Serializable;

import spacepi.movement.PlanRoute;

public class CommandRQ implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1990739926972127633L;
	
	private CommandType commandType;
	private PlanRoute planRoute;
	
	public CommandRQ(CommandType commandType) {
		this.commandType = commandType;
	}

	public CommandType getCommandType() {
		return commandType;
	}

	public void setCommandType(CommandType commandType) {
		this.commandType = commandType;
	}

	public PlanRoute getPlanRoute() {
		return planRoute;
	}

	public void setPlanRoute(PlanRoute planRoute) {
		this.planRoute = planRoute;
	}

	@Override
	public String toString() {
		return "CommandRQ [commandType=" + commandType + ", planRoute=" + planRoute + "]";
	}
	
}
