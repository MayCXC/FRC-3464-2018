package org.usfirst.frc.team3464.robot.commands;

import java.util.function.Consumer;

import org.usfirst.frc.team3464.robot.Robot;
import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class Teleoperated extends InstantCommand { // Controls robot during tele
	private Consumer<Void> driveMethod;

    public Teleoperated(Consumer<Void> driveMethod) { // Use chosen drive mode
        super();
        requires(Robot.driveLine);
        requires(Robot.elevator);
        requires(Robot.claw);
        
        this.driveMethod = driveMethod;
    }
    
    @Override
    protected void execute() { // Move motors, winch, lift, claw, based on inputs
    	Joystick
    		leftStick = Robot.operatorInterface.leftStick,
    		rightStick = Robot.operatorInterface.rightStick,
    		winchStick = Robot.operatorInterface.winchStick,
    		clawStick = Robot.operatorInterface.clawStick;

    	if (rightStick.getRawButton(5)) {
    		RobotMap.drive.tankDrive(.5, .5);  //Modified to .3
    	}
    	else if(rightStick.getRawButton(3)) {
    		RobotMap.drive.tankDrive(-.5, -.5); //Modified to .3, originally .5
    	}
    	else {
    		driveMethod.accept(null);
    	}

    	Robot.elevator.set(winchStick.getY());

    	double clawy = clawStick.getY(), clawp = RobotMap.claw.getSelectedSensorPosition(0);
    	if( (clawy > 0 && RobotMap.clawSwitch.get()) ||
			(clawy < 0 && clawp > 0)
		)	RobotMap.claw.set(clawy);   
    	else RobotMap.claw.set(0);

    	if (clawStick.getPOV() == 0 || winchStick.getPOV() == 0) {
    		RobotMap.leftClaw.set(!true);
    		RobotMap.rightClaw.set(!true);
    	}

    	//if(clawStick.getTrigger() && !RobotMap.clawSwitch.get()) {
    	//	RobotMap.leftClaw.set(!false);
    	//	RobotMap.rightClaw.set(!false);
    	//}
    	
    	if (clawStick.getPOV() == 180 || winchStick.getPOV() == 180) {
    		RobotMap.leftClaw.set(!false);
    		RobotMap.rightClaw.set(!false);
    	}

    	if ( leftStick.getPOV() == 270 ||
			rightStick.getPOV() == 270 ||
			clawStick.getPOV() == 270 ||
			winchStick.getPOV() == 270
		) RobotMap.view.setPosition(1.0);

    	if ( leftStick.getPOV() == 90 ||
			rightStick.getPOV() == 90 ||
			clawStick.getPOV() == 90 ||
			winchStick.getPOV() == 90
		) RobotMap.view.setPosition(0.015);
 	}
}
