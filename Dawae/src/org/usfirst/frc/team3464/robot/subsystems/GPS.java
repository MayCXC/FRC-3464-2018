package org.usfirst.frc.team3464.robot.subsystems;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;

public class GPS extends Subsystem {
	private static Vector2d here = new Vector2d(0.0, 0.0);

	public static void move(Vector2d there) {
		here = add.apply(here, there);
	}

    public void initDefaultCommand() {
    	// setDefaultCommand(new Posit());
    }

	public static BinaryOperator<Vector2d> add = (a, b) -> new Vector2d(a.x+b.x,a.y+b.y);
    public static DoubleFunction<Vector2d> unit = (d) -> new Vector2d(Math.cos(d), Math.sin(d));
    public static BiFunction<Vector2d,Double,Vector2d> mul = (a, b) -> new Vector2d(a.x*b, a.y*b);
    public static BinaryOperator<Vector2d> sub = (a, b) -> add.apply(a, mul.apply(b, -1.0));

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

	public static Vector2d unit(double a) {
		return new Vector2d(Math.cos(a), Math.sin(a));
	}
}

