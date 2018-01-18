package org.usfirst.frc.team3464.robot.subsystems;

import java.util.function.BiConsumer;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveLine extends Subsystem {
	public void driveDD(BiConsumer<Double,Double> driveMethod, double l, double r) {
		driveMethod.accept(l, r);
	}

	@Override
	protected void initDefaultCommand() {	}
}
