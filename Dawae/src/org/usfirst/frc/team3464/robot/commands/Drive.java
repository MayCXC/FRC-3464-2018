package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.subsystems.GPS;

public class Drive extends Move {
	private double startPosition, startAngle;

    public Drive(DoubleSupplier pidGet, double setpoint) {
    	super(pidGet, setpoint+pidGet.getAsDouble());
    }

	protected void initialize() {
        this.startPosition = pidGet.getAsDouble();
        this.startAngle = RobotMap.gyro.getAngle();
		getPIDController().setAbsoluteTolerance(.1);
    }

	protected void end() {
		GPS.move(
			GPS.mul.apply(
				GPS.unit(startAngle),
				pidGet.getAsDouble() - startPosition
		) );
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.dl.driveDD(RobotMap.driveMethodAuto, output, startAngle-RobotMap.gyro.getAngle());
	}
}
