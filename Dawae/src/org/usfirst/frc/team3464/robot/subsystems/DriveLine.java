package org.usfirst.frc.team3464.robot.subsystems;

import java.util.function.Consumer;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveLine extends Subsystem { // Controls robot movement
	public double zRotation(double angle) { // Utility function to find shortest path between two between angles with variable smoothness
		double
			rad = (angle-RobotMap.gyro.getAngle()) * Math.PI / 180.0,
			radnorm = 2.0 / Math.PI,
			attack = 35.0;
			// defense = .45;
		return
			Math.asin( Math.atan( Math.tan( rad/2.0 ) * attack ) * radnorm ) * radnorm;
			// Math.atan( 1.0 / Math.tan( rad/2.0 ) ) * radnorm * defense;
	}

	private double idRange(double n) { // Utility function to clamp inputs between -1,1
		return n > 1.0 ? 1.0 : n < -1.0 ? -1.0 : n;
	}

	public void arcade(double y, double z) { // Arcade controls, implemented as tank controls
		RobotMap.drive.tankDrive(idRange( y + z ), idRange( y - z ));
	}

	public Consumer<Void> teleTank = (Void) -> { // Tank drive
		/** TOKYO DRIFT
		double
			l = Robot.operatorInterface.leftStick.getTrigger() ? 0.0 : Robot.operatorInterface.leftStick.getY(),
			ls = 1.0 - Robot.operatorInterface.leftStick.getRawAxis(3),
			r = Robot.operatorInterface.rightStick.getTrigger() ? 0.0 : Robot.operatorInterface.rightStick.getY(),
			rs = 1.0 - Robot.operatorInterface.rightStick.getRawAxis(3);
			RobotMap.drive.tankDrive(l*Math.abs(l)*ls,r*Math.abs(r)*rs);
		**/
		double ls = -Robot.operatorInterface.leftStick.getY(), rs = -1*Robot.operatorInterface.rightStick.getY();
		double l = ls, r = rs;
		
		if(Robot.operatorInterface.leftStick.getTrigger()) r += ls;
		if(Robot.operatorInterface.rightStick.getTrigger()) l += rs;

		RobotMap.drive.tankDrive(l*Math.abs(l), r*Math.abs(r));
	};

	public Consumer<Void> teleTank180 = (Void) -> { // Reverse tank drive for backing up
		double ls =.6*Robot.operatorInterface.leftStick.getY(), rs = -.6*Robot.operatorInterface.rightStick.getY();
		double l = ls, r = rs;
		
		if(Robot.operatorInterface.leftStick.getTrigger()) r += ls;
		if(Robot.operatorInterface.rightStick.getTrigger()) l += rs;

		RobotMap.drive.tankDrive(r*Math.abs(r), l*Math.abs(l));
	};

	public Consumer<Void> teleArcade = (Void) -> { // Arcade drive
		double
			y = Robot.operatorInterface.leftStick.getY(),
			z = Robot.operatorInterface.leftStick.getZ(),
			s = 1.0 - Robot.operatorInterface.rightStick.getRawAxis(3);
			Robot.driveLine.arcade(y*s,z*s);
	};

	@Override
	protected void initDefaultCommand() {		
	}
}
