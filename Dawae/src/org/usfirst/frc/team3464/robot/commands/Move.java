package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import edu.wpi.first.wpilibj.command.PIDCommand;

public abstract class Move extends PIDCommand {
	protected DoubleSupplier pidGet;
	protected double setpoint;

    public Move(DoubleSupplier pidGet, double setpoint) {
    	super(1.0, 0.0, 0.0);
    	requires(Robot.dl);
    	requires(Robot.gps);

		this.pidGet = pidGet;
		this.setpoint = setpoint;
    }

    protected void initialize() {
		getPIDController().setContinuous(false);
		getPIDController().setOutputRange(-1.0, 1.0);
    	getPIDController().setSetpoint(setpoint);
    }
    
    protected double returnPIDInput() {
    	return pidGet.getAsDouble();
    }

    protected boolean isFinished() {
		return getPIDController().onTarget();
    }   
}
