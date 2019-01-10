package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Move extends Command {
	protected DoubleSupplier progress;
	protected double finished;

    public Move(DoubleSupplier progress, double finished) {
    	requires(Robot.driveLine);

		this.progress = progress;
		this.finished = finished;
    }
    protected abstract double getSpeed();
    protected abstract double getZRotation();

    @Override
    protected void initialize() {
    	this.finished += progress.getAsDouble();
    }

    @Override
    protected void execute() {
    	Robot.driveLine.arcade(getSpeed(), getZRotation());
    }
}
