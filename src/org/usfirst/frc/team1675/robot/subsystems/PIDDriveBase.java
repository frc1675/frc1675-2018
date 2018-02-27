package org.usfirst.frc.team1675.robot.subsystems;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;
import org.usfirst.frc.team1675.robot.commands.CheesyDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveBase extends PIDSubsystem {
    private TalonSRX leftFront;
    private TalonSRX leftMid;
    private VictorSPX leftBack;
    private TalonSRX rightFront;
    private TalonSRX rightMid;
    private VictorSPX rightBack;
    static final double P = .07;
    static final double I = .0;
    static final double D = .03;
    private double correction;
    private DoubleSolenoid shifter;
    AHRS ahrs;

    public PIDDriveBase() {
        super(P, I, D);
        leftFront = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_LEFT_FRONT);
        leftMid = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_LEFT_MID);
        leftBack = new VictorSPX(RobotMap.CANDeviceIDs.DRIVE_LEFT_BACK);
        rightFront = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_FRONT);
        rightMid = new TalonSRX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_MID);
        rightBack = new VictorSPX(RobotMap.CANDeviceIDs.DRIVE_RIGHT_BACK);
        leftFront.setInverted(true);
        leftBack.setInverted(true);
        leftMid.setInverted(true);
        rightFront.setInverted(false);
        rightBack.setInverted(false);
        rightMid.setInverted(false);

        rightFront.setSensorPhase(true);
        leftFront.setSensorPhase(true);

        ahrs = new AHRS(SerialPort.Port.kMXP);

        shifter = new DoubleSolenoid(RobotMap.SolenoidChannels.SHIFT_HIGH, RobotMap.SolenoidChannels.SHIFT_LOW);

    }

    public void setLeftMotors(double power) {
        if (this.getPIDController().isEnabled()) {
            if (Math.abs(power) + Math.abs(correction) > 1) {
                if (power > 0) {
                    power = power - correction;
                } else {
                    power = power + correction;
                }
            }
        }
        double corrValue = (power + correction);
        leftFront.set(ControlMode.PercentOutput, corrValue);
        leftMid.set(ControlMode.PercentOutput, corrValue);
        leftBack.set(ControlMode.PercentOutput, corrValue);
    }

    public void setRightMotors(double power) {
        if (this.getPIDController().isEnabled()) {
            if (Math.abs(power) + Math.abs(correction) > 1) {
                if (power > 0) {
                    power = power - correction;
                } else {
                    power = power + correction;
                }
            }
        }
        double corrValue = (power - correction);
        rightFront.set(ControlMode.PercentOutput, corrValue);
        rightMid.set(ControlMode.PercentOutput, corrValue);
        rightBack.set(ControlMode.PercentOutput, corrValue);
        // SmartDashboard.putNumber("angle", this.getAngle());

    }

    public void setAllMotors(double power) {
        if (Math.abs(power) + Math.abs(correction) > 1) {
            if (power > 0) {
                power = power - correction;
            } else {
                power = power + correction;
            }
        }
        double leftpower = (power + correction);
        double rightpower = (power - correction);
        leftFront.set(ControlMode.PercentOutput, leftpower);
        leftMid.set(ControlMode.PercentOutput, leftpower);
        leftBack.set(ControlMode.PercentOutput, leftpower);
        rightFront.set(ControlMode.PercentOutput, rightpower);
        rightMid.set(ControlMode.PercentOutput, leftpower);
        rightBack.set(ControlMode.PercentOutput, rightpower);
    }

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
      
       // return leftFront.getSensorCollection().getQuadraturePosition();
        SmartDashboard.putNumber("Left enc", leftFront.getSelectedSensorPosition(0));
      return leftFront.getSelectedSensorPosition(0);
      
    }

    public double getRightEncoderValue() {
       
//        return rightFront.getSensorCollection().getQuadraturePosition();
        SmartDashboard.putNumber("Right enc", rightFront.getSelectedSensorPosition(0));
        return rightFront.getSelectedSensorPosition(0);
    }

    public void activatePIDMode() {
        if (!this.getPIDController().isEnabled()) {
            this.getPIDController().reset();
            this.setSetpoint(this.getAngle());
            this.getPIDController().setOutputRange(-1, 1);
            this.getPIDController().setAbsoluteTolerance(RobotMap.DriveBaseConstants.TOLERANCE);
            this.getPIDController().enable();
        }
    }

    public void disablePIDMode() {
        this.getPIDController().disable();
    }

    protected double returnPIDInput() {
        // SmartDashboard.putNumber("pid in", this.getAngle());
        return this.getAngle();
    }

    protected void usePIDOutput(double output) {
        // SmartDashboard.putNumber("pid out",output);
        // SmartDashboard.putNumber("Setpoint", this.getPIDController().getSetpoint());
        correction = output;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new CheesyDrive());
    }
}
