package org.usfirst.frc.team3464.robot.commands;

import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

public class Drive extends Move {
	private double start;
    public Drive(double drive, DoubleSupplier distance, DoublePredicate finished) {
    	super(drive, drive, distance, finished);
    }

	protected void initialize() {
        this.start = distance.getAsDouble();
    }

	protected void end() {
		SensorInput.movePosition(
			SensorInput.mul.apply(
				SensorInput.getGyroOrientation(),
				distance.getAsDouble() - start
		) );
	}
}
