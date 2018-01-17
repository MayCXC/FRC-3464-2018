package org.usfirst.frc.team3464.robot.commands;

import org.usfirst.frc.team3464.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Lift extends Command {

    public Lift() {
    	requires(Robot.cl);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
