package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Turn extends Move { // Change the direction the robot is facing
	private double extra = 0.0;
	
	public Turn(DoubleSupplier compass, double angle) {
		super(compass, angle);
	}

	@Override
	protected double getSpeed() { // Do not move forewards or backwards
		return 0.0;
	}

	@Override
	protected double getZRotation() { // Turn based on angle difference plus a correction factor
		double z = Robot.driveLine.zRotation(finished);
		return z + Math.copySign(extra, z);
	}
	
	@Override
	protected boolean isFinished() { // Measure progress and stop when we are facing the correct angle
		double // angular velocity and plus or minus angle tolerance
			cut = 1.0,
			pma = 2.0;
		boolean // check if the robot is done turning according progress wise and momentum wise
			oriented = Math.abs( progress.getAsDouble() - finished ) <= pma,
			settled = Math.abs( RobotMap.gyro.getRate() ) <= cut;

		if(oriented) { // Gradually increase throttle if driveline locks up before we reach the correct angle
			if(settled)
				return true;
			extra = 0.0;
		}
		else if(settled) {
			extra += .01;
		}

		return false;
	}
}
