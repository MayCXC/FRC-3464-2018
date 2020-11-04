package org.usfirst.frc.team3464.robot.commands;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class Grab extends InstantCommand { // Open and close the claw
	private boolean grip;

    public Grab(boolean grip) {
    	requires(Robot.claw);

    	this.grip = grip;
    }

    protected void initialize() {
    	Robot.claw.grabState = grip;
    }

    protected void execute() {
    	RobotMap.leftClaw.set(!grip);
    	RobotMap.rightClaw.set(!grip);
    }
}
