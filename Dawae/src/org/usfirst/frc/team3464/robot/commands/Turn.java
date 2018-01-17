package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Turn extends Command {
	double drive;
	private DoubleSupplier direction;
	private DoublePredicate finished;

    public Turn(double drive, DoubleSupplier direction, DoublePredicate finished) {
    	requires(Robot.si);
    	requires(Robot.dl);
    	this.drive = drive;
    	this.direction = direction;
    	this.finished = finished;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.dl.driveStick(drive,-drive);
    }

    protected boolean isFinished() {
        return finished.test(direction.getAsDouble());
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
