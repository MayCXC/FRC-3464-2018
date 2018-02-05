package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Turn extends Move {
	private double extra = 0.0;
	
    public Turn(DoubleSupplier compass, double angle) {
    	super(compass, angle);
    }

	@Override
	protected double getSpeed() {
		return 0.0;
	}

	@Override
	protected double getZRotation() {
		double z = Robot.driveLine.zRotation(finished);
		return z + Math.copySign(extra, z);
	}
	
	@Override
	protected boolean isFinished() {
		double cut = 1.0, pma = 1.0;

		if(Math.abs( progress.getAsDouble() - finished ) <= pma) {
			if(Math.abs( RobotMap.gyro.getRate() ) <= cut)
				return true;
			extra = 0.0;
		}
		else if(Math.abs( RobotMap.gyro.getRate() ) <= cut)
			extra += .01;

		return false;
	}
}