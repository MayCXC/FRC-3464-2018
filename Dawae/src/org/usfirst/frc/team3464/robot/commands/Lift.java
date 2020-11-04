package org.usfirst.frc.team3464.robot.commands;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class Lift extends Command { // Move the lift, could extend Move
	private DigitalInput finished;
	private double speed = 0.0;
	
    public Lift(DigitalInput finished) {
    	requires(Robot.elevator);
    	
    	this.finished = finished;
    }

    protected void initialize() {
    	if(finished.equals(RobotMap.elevatorHigh)) speed = 1.0;
    	else if(finished.equals(RobotMap.elevatorLow)) speed = -1.0;
    }

    protected void execute() {
    	Robot.elevator.set(speed);
    }

    protected boolean isFinished() {
        return finished.get() == false;
    }

    protected void end() {
    	Robot.elevator.set(0.0);
    }

    protected void interrupted() {
    }
}
