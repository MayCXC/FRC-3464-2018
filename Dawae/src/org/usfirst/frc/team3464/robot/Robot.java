package org.usfirst.frc.team3464.robot;

import org.usfirst.frc.team3464.robot.commands.*;
import org.usfirst.frc.team3464.robot.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends TimedRobot {
	public static DriveLine dl;
	public static CubeLift cl;
	public static OI oi;
	public static DriverStation ds;
	public static SendableChooser<Command> ac;
	Command ai = null;
	@Override
	public void robotInit() {
		dl = new DriveLine();
		cl = new CubeLift();
		oi = new OI();
		ds = DriverStation.getInstance();

		ac = new SendableChooser<>();
		ac.addDefault("Left", new Autonomous('A'));
		ac.addObject("Middle", new Autonomous('B'));
		ac.addObject("Right", new Autonomous('C'));
		SmartDashboard.putData("Starting position", ac);
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		RobotMap.gyro.calibrate();
		ai = ac.getSelected();
		if (ai != null) ai.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (ai != null) ai.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		dl.getDrive().tankDrive(oi.leftStick.getX(), oi.rightStick.getX());
	}

	@Override
	public void testPeriodic() {
	}
}
