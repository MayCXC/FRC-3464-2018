package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Drive extends Move {
	private double angle;
	
    public Drive(DoubleSupplier odometer, double distance) {
    	super(odometer, odometer.getAsDouble() + distance);
    }

    @Override
    protected void initialize() {
    	this.angle = RobotMap.gyro.getAngle();
    }
    
    @Override
    protected void execute() {
    	Robot.dl.getDrive().arcadeDrive(.5, Robot.dl.zRotation(angle));
    }
    
	@Override
	protected void end() {
	}
}
