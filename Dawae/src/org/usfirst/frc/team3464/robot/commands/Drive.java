package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Drive extends Move {
	private double angle;
	private DoubleSupplier speed, rotation;

    public Drive(DoubleSupplier odometer, double distance, DoubleSupplier speed) {
    	super(odometer, distance);
    	this.speed = speed;
    	this.rotation = () -> Robot.driveLine.zRotation(angle);
    }

    @Override
    protected void initialize() {
    	super.initialize();
    	this.angle = RobotMap.gyro.getAngle();
    }

    @Override
    protected double getSpeed() {
    	return speed.getAsDouble();    	
    }

    @Override
    protected double getZRotation() {
    	return rotation.getAsDouble();        
    }

    @Override
    protected boolean isFinished() {
		return progress.getAsDouble() >= finished;
    }
}