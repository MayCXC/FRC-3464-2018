package org.usfirst.frc.team3464.robot;

import java.util.function.Consumer;

import edu.wpi.first.wpilibj.Joystick;

public class OperatorInterface {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	public Joystick
	leftStick = new Joystick(RobotMap.leftStickPort),
	rightStick = new Joystick(RobotMap.rightStickPort);

	private double idRange(double n) {
		return n > 1.0 ? 1.0 : n < -1.0 ? -1.0 : n;
	}

	private double tankLeft(double y, double z) {
		return idRange( y + z );
	}

	private double tankRight(double y, double z) {
		return idRange( y - z );
	}

	public void arcade(double y, double z) {
		RobotMap.drive.tankDrive(tankLeft(y,z), tankRight(y,z));
	}
	
	public Consumer<Void> teleTank = (Void) -> {
		double l = leftStick.getY();
		double r = rightStick.getY();
		RobotMap.drive.tankDrive(l,r);
	};
	
	public Consumer<Void> teleArcade = (Void) -> {
		double y = Robot.operatorInterface.leftStick.getY();
		double z = Robot.operatorInterface.leftStick.getZ();
		arcade(y,z);
	};

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
