package org.usfirst.frc.team3464.robot.commands;

import org.usfirst.frc.team3464.robot.RobotMap;
import org.usfirst.frc.team3464.robot.subsystems.GPS;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.Vector2d;

public class Goto extends CommandGroup {
    public Goto(Turn t, Drive d) {
    	addSequential(t);
    	addSequential(d);
    }

    public Goto(Vector2d there) {
    	addSequential( new Turn( RobotMap.gyro::pidGet, GPS.getAngle(there) ) );
    	addSequential( new Drive( RobotMap.encoder::pidGet, GPS.getDistance(there) ) );
    	// addSequential( new Drive( .5, RobotMap.timer::getTime, GPS.getDistance(there) ) );
    }
}
