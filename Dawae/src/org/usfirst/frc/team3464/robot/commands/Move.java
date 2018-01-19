package org.usfirst.frc.team3464.robot.commands;

import java.util.function.BiConsumer;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Move extends Command {
	protected BiConsumer<Double,Double> method;
	protected DoubleSupplier progress;
	protected double finished;

    public Move(BiConsumer<Double,Double> method, DoubleSupplier progress, double finished) {
    	requires(Robot.dl);

    	this.method = method;
		this.progress = progress;
		this.finished = finished;
    }

    protected abstract double left();
    protected abstract double right();

    @Override
    protected void execute() {
    	method.accept(left(), right());
    }
    
    @Override
    protected boolean isFinished() {
		return progress.getAsDouble() >= finished;
    }   
}
