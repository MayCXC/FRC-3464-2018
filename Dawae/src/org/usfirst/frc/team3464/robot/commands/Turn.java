package org.usfirst.frc.team3464.robot.commands;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	double drive;
	private BooleanSupplier finished;

    public Turn(double drive, BooleanSupplier finished) {
    	requires(Robot.si);
    	requires(Robot.dl);
    	this.drive = drive;
    	this.finished = finished;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.dl.driveStick(drive,-drive);
    }

    protected boolean isFinished() {
        return finished.getAsBoolean();
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
