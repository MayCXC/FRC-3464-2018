package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

public class Turn extends Move {
    public Turn(DoubleSupplier compass, double direction) {
    	super(compass, direction);
    }

	@Override
	protected void execute() {
		Robot.dl.getDrive().arcadeDrive(0.0, Robot.dl.zRotation(finished));
	}
}