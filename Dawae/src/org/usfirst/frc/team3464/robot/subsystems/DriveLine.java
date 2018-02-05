package org.usfirst.frc.team3464.robot.subsystems;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.commands.Teleoperated;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveLine extends Subsystem {	
	public double zRotation(double angle) {
		double
			rad = (angle-RobotMap.gyro.getAngle()) * Math.PI / 180.0,
			radnorm = 2.0 / Math.PI,
			attack = 8.0;
			// defence = .45;
		return
			Math.asin( Math.atan( Math.tan( rad/2.0 ) * attack ) * radnorm ) * radnorm;
			// Math.atan( 1.0 / Math.tan( rad/2.0 ) ) * radnorm * defence;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Teleoperated(Robot.operatorInterface.teleTank));
	}
}
