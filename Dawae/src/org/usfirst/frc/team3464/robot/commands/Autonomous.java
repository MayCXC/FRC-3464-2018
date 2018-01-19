package org.usfirst.frc.team3464.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Autonomous extends CommandGroup {
	private String gsm = "";

	public Autonomous(char start) {
		SmartDashboard.putString("GSM", gsm);
		addSequential(new Drive(RobotMap.timer::get, 5.0, .5) );
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
		return !Robot.ds.isAutonomous();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
