package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Move extends Command {
	private double left, right;
	DoubleSupplier distance;
	private DoublePredicate finished;

    public Move(double left, double right, DoubleSupplier distance, DoublePredicate finished) {
    	requires(Robot.si);
    	requires(Robot.dl);
    	
    	this.left = left;
		this.right = right;
		this.distance = distance;
		this.finished = finished;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.dl.driveStick(left, right);
    }

    protected boolean isFinished() {
		return finished.test(distance.getAsDouble());
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
