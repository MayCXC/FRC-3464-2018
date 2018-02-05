package org.usfirst.frc.team3464.robot;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.usfirst.frc.team3464.robot.commands.*;
import org.usfirst.frc.team3464.robot.subsystems.*;

import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static DriveLine driveLine;
	public static CubeLift cubeLift;
	public static OperatorInterface operatorInterface;
	public static DriverStation driverStation;

	public SendableChooser<Command> autoChooser, teleChooser;
	public Command autoCommand = null, teleCommand = null;

	@Override
	public void robotInit() {
		driveLine = new DriveLine();
		cubeLift = new CubeLift();
		operatorInterface = new OperatorInterface();
		driverStation = DriverStation.getInstance();
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Left", new Autonomous('A'));
		autoChooser.addObject("Middle", new Autonomous('B'));
		autoChooser.addObject("Right", new Autonomous('C'));
		SmartDashboard.putData("Autonomous", autoChooser);
		teleChooser = new SendableChooser<>();
		teleChooser.addDefault("Tank", new Teleoperated(operatorInterface.teleTank));
		teleChooser.addObject("Arcade", new Teleoperated(operatorInterface.teleArcade));
		SmartDashboard.putData("Teleoperated", teleChooser);

		new Thread( () -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			CvSink sink = CameraServer.getInstance().getVideo();
			CvSource source = CameraServer.getInstance().putVideo("FPV", 640, 480);
			Mat sinkMat = new Mat(), sourceMat = new Mat();
			while( !Thread.interrupted() ) {
				sink.grabFrame(sourceMat);
				Core.flip(sourceMat, sinkMat, -1);
				source.putFrame(sinkMat);
			}
		} ).start();
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
		autoCommand = autoChooser.getSelected();
		if (autoCommand != null) autoCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
		teleCommand = teleChooser.getSelected();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		teleCommand = teleChooser.getSelected();
		if (teleCommand != null) driveLine.setDefaultCommand(teleCommand);
	}
}
