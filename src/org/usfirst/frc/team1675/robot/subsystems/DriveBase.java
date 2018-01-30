package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.CheesyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveBase extends Subsystem {

	private TalonSRX leftFront;
	private VictorSPX leftMid;
	private VictorSPX leftBack;
	private TalonSRX rightFront;
	private VictorSPX rightMid;
	private VictorSPX rightBack;
	private DoubleSolenoid shifter;
	AHRS ahrs;

	public DriveBase() {
		leftFront = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_LEFT_FRONT);
		leftMid = new VictorSPX(RobotMap.CANDeviceIDs.DRIVE_LEFT_MID);
		leftBack = new VictorSPX(RobotMap.CANDeviceIDs.DRIVE_LEFT_BACK);
		rightFront = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_FRONT);
		rightMid = new VictorSPX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_MID);
		rightBack = new VictorSPX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_BACK);

		leftFront.setInverted(true);
		leftBack.setInverted(true);
		leftMid.setInverted(true);
		rightFront.setInverted(false);
		rightBack.setInverted(false);
		
		ahrs = new AHRS(SerialPort.Port.kMXP);
		
		shifter = new DoubleSolenoid(RobotMap.SolenoidChannels.SHIFT_HIGH, RobotMap.SolenoidChannels.SHIFT_LOW);
	
	}
    public void setLeftMotors(double power) {
    	leftFront.set(ControlMode.PercentOutput,power);
    	leftMid.set(ControlMode.PercentOutput,power);
    	leftBack.set(ControlMode.PercentOutput,power);
    }
    public void setRightMotors(double power) {
    	rightFront.set(ControlMode.PercentOutput,power);
    	rightMid.set(ControlMode.PercentOutput,power);
    	rightBack.set(ControlMode.PercentOutput,power);
    }	
    public void setAllMotors(double power) {
    	leftFront.set(ControlMode.PercentOutput,power);
    	leftMid.set(ControlMode.PercentOutput,power);
    	leftBack.set(ControlMode.PercentOutput,power);
    	rightFront.set(ControlMode.PercentOutput,power);
    	rightMid.set(ControlMode.PercentOutput,power);
    	rightBack.set(ControlMode.PercentOutput,power);
    }
//    public double deadZone(double value) {
//    	if(Math.abs(value) < 0.1675) {
//    		value = 0;	
//    	}
//    	return value;
//    }
    public void shiftHigh() {
    	shifter.set(DoubleSolenoid.Value.kForward);
    }
    public void shiftLow() {
    	shifter.set(DoubleSolenoid.Value.kReverse);
    }
    public void stopShifter() {
    	shifter.set(DoubleSolenoid.Value.kOff);
    }
    public void resetGyro() {
    	ahrs.zeroYaw();
	}
    public double getAngle() {
    	return ahrs.getAngle();
    }
    public void resetEncoder() {
    	leftFront.getSensorCollection().setQuadraturePosition(0, 0);
    	rightFront.getSensorCollection().setQuadraturePosition(0, 0);
    }
    public double getLeftEncoderValue() {
    	return leftFront.getSelectedSensorPosition(0);
    }
    public double getRightEncoderValue() {
    	return rightFront.getSelectedSensorPosition(0);
    }
    public void initDefaultCommand() {
        setDefaultCommand(new CheesyDrive());
	}
}
