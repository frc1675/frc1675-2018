package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForDistance extends PIDCommand {
    PIDSource pst = new PIDSource() {
        PIDSourceType pidType;

        public void setPIDSourceType(PIDSourceType pidSource) {
            pidType = pidSource;
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            // TODO Auto-generated method stub
            return pidType;
        }

        @Override
        public double pidGet() {
            // TODO Auto-generated method stub
            double encoderval = (Robot.driveBase.getLeftEncoderValue() + Robot.driveBase.getRightEncoderValue()) / 2;
            SmartDashboard.putNumber("encoder value", encoderval);
            return encoderval;
        }
    };

    double setpoint;
    double timeout;
    int count = 0;
    LinearDigitalFilter ldf = LinearDigitalFilter.movingAverage(pst, 10);;

    public DriveForDistance(double setpoint, double timeout) {
        super(RobotMap.DriveBaseConstants.DRIVE_P, RobotMap.DriveBaseConstants.DRIVE_I, RobotMap.DriveBaseConstants.DRIVE_D);
        requires(Robot.driveBase);
        this.setpoint = setpoint * RobotMap.DriveBaseConstants.TICKS_PER_INCH;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

        Robot.driveBase.resetEncoder();
        this.getPIDController().reset();
        this.getPIDController().setSetpoint(setpoint);
        this.getPIDController().setOutputRange(-.6, .6);
        this.setTimeout(timeout);
        SmartDashboard.putNumber("setpoint",setpoint );
        this.getPIDController().enable();
        // Robot.driveBase.activatePIDMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    public boolean averageOnTarget() {
        if (Math.abs(ldf.pidGet()-setpoint) <= RobotMap.DriveBaseConstants.DRIVE_TOLERANCE) {
                count++;
            }else {
                count = 0;
            }
        if(count == 10) {
            return true;
        }
        return false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean onTarget = averageOnTarget();
        SmartDashboard.putBoolean("on target", onTarget);
        if (onTarget || this.isTimedOut()) {
            return true;
        } else
            return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.getPIDController().disable();
        Robot.driveBase.setAllMotors(0);
        // Robot.driveBase.disablePIDMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }

    @Override
    protected double returnPIDInput() {
        return (ldf.pidGet());

    }

    @Override
    protected void usePIDOutput(double output) {
        SmartDashboard.putNumber("output", output);
        Robot.driveBase.setAllMotors(output);
    }

}
