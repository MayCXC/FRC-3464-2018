package org.usfirst.frc.team3464.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.subsystems.GPS;

public class Autonomous extends CommandGroup {
	private String gsm;

	public Autonomous(RobotMap.Point start) {
		if(gsm.length()==3)
		switch(start) {
			case A:
				GPS.move(RobotMap.field.get(RobotMap.Point.A));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.D)));
				break;
			case B:
				GPS.move(RobotMap.field.get(RobotMap.Point.B));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.E)));
				break;
			case C:
				GPS.move(RobotMap.field.get(RobotMap.Point.C));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.F)));
				break;
			default: break;
		}
	}

	@Override
	protected void initialize() {
		gsm = Robot.ds.getGameSpecificMessage();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
