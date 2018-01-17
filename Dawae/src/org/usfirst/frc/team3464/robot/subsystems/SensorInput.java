package org.usfirst.frc.team3464.robot.subsystems;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.lang.Math;

import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;

public class SensorInput extends Subsystem {
	private static Vector2d here = new Vector2d(0.0,0.0);
	public static void movePosition(Vector2d move) {
		here = add.apply(move, here);
	}

    public static BinaryOperator<Vector2d> add = 
		(a, b) -> new Vector2d(a.x+b.x,a.y+b.y);

    public static BiFunction<Vector2d,Double,Vector2d> mul =
    	(a, b) -> new Vector2d(a.x*b, a.y*b);
    
    public static BinaryOperator<Vector2d> sub =
		(a, b) -> add.apply(a, mul.apply(b, -1.0));

	private static Vector2d differenceVector(Vector2d there) {
		return sub.apply(there, here);
	}

	public static double getAngle(Vector2d there) {
		Vector2d diff = differenceVector(there);
		return Math.atan2(diff.y, diff.x);
	}

	public static double getDistance(Vector2d there) {
		return differenceVector(there).magnitude();
	}

	public static double getTime() {
		return RobotMap.timer.get();
	}
	
	public static double getEncoderDistance() {
		return RobotMap.encoder.getDistance();
	}
	
	public static double getUltraDistance() {
		return RobotMap.ultra.getRangeInches();
	}

	public static double getGyroDirection() {
		return RobotMap.gyro.getAngle();
	}

	public static Vector2d getGyroOrientation() {
		double dir = getGyroDirection();
		return new Vector2d(Math.cos(dir), Math.sin(dir));
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

