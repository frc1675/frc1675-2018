package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.MoveArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {

	private TalonSRX arm;
	PowerDistributionPanel pdp;

	private DigitalInput button;

	boolean hasButtonBeenPressed = false;

	public static final int FORWARD_LIMIT_POSITION = 3300;
	public static final int REVERSE_LIMIT_POSITION = 500;

	public static final double ENCODER_TICKS_PER_DEGREE = 14.22;
	private static final double ARM_HOLD_POWER = .1;
	public static final double MAX_BATTERY_VOLTAGE = 12.0;

	public Arm() {
		pdp = new PowerDistributionPanel(10);

		arm = new TalonSRX(RobotMap.CANDeviceIDs.ARM);
	    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		arm.setSensorPhase(true);

		arm.configForwardSoftLimitThreshold(FORWARD_LIMIT_POSITION, 0);
		arm.configReverseSoftLimitThreshold(REVERSE_LIMIT_POSITION, 0);
		arm.configForwardSoftLimitEnable(false, 0);
		arm.configReverseSoftLimitEnable(false, 0);

		button = new DigitalInput(RobotMap.ArmConstants.ARM_BUTTON);

	}

	public void moveArm(double power, double scale) {
		if (hasButtonBeenPressed == false && power > 0) {
			power = 0;
		}

		if (power == 0) {
			arm.set(ControlMode.PercentOutput, ARM_HOLD_POWER * (MAX_BATTERY_VOLTAGE / pdp.getVoltage()));
		} else {
			double adjustedPower = this.accountForMotorDeadzone(power * scale);
			arm.set(ControlMode.PercentOutput, adjustedPower);
		}
	}

	public double getArmAngle() {
		return arm.getSensorCollection().getPulseWidthPosition() / ENCODER_TICKS_PER_DEGREE; // zero does
	}

	public double accountForMotorDeadzone(double value) {
		if (value >= 0) {
			return value + ARM_HOLD_POWER;
		} else
			return value;

	}

	public void periodic() {
		if (hasButtonBeenPressed == false) {
			if (button.get() == false) {
				arm.getSensorCollection().setQuadraturePosition(0, 0);
				arm.configForwardSoftLimitEnable(true, 0);
				arm.configReverseSoftLimitEnable(true, 0);

				hasButtonBeenPressed = true;
			}
		}
		SmartDashboard.putBoolean("hasButtonBeenPressed", hasButtonBeenPressed);
		SmartDashboard.putNumber("arm encoder", arm.getSensorCollection().getQuadraturePosition() );
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new MoveArm());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
