package org.usfirst.frc.team3464.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.Ultrasonic;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;

public class RobotMap { // Central list of sensor ports and parameters for convenience
	 public static int
		leftStickPort = 0,
		rightStickPort = 1,
		winchStickPort = 2,
		clawStickPort = 3,

		leftBackMotor = 0,
		leftFrontMotor = 1,
		rightBackMotor = 2,
		rightFrontMotor = 3, 
		
		spinnerleft = 4,
		spinnerright = 5,
		
		lift = 6,

		leftEncoderA = 4,
		leftEncoderB = 5,

		rightEncoderA = 2,
		rightEncoderB = 3,

		winchMotor = 4,
		clawMotor = 0,
		clawSwitchPort = 6,

		lowSwitch = 1,
		highSwitch = 0,

		pcmPort = 0,
		pressurePort = 0,
		clawButtonNumber = 0,
		leftSolenoid = 0,
		rightSolenoid = 1,
		
		cameraServo = 5,
		
		ultraP = 0,
		ultraE = 1;
	public static double
		dpp = 0.16, // distance per pulse
		top = 2700.0;

	public static DifferentialDrive drive = new DifferentialDrive( // Tank controls
		new SpeedControllerGroup(
			new Spark(RobotMap.leftBackMotor),
			new Spark(RobotMap.leftFrontMotor)
		),
		new SpeedControllerGroup(
			new Spark(RobotMap.rightBackMotor),
			new Spark(RobotMap.rightFrontMotor)
		)
	);

	public static Encoder // Left and right driveline odometers
		leftEncoder = new Encoder(leftEncoderA, leftEncoderB),
		rightEncoder = new Encoder(rightEncoderA, rightEncoderB);

	public static Spark winch = new Spark(winchMotor); // Big lift motor
    
	public static WPI_TalonSRX claw = new WPI_TalonSRX(clawMotor); // Little lift motor
	public static DigitalInput clawSwitch = new DigitalInput(clawSwitchPort); // Claw limit switch

	public static Servo view = new Servo(cameraServo); // Changes camera angle

	public static DigitalInput // Lift limit switches
		elevatorLow = new DigitalInput(lowSwitch),
		elevatorHigh = new DigitalInput(highSwitch);

	public static Compressor compressor = new Compressor(pcmPort); // Air tank compressor

	public static AnalogInput pressure = new AnalogInput(pressurePort); // Air tank pressure

	public static Solenoid // Claw valves
		leftClaw = new Solenoid(leftSolenoid),
		rightClaw = new Solenoid(rightSolenoid);

	public static Timer timer = new Timer(); // Timer for auto
	//public static Ultrasonic ultra = new Ultrasonic(ultraP, ultraE);
	public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(); // Gyro for auto
	//public static USB
	static { // Set up all sensors when robot turns on
		timer.reset();
		timer.start();
		leftEncoder.setDistancePerPulse(dpp);
		leftEncoder.setReverseDirection(true);
		leftEncoder.reset();
		rightEncoder.setDistancePerPulse(dpp);
		rightEncoder.reset();
		claw.setNeutralMode(NeutralMode.Brake);
		claw.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		claw.config_kP(0, 1.0, 0);
		claw.selectProfileSlot(0, 0);
		claw.setSelectedSensorPosition(0, 0, 0);
		claw.setSensorPhase(true);
		//ultra.setDistanceUnits(Ultrasonic.Unit.kMillimeters);
		//ultra.setAutomaticMode(true);
		gyro.calibrate();
		compressor.setClosedLoopControl(true);
	}
}
