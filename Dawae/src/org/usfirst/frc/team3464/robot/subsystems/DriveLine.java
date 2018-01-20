package org.usfirst.frc.team3464.robot.subsystems;

import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveLine extends Subsystem {
	public DifferentialDrive drive = RobotMap.drive;

	public double zRotation(double angle) {
		double
			rad = (angle-RobotMap.gyro.getAngle()) * Math.PI / 180.0,
			radnorm = 2.0 / Math.PI,
			attack = 16.0;
		return Math.asin( Math.atan( Math.tan( rad/2 ) * attack ) * radnorm ) * radnorm;
	}

	@Override
	protected void initDefaultCommand() {
		// setDefaultCommand(new Drive());
	}
}
