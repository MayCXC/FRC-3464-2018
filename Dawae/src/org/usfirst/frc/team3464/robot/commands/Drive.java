package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;
import java.util.function.DoubleUnaryOperator;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Drive extends Move { // Use driveline to move robot based on any input, the timer or encoders for example
	private double angle, start; // Starting angle-distance vector
	private DoubleUnaryOperator speed; // Changes ESC voltage
	private DoubleSupplier rotation; // Changes rotation speed

    public Drive(DoubleSupplier odometer, double distance, DoubleUnaryOperator speed) { // Move at a given speed for a given distance, duration, etc.
    	super(odometer, distance);
    	this.speed = speed;
    	this.rotation = () -> Robot.driveLine.zRotation(angle);
    }

    @Override
    protected void initialize() { // Read when command starts
    	this.start = RobotMap.timer.get();
    	this.angle = RobotMap.gyro.getAngle();
    	super.initialize();
    }

    @Override
    protected double getSpeed() { // Evaluate speed function
    	return speed.applyAsDouble(RobotMap.timer.get() - start);    	
    }

    @Override
    protected double getZRotation() { // Correct for terrain or damage to keep driveline on course
    	return rotation.getAsDouble();        
    }

    @Override
    protected boolean isFinished() { // Stop when destination is reached
		return progress.getAsDouble() >= finished;
    }
}
