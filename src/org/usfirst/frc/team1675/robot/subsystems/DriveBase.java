package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.CheeseyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveBase extends Subsystem {

	private TalonSRX leftFront;
	private TalonSRX leftBack;
	private TalonSRX rightFront;
	private TalonSRX rightBack;
	private DoubleSolenoid shifter;
	
	public DriveBase() {
		leftFront = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_LEFT_FRONT);
		leftBack = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_LEFT_BACK);
		rightFront = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_FRONT);
		rightBack = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_BACK);
		
		leftFront.setInverted(true);
		leftBack.setInverted(true);
		rightFront.setInverted(false);
		rightBack.setInverted(false);

	}
    public void setLeftMotors(double power) {
    	leftFront.set(ControlMode.PercentOutput,power);
    	leftBack.set(ControlMode.PercentOutput,power);
    }
    public void setRightMotors(double power) {
    	rightFront.set(ControlMode.PercentOutput,power);
    	rightBack.set(ControlMode.PercentOutput,power);
    }	
    public void setAllMotors(double power) {
    	leftFront.set(ControlMode.PercentOutput,power);
    	leftBack.set(ControlMode.PercentOutput,power);
    	rightFront.set(ControlMode.PercentOutput,power);
    	rightBack.set(ControlMode.PercentOutput,power);
    }
//    public double deadZone(double value) {
//    	if(Math.abs(value) < 0.1675) {
//    		value = 0;	
//    	}
//    	return value;
//    }
    public void shiftHigh() {
    	
    }
    public void shiftLow() {
    	
    }
    public void stopShifter() {
    	
    }
    public void resetGyro() {
	
	}
   
    public void initDefaultCommand() {
        setDefaultCommand(new CheeseyDrive());
    }
}

