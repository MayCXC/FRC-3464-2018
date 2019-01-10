package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Drive extends Move {
	private double angle, start;
	private DoubleUnaryOperator speed;
	private DoubleSupplier rotation;

    public Drive(DoubleSupplier odometer, double distance, DoubleUnaryOperator speed) {
    	super(odometer, distance);
    	this.speed = speed;
    	this.rotation = () -> Robot.driveLine.zRotation(angle);
    }

    @Override
    protected void initialize() {
    	this.start = RobotMap.timer.get();
    	this.angle = RobotMap.gyro.getAngle();
    	super.initialize();
    }

    @Override
    protected double getSpeed() {
    	return speed.applyAsDouble(RobotMap.timer.get() - start);    	
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