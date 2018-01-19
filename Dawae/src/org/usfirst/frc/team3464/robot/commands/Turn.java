package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

public class Turn extends Move {
    public Turn(DoubleSupplier compass, double angle) {
    	super(Robot.dl.drive::arcadeDrive, compass, compass.getAsDouble() + angle);
    }

	@Override
	protected double left() {
		return 0.0;
	}

	@Override
	protected double right() {
		return Robot.dl.zRotation(finished);
	}
}