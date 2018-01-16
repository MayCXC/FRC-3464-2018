package org.usfirst.frc.team3464.robot.commands;

import java.util.function.BooleanSupplier;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.Command;

public class Drive extends Command {
	double startDist;
	BooleanSupplier fin;

    public Drive(BooleanSupplier fin) {
        requires(Robot.si);
        requires(Robot.dl);
        this.fin = fin;
    }

    protected void initialize() {
        startDist = SensorInput.getEncoderDist();
    }	
    
	public static BooleanSupplier encoderFin(double endDist) {
		return () -> SensorInput.getEncoderDist() >= endDist;
	}

	public static BooleanSupplier ultraFin(double endDist) {
		return () -> SensorInput.getUltraDist() <= endDist;
	}

	@Override
	protected boolean isFinished() {
		return fin.getAsBoolean();
	}

	protected void end() {
		SensorInput.setPosition(
			SensorInput.add.apply( SensorInput.getPosition(),
			SensorInput.mul.apply( SensorInput.getOrientation(),
			SensorInput.getEncoderDist() - startDist
		) ) );
	}

	protected void interrupted() {
		end();
	}
}
