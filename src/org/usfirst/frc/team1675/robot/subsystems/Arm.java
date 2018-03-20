package org.usfirst.frc.team1675.robot.subsystems;

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

    private static final double ARM_HOLD_POWER = .1;
    public static final double MAX_BATTERY_VOLTAGE = 12.0;
    private double armPower = 0;
    private double armScale = 0;

    public Arm() {
        pdp = new PowerDistributionPanel();

        arm = new TalonSRX(RobotMap.CANDeviceIDs.ARM);
        arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        arm.setSensorPhase(true);

        arm.configForwardSoftLimitThreshold(RobotMap.ArmConstants.FORWARD_LIMIT_POSITION, 0);
        arm.configReverseSoftLimitThreshold(RobotMap.ArmConstants.REVERSE_LIMIT_POSITION, 0);
        arm.configForwardSoftLimitEnable(false, 0);
        arm.configReverseSoftLimitEnable(false, 0);

        button = new DigitalInput(RobotMap.ArmConstants.ARM_BUTTON);

    }

    public boolean hasButtonBeenPressed() {
        return this.hasButtonBeenPressed;
    }

    public void moveArm(double power, double scale) {
      armPower = power;
      armScale = scale;
    }

    public double getArmEncoderValue() {
        return arm.getSelectedSensorPosition(0);
    }

    public double accountForMotorDeadzone(double value) {
        if (value >= 0) {
            return value + ARM_HOLD_POWER;
        } else
            return value;

    }

    @Override
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
        SmartDashboard.putNumber("arm encoder", arm.getSensorCollection().getQuadraturePosition());
     
        if (hasButtonBeenPressed == false && armPower > 0) {
            armPower = 0;
        }

        if (armPower == 0) {
            arm.set(ControlMode.PercentOutput, ARM_HOLD_POWER * (MAX_BATTERY_VOLTAGE / pdp.getVoltage()));
        } else {
            double adjustedPower = this.accountForMotorDeadzone(armPower * armScale);
            arm.set(ControlMode.PercentOutput, adjustedPower);
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new MoveArm());
    }
}
