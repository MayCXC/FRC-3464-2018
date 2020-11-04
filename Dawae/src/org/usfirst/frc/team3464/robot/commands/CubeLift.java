package org.usfirst.frc.team3464.robot.commands;

import org.usfirst.frc.team3464.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class CubeLift extends Command { // Move the lift into position and close or open the claw
	double position;
	
    public CubeLift(double position) {
    	this.position = position;
    }

    protected void initialize() {
    }

    protected void execute() {
    	RobotMap.claw.set(ControlMode.Position, position);
    }

    protected boolean isFinished() {
        return Math.abs(RobotMap.claw.getSelectedSensorPosition(0) - position) == .01
    		|| RobotMap.clawSwitch.get() == false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
