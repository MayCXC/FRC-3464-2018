package org.usfirst.frc.team3464.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.RobotMap;

public class Autonomous extends CommandGroup { // All of the auto presets
	private char start;

	public Autonomous(char start) { // Read starting position from GUI
		this.start = start;
	}

	@Override
	public void start() { // Read field layout and map start and end points to movement paths 
		//DoubleSupplier driveProgress = RobotMap.timer::get;
		DoubleSupplier driveProgress = RobotMap.leftEncoder::getDistance;
		char fms = '_';
		fms = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
		addSequential(new Grab(true));
		addParallel(new CubeLift(RobotMap.top));
		if ((start == 'L' || start == 'l') && fms == 'L') { // Move down left lane
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 124.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, 90.0));
			addSequential(new Drive(RobotMap.timer::get, 2.0, (t) -> .6));
			addSequential(new Grab(false));
		}
		else if (start == 'L' && fms == 'R') { // Cross lanes and drop the cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 180.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, 90.0));
			addSequential(new Drive(driveProgress, 130.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, 45.0));
			addSequential(new Drive(RobotMap.timer::get, .25, (t) -> .6));
			addSequential(new Turn(RobotMap.gyro::getAngle, 45.0));
			addSequential(new Drive(RobotMap.timer::get, .25, (t) -> .6));
			addSequential(new Grab(false));
		}
		else if (start == 'M' && fms == 'L') { // Adjust angle and drop the cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .85));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .85));
			addSequential(new Turn(RobotMap.gyro::getAngle, -45.0));
			addSequential(new Drive(driveProgress, /**99.0**/ 60.0, (t) -> .70));
			addSequential(new Turn(RobotMap.gyro::getAngle, 45.0));
			addSequential(new Drive(RobotMap.timer::get, 2.0, (t) -> .60));
			addSequential(new Grab(false));
		}
		else if (start == 'M' && fms == 'R') { // Adjut angle and drop the cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .85));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .85));
			addSequential(new Turn(RobotMap.gyro::getAngle, 45.0));
			addSequential(new Drive(driveProgress, 60.0, (t) -> .70));
			addSequential(new Turn(RobotMap.gyro::getAngle, -45.0));
			addSequential(new Drive(RobotMap.timer::get, 2.0, (t) -> .60));
			addSequential(new Grab(false));
		}
		else if (start == 'R' && fms == 'L') { // Cross lanes and drop the cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 180.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, -90.0));
			addSequential(new Drive(driveProgress, 130.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, -45.0));
			addSequential(new Drive(RobotMap.timer::get, .25, (t) -> .6));
			addSequential(new Turn(RobotMap.gyro::getAngle, -45.0));
			addSequential(new Drive(RobotMap.timer::get, .25, (t) -> .6));
			addSequential(new Grab(false));
		}
		else if ((start =='R' || start == 'r') && fms == 'R') { // Cross lanes, keep cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 124.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, -90.0));
			addSequential(new Drive(RobotMap.timer::get, 2.0, (t) -> .6));
			addSequential(new Grab(false));
		}
		else if (start == 'l' && fms == 'R') { // Cross lanes, keep cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 180.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, 90.0));
			addSequential(new Drive(driveProgress, 80.0, (t) -> .60));
		}
		else if (start == 'r' && fms == 'L') { // Cross lanes, keep cube
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 5.0, (t) -> .65));
			addSequential(new Drive(driveProgress, 180.0, (t) -> .65));
			addSequential(new Turn(RobotMap.gyro::getAngle, -90.0));
			addSequential(new Drive(driveProgress, 80.0, (t) -> .60));
		}
		else { // Enter field and wait
			addSequential(new Drive(RobotMap.timer::get, 2.0, (t -> .5)));
		}
		super.start();
	}

	@Override
	protected boolean isFinished() { // Stop gracefully when auto is over, but tele will override this if it needs to anyways
		return DriverStation.getInstance().isAutonomous() == false;
	}
}
