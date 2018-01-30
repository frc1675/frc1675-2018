package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnWithGyro extends PIDCommand {
	double setpoint;
	double timeout;
	double initialDegrees;

    public TurnWithGyro(double setpoint,double timeout) {
    	super(RobotMap.DriveBaseConstants.P,RobotMap.DriveBaseConstants.I,RobotMap.DriveBaseConstants.D);
        requires(Robot.driveBase);
        this.setpoint = setpoint;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.getPIDController().reset();
    	this.getPIDController().setOutputRange(-1.0, 1.0);
    	this.getPIDController().setSetpoint(initialDegrees+setpoint);
    	this.getPIDController().setAbsoluteTolerance(RobotMap.DriveBaseConstants.TOLERANCE);
    	this.setTimeout(timeout);
    	initialDegrees = Robot.driveBase.getAngle();
    	this.getPIDController().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(this.getPIDController().onTarget() || this.isTimedOut()) {
    		return true;
    	}
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
    	Robot.driveBase.setAllMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		return Robot.driveBase.getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		SmartDashboard.putNumber("GyroPIDAngle", Robot.driveBase.getAngle());
		Robot.driveBase.setRightMotors(output);
		Robot.driveBase.setLeftMotors(output);
	}
}
