package org.usfirst.frc.team3464.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem { // Literally just toggle the claw state
	public boolean grabState = false;
	
	@Override
	public void initDefaultCommand() {
	}
}

