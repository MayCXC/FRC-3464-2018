package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Drive extends Move {
	private double angle;
	private DoubleSupplier l, r;

    public Drive(DoubleSupplier odometer, double distance, double speed) {
    	super(Robot.dl.drive::arcadeDrive, odometer, odometer.getAsDouble() + distance);
    	this.l = () -> speed;
    	this.r = () -> Robot.dl.zRotation(angle);
    }

    public Drive() {
    	super(Robot.dl.drive::tankDrive, () -> 0.0, 0.0);
    	this.l = Robot.oi.leftStick::getX;
    	this.r = Robot.oi.rightStick::getX;
    }

    @Override
    protected void initialize() {
    	this.angle = RobotMap.gyro.getAngle();
    }

    @Override
    protected double left() {
    	return l.getAsDouble();
    }

    @Override
    protected double right() {
    	return r.getAsDouble();
    }

	@Override
	protected void end() {
	}
}
