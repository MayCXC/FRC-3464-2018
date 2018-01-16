package org.usfirst.frc.team3464.robot.commands;

import org.usfirst.frc.team3464.robot.subsystems.SensorInput;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.Vector2d;

public class Goto extends CommandGroup {
    public Goto(Vector2d newPos) {
    	addSequential(new Turn(SensorInput.getAngle(newPos)));
    	addSequential(new Drive(Drive.encoderFin(SensorInput.getDistance(newPos))));
    }
}
