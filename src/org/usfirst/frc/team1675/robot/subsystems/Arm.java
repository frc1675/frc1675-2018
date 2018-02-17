package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.MoveArm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

    private TalonSRX arm;
    PowerDistributionPanel pdp;
    
    public Arm() {
        pdp = new PowerDistributionPanel();

        arm = new TalonSRX(RobotMap.CANDeviceIDs.ARM);
        arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
        arm.setSensorPhase(true);;
        arm.configForwardSoftLimitThreshold(RobotMap.ArmConstants.FORWARD_LIMIT_POSITION, 0);
        arm.configReverseSoftLimitThreshold(RobotMap.ArmConstants.REVERSE_LIMIT_POSITION, 0);
        arm.configForwardSoftLimitEnable(true, 0);
        arm.configReverseSoftLimitEnable(true, 0);
    }
    
    public void moveArm(double power, double scale) {
        if (power == 0) {
            arm.set(ControlMode.PercentOutput, .1 * (12.0 / pdp.getVoltage()));
        }
        else {
            double adjustedPower = this.accountForMotorDeadzone(power * scale);
            arm.set(ControlMode.PercentOutput, adjustedPower);
        }
    }

    public double getArmAngle() {
        return arm.getSensorCollection().getPulseWidthPosition() / RobotMap.ArmConstants.ENCODER_TICKS_PER_DEGREE;// don't know what the zero does                                                                                        // zero does
    }
    
    public double accountForMotorDeadzone(double value) {
        if(value >= 0) {
            return value + .1;
        }
        else
            return value;
            
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArm());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
