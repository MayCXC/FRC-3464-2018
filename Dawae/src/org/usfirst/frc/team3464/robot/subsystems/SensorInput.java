package org.usfirst.frc.team3464.robot.subsystems;

import java.util.function.BiFunction;
import java.lang.Math;

import org.usfirst.frc.team3464.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;

public class SensorInput extends Subsystem {
	private static Vector2d position, orientation;
	public static void setPosition(Vector2d pos) {
		position = pos;
	}
	public static Vector2d getPosition() {
		return position;
	}
	public static void setOrientation(Vector2d orn) {
		orientation = orn;
	}
	public static Vector2d getOrientation() {
		return orientation;		
	}

    public static BiFunction<Vector2d,Vector2d,Vector2d> add = 
		(a, b) -> new Vector2d(a.x+b.x,a.y+b.y);

    public static BiFunction<Vector2d,Double,Vector2d> mul =
    	(a, b) -> new Vector2d(a.x*b, a.y*b);
    
    public static BiFunction<Vector2d,Vector2d,Vector2d> sub =
		(a, b) -> add.apply(a, mul.apply(b, -1.0));

	public static double getAngle(Vector2d newPos) {
		Vector2d s = sub.apply(newPos, getPosition());
		return Math.atan2(s.y, s.x);
	}

	public static double getDistance(Vector2d newPos) {
		return sub.apply(newPos, getPosition()).magnitude();
	}
	
	public static double getEncoderDist() {
		return RobotMap.encoder.getDistance();
	}

	public static double getUltraDist() {
		return RobotMap.ultra.getRangeInches();
	}

	public static double getGyroDir() {
		return RobotMap.gyro.getAngle();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

