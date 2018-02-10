package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {

	private VictorSPX intakeLeft;
	private VictorSPX intakeRight;
	
	public Claw(){
		intakeLeft = new VictorSPX(RobotMap.CANDeviceIDs.INTAKE_LEFT);
		intakeRight = new VictorSPX(RobotMap.CANDeviceIDs.INTAKE_RIGHT);
	}
	
	public void setLeftIntakePower(double power) {
		intakeLeft.set(ControlMode.PercentOutput, power);
	}
	
	public void setRightIntakePower(double power) {
		intakeRight.set(ControlMode.PercentOutput, power);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

