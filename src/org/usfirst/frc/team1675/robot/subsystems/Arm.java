package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.MoveArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	
	private TalonSRX arm;
	
	public Arm(){
		arm = new TalonSRX(RobotMap.CANDeviceIDs.ARM);	
	}
	
	public void moveArm(double power) {
		arm.set(ControlMode.PercentOutput, power);
	}

    public double getArmEncoderValue() {
        return arm.getSelectedSensorPosition(0);
    }
    
    public void resetArmEncoder() {
        arm.getSensorCollection().setQuadraturePosition(0,0);
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveArm());
    }
}

