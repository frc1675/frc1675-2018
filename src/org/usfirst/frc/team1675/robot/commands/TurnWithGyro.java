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
public class TurnWithGyro extends PIDCommand {
    PIDSource pst = new PIDSource() {
        PIDSourceType pidType;
//        int samples = 0;

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
//            samples++;
//            SmartDashboard.putNumber("samples", samples);
            // TODO Auto-generated method stub
            double angle = Robot.driveBase.getAngle();
            SmartDashboard.putNumber("angle", angle);
            return angle;
        }
    };
    
    int count = 0;
    double setpoint;
    double timeout;
    double initialDegrees;
    double actualSetpoint;
    LinearDigitalFilter ldf = LinearDigitalFilter.movingAverage(pst, 10);

    public TurnWithGyro(double setpoint, double timeout) {
        super(RobotMap.DriveBaseConstants.GYRO_P, RobotMap.DriveBaseConstants.GYRO_I, RobotMap.DriveBaseConstants.GYRO_D);
        requires(Robot.driveBase);
        this.setpoint = setpoint;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.getPIDController().reset();
        this.getPIDController().setOutputRange(-.5, .5);
        initialDegrees = Robot.driveBase.getAngle();
        double actualSetpoint = initialDegrees + setpoint;
        actualSetpoint = this.actualSetpoint;
        this.getPIDController().setSetpoint(actualSetpoint);
        SmartDashboard.putNumber("Setpoint", actualSetpoint);
        this.setTimeout(timeout);
        this.getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    
    public boolean averageOnTarget() {
        if (Math.abs(ldf.pidGet()-actualSetpoint) <= RobotMap.DriveBaseConstants.GYRO_TOLERANCE) {
                count++;
            }else {
                count = 0;
            }
        if(count == 20) {
            return true;
        }
        return false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean onTarget = averageOnTarget();
        SmartDashboard.putBoolean("Gyro on target", onTarget);
        SmartDashboard.putBoolean("Gyro timed out", this.isTimedOut());
        if (onTarget || this.isTimedOut()) {
            return true;
        } else
            return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.getPIDController().disable();
        Robot.driveBase.setAllMotors(0);
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
        SmartDashboard.putNumber("GyroPIDAngle", Robot.driveBase.getAngle());
        SmartDashboard.putNumber("out", output);
        Robot.driveBase.setRightMotors(-output);
        Robot.driveBase.setLeftMotors(output);
    }
}
