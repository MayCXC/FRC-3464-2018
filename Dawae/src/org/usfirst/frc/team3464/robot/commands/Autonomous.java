package org.usfirst.frc.team3464.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Autonomous extends CommandGroup {
	private String gsm = "";

	public Autonomous(char start) {
		if(gsm.length()==3 || true)
		switch(start) {
		default:
			addSequential(new Drive(RobotMap.timer::get, 5.0) );
			break;
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
