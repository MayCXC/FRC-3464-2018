package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;

import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.Vector2d;

public class Goto extends CommandGroup {
    public Goto(Turn t, Drive d) {
    	addSequential(t);
    	addSequential(d);
    }

    public static DoublePredicate turnGyroFinish(Vector2d there) {
    	double end = SensorInput.getAngle(there);
    	DoubleUnaryOperator wrap = (d) -> Math.atan2(Math.sin(d), Math.cos(d));
    	return d -> Math.abs( wrap.applyAsDouble( d - end ) ) <= .1;
    }

    public static DoublePredicate driveEncoderFinish(Vector2d there) {
    	double end = SensorInput.getEncoderDistance() + SensorInput.getDistance(there);
    	return d -> d >= end;
    }

    public static DoublePredicate driveUltraFinish(double distance) {
    	return d -> SensorInput.getUltraDistance() < distance; // is ignoring the d crappy?
    }
    
    public static DoublePredicate timerFinish(Vector2d there) {
    	double end = SensorInput.getTime() + SensorInput.getDistance(there); // divided by vel etc.
    	return d -> d >= end;
    }

    public Goto(Vector2d there) {
    	addSequential( new Turn( .5, SensorInput::getGyroDirection, turnGyroFinish(there) ) );
    	addSequential( new Drive( .5, SensorInput::getEncoderDistance, driveEncoderFinish(there) ) );
    	// addSequential( new Drive( .5, SensorInput::getTime, driveTimerFinish(there) ) );
    }
}
