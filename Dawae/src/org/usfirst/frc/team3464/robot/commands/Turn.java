package org.usfirst.frc.team3464.robot.commands;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	double startDir;

    public Turn(double endAngle) {
    	requires(Robot.dl);
    }

    protected void initialize() {
    	startDir = SensorInput.getGyroDir();
    }

    public static BooleanSupplier gyroCWFin() {
    	return () -> false; // SensorInput.getGyroDir(); and trig
    }
    
    public static BooleanSupplier gyrpCCWFin() {
    	return () -> false; // ditto
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
