package org.usfirst.frc.team3464.robot.subsystems;

import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveLine extends Subsystem {
	public DifferentialDrive getDrive() {
		return RobotMap.drive;
	}

	public double zRotation(double angle) {
		return Math.atan( Math.tan( (angle-RobotMap.gyro.getAngle()) * Math.PI / 180.0 ) ) * 2 / Math.PI;
	}

	@Override
	protected void initDefaultCommand() {	}
}
