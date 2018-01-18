package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

public class Turn extends Move {
    public Turn(DoubleSupplier pidGet, double setpoint) {
    	super(pidGet, setpoint);
    }

    @Override
    protected void initialize() {
		getPIDController().setAbsoluteTolerance(.1);
    }

	@Override
	protected void usePIDOutput(double output) {
		Robot.dl.driveDD(RobotMap.driveMethodAuto,0,output);
	}
}