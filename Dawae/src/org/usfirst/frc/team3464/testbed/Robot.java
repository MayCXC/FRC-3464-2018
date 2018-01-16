package org.usfirst.frc.team3464.testbed;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	private DifferentialDrive drive;
	private Joystick stick;
	private Timer timer;
	private Encoder encoder;

	@Override
	public void robotInit() {
		drive = new DifferentialDrive(
			new Spark(RobotMap.leftSpark),
			new Spark(RobotMap.rightSpark)
		);

		stick = new Joystick(RobotMap.joystick);

		timer = new Timer();
		timer.reset();
		timer.start();

		encoder = new Encoder(
				RobotMap.encoderA,
				RobotMap.encoderB
			);
		encoder.setDistancePerPulse(RobotMap.dpp);
		encoder.reset();
	}

	private double autoInitTime;

	@Override
	public void autonomousInit() {
		autoInitTime = timer.get();
	}

	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putNumber("Autonomous Duration", timer.get() - autoInitTime);
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		drive.arcadeDrive(stick.getY(), stick.getX());
	}

	@Override
	public void testPeriodic() {
		drive.arcadeDrive(RobotMap.xSpeed, RobotMap.zRotation);
		SmartDashboard.putNumber("Encoder Pulses k4x", encoder.getDistance());
	}
}
