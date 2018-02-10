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

    public Arm() {
        arm = new TalonSRX(RobotMap.CANDeviceIDs.ARM);
    }

    public void moveArm(double power) {
        arm.set(ControlMode.PercentOutput, power);
    }

    public void resetArmEncoder() {
        arm.getSensorCollection().setQuadraturePosition(RobotMap.ArmConstants.ENCODER_INITIAL_POSITION, 0);
    }

    public double getArmAngle() {
        return RobotMap.ArmConstants.DEGREES_PER_ENCODER_TICK * arm.getSelectedSensorPosition(0);// don't know what the
                                                                                                 // zero does
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArm());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
