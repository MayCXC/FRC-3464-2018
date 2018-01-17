package org.usfirst.frc.team3464.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
	private double drive, start;
	private DoubleSupplier distance;
	private BooleanSupplier finished;

    public Drive(double drive, DoubleSupplier distance, BooleanSupplier finished) {
        requires(Robot.si);
        requires(Robot.dl);
        this.drive = drive;
        this.distance = distance;
        this.finished = finished;
    }

	protected void initialize() {
        this.start = distance.getAsDouble();
    }

	protected void execute() {
		Robot.dl.driveStick(drive, drive);
	}

	@Override
	protected boolean isFinished() {
		return finished.getAsBoolean();
	}

	protected void end() {
		SensorInput.movePosition(
			SensorInput.mul.apply(
				SensorInput.getGyroOrientation(),
				distance.getAsDouble() - start
		) );
	}

	protected void interrupted() {
		end();
	}
}
