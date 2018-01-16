/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3464.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

public class Autonomous extends CommandGroup {
	private String gsm;
	public void setGSM(String gsm) {
		this.gsm = gsm;
	}

	public Autonomous(RobotMap.Point startPoint) {
		if(gsm.length()==3)
		switch(startPoint) {
			case A:
				SensorInput.setPosition(RobotMap.field.get(RobotMap.Point.A));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.D)));
				break;
			case B:
				SensorInput.setPosition(RobotMap.field.get(RobotMap.Point.B));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.E)));
				break;
			case C:
				SensorInput.setPosition(RobotMap.field.get(RobotMap.Point.C));
				addSequential(new Goto(RobotMap.field.get(RobotMap.Point.F)));
				break;
		default:
			break;
		}
	}

	@Override
	protected void initialize() {
		setGSM(Robot.ds.getGameSpecificMessage());
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
