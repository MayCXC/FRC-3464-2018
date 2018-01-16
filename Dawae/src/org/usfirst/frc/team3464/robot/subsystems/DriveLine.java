package org.usfirst.frc.team3464.robot.subsystems;

import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveLine extends Subsystem {
	public void driveStick(double l, double r) {
		RobotMap.driveMethod.accept(l, r);
	}
	
	public void initDefaultCommand() {
		// setDefaultCommand(new MySpecialCommand());
	}
}
