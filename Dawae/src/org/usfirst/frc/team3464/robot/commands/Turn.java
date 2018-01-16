package org.usfirst.frc.team3464.robot.commands;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	BooleanSupplier fin;

    public Turn(BooleanSupplier fin) {
    	requires(Robot.si);
    	requires(Robot.dl);
    	this.fin = fin;
    }

    protected void initialize() {
    }

    /**
    public static BooleanSupplier gyroCWFin(double endDir) {
    	return () -> SensorInput.getGyroDir() >= endDir;
    }
    
    public static BooleanSupplier gyroCCWFin(double endDir) {
    	return () -> SensorInput.getGyroDir() <= endDir;
    }
	**/

    public static BooleanSupplier gyroFin(double endDir) {
    	return endDir >= 0
    			? () -> SensorInput.getGyroDir() >= endDir
    			: () -> SensorInput.getGyroDir() <= endDir
    			;
    }
    
    protected void execute() {
    	Robot.dl.driveStick(.5, -.5); // cw ? ccw
    }

    protected boolean isFinished() {
        return fin.getAsBoolean();
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
