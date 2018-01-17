package org.usfirst.frc.team3464.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

public class Autonomous extends CommandGroup {
	private String gsm;

	public Autonomous(RobotMap.Point start) {
		if(gsm.length()==3)
		switch(start) {
			case A:
				SensorInput.movePosition(RobotMap.field.get(RobotMap.Point.A));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.D)));
				break;
			case B:
				SensorInput.movePosition(RobotMap.field.get(RobotMap.Point.B));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.E)));
				break;
			case C:
				SensorInput.movePosition(RobotMap.field.get(RobotMap.Point.C));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.F)));
				break;
			default:
				break;
		}
	}

	@Override
	protected void initialize() {
		gsm = Robot.ds.getGameSpecificMessage();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
