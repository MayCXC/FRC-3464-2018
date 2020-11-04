package org.usfirst.frc.team3464.robot;

import org.usfirst.frc.team3464.robot.commands.*;
import org.usfirst.frc.team3464.robot.subsystems.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	public static final DriveLine driveLine = new DriveLine(); // Motor subsystem
	public static final Elevator elevator = new Elevator(); // Lift subsystem
	public static final Claw claw = new Claw(); // Arm subsystem

	public static OperatorInterface operatorInterface; // Takes input from joysticks and GUI

	SendableChooser<Autonomous> autoChooser; // GUI selector to pick which autonomous preset to attempt
	Command autoCommand = null;

	SendableChooser<Teleoperated> teleChooser; // GUI selector to pick between tank and arcade controls
	Command teleCommand = null;
	
	SendableChooser<Boolean> killCompressor; // GUI selector to turn compressor off

	@Override
	public void robotInit() { // Instantiate subsystems and connect to GUI
		operatorInterface = new OperatorInterface();

		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Left", new Autonomous('L'));
		autoChooser.addObject("Middle", new Autonomous('M'));
		autoChooser.addObject("Right", new Autonomous('R'));
		autoChooser.addObject("Left half cross", new Autonomous('l'));
		autoChooser.addObject("Right half cross", new Autonomous('r'));
		SmartDashboard.putData("Autonomous", autoChooser);

		teleChooser = new SendableChooser<>();
		teleChooser.addDefault("Tank", new Teleoperated(driveLine.teleTank));
		teleChooser.addObject("Tank180", new Teleoperated(driveLine.teleTank180));
		teleChooser.addObject("Arcade", new Teleoperated(driveLine.teleArcade));
		SmartDashboard.putData("Teleoperated", teleChooser);
		
		killCompressor = new SendableChooser<>();
		
		killCompressor.addDefault("Compressor on", true);
		killCompressor.addObject("Compressor off", false);
		SmartDashboard.putData("Kill Compressor", killCompressor);

		new Thread( CameraServer.getInstance()::startAutomaticCapture ).start(); // View webcam without blocking subsystems
	}

	@Override
	public void robotPeriodic() { // Update GUI as frequently as possible
		SmartDashboard.putBoolean("WINCH LOW", RobotMap.elevatorLow.get());
		SmartDashboard.putBoolean("WINCH HIGH", RobotMap.elevatorHigh.get());
		SmartDashboard.putNumber("LEFT ENCODER", RobotMap.leftEncoder.getDistance());
		SmartDashboard.putNumber("RIGHT ENCODER", RobotMap.rightEncoder.getDistance());
    	SmartDashboard.putBoolean("REGULATOR", RobotMap.compressor.getPressureSwitchValue());
    	SmartDashboard.putNumber("PRESSURE", RobotMap.pressure.getVoltage()*111.5/2.54);
		SmartDashboard.putString("MESSAGE", DriverStation.getInstance().getGameSpecificMessage());
		SmartDashboard.putNumber("LIFT", RobotMap.claw.getSelectedSensorPosition(0));
		// SmartDashboard.putNumber("STICK", operatorInterface.clawStick.getY());
		SmartDashboard.putBoolean("CLEAR", RobotMap.clawSwitch.get());
		}
	
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() { // Close arm before match
		Scheduler.getInstance().run();
		Scheduler.getInstance().add(new Grab(true));
	}

	@Override
	public void autonomousInit() { // Wait until last second to read which auto to use
		autoCommand = autoChooser.getSelected();
		if (autoCommand != null) autoCommand.start();
	}

	@Override
	public void autonomousPeriodic() { // Pass control to subsystems
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() { // Stop auto and start tele
		if (autoCommand != null) autoCommand.cancel();
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopPeriodic() { // Pick control scheme from OI and check compressor switch
		Scheduler.getInstance().run();
		teleCommand = teleChooser.getSelected();
		if (teleCommand != null) {
			driveLine.setDefaultCommand(teleCommand);
			elevator.setDefaultCommand(teleCommand);
			claw.setDefaultCommand(teleCommand);
		}

		if(killCompressor.getSelected() != null)
			RobotMap.compressor.setClosedLoopControl(killCompressor.getSelected());
	}
}
