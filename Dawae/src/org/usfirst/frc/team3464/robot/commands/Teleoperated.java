package org.usfirst.frc.team3464.robot.commands;

import java.util.function.Consumer;

import org.usfirst.frc.team3464.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class Teleoperated extends InstantCommand {
	private Consumer<Void> driveMethod;

    public Teleoperated(Consumer<Void> driveMethod) {
        super();
        requires(Robot.driveLine);
        
        this.driveMethod = driveMethod;
    }

    @Override
    protected void execute() {
    	driveMethod.accept(null);
	}
}
