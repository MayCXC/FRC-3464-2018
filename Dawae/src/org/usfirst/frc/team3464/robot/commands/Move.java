package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public abstract class Move extends Command {
	protected DoubleSupplier progress;
	protected double finished;

    public Move(DoubleSupplier progress, double finished) {
    	requires(Robot.dl);

		this.progress = progress;
		this.finished = finished;
    }

    @Override
    protected void execute() {
    	Robot.dl.getDrive().arcadeDrive(0.5, 0.0);
    }
    
    @Override
    protected boolean isFinished() {
		return progress.getAsDouble() >= finished;
    }   
}
