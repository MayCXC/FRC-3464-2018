package org.usfirst.frc.team3464.robot;

import java.util.EnumMap;
import java.util.function.BiConsumer;

import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class RobotMap {
	public static int
		leftStickPort = 0,
		rightStickPort = 1,
		leftBackMotor = 0,
		leftFrontMotor = 1,
		rightBackMotor = 4,
		rightFrontMotor = 5,
		encoderA = 0,
		encoderB = 1,
		ultraP = 0,
		ultraE = 1;

	public static double
		dpp = 1.0;

	public static enum Point { A, B, C, D, E, F }
	public static EnumMap<Point,Vector2d> field = new EnumMap<Point,Vector2d>(Point.class);
	static {
		field.put(Point.A, new Vector2d(0.0, 0.0));
		field.put(Point.B, new Vector2d(0.0, 0.0));
		field.put(Point.C, new Vector2d(0.0, 0.0));
		field.put(Point.D, new Vector2d(0.0, 0.0));
		field.put(Point.E, new Vector2d(0.0, 0.0));
		field.put(Point.F, new Vector2d(0.0, 0.0));
	}

	public static DifferentialDrive drive = new DifferentialDrive(
		new SpeedControllerGroup(
			new Spark(RobotMap.leftBackMotor),
			new Spark(RobotMap.leftFrontMotor)
		),
		new SpeedControllerGroup(
			new Spark(RobotMap.rightBackMotor),
			new Spark(RobotMap.rightFrontMotor)
		)
	);

	public static BiConsumer<Double,Double> driveMethod = drive::tankDrive;

	public static Timer timer = new Timer();
	public static Encoder encoder = new Encoder(encoderA, encoderB);
	public static Ultrasonic ultra = new Ultrasonic(ultraP, ultraE);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	static {
		timer.reset();
		timer.start();
		encoder.setDistancePerPulse(dpp);
		encoder.reset();
		ultra.setDistanceUnits(Ultrasonic.Unit.kMillimeters);
		ultra.setAutomaticMode(true);
		gyro.calibrate();
	}
}
