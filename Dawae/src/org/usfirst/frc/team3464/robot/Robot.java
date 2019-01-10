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
	public static final DriveLine driveLine = new DriveLine();
	public static final Elevator elevator = new Elevator();
	public static final Claw claw = new Claw();

	public static OperatorInterface operatorInterface;

	SendableChooser<Autonomous> autoChooser;
	Command autoCommand = null;

	SendableChooser<Teleoperated> teleChooser;
	Command teleCommand = null;
	
	SendableChooser<Boolean> killCompressor;

	@Override
	public void robotInit() {
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

		new Thread( CameraServer.getInstance()::startAutomaticCapture ).start();
	}

	@Override
	public void robotPeriodic() {
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
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		Scheduler.getInstance().add(new Grab(true));
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
		Scheduler.getInstance().removeAll();
	}

	@Override
	public void teleopPeriodic() {
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
