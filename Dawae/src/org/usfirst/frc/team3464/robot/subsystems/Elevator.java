package org.usfirst.frc.team3464.robot.subsystems;

import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public void set(double lift) {
		if ( (RobotMap.elevatorLow.get() && lift < 0.0) || (RobotMap.elevatorHigh.get() && lift > 0.0) )
			RobotMap.winch.set(lift);
		else RobotMap.winch.set(0.0);
	}

	@Override
	protected void initDefaultCommand() {		
	}
}

