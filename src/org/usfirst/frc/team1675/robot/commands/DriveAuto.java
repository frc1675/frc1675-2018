package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.filters.LinearDigitalFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAuto extends PIDCommand {
	PIDSource pst = new PIDSource() {
		PIDSourceType pidType;
		public void setPIDSourceType(PIDSourceType pidSource) {
			pidType=pidSource;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return pidType;
		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return (Robot.driveBase.getLeftEncoderValue()+Robot.driveBase.getRightEncoderValue())/2;
		}};
		
	double setpoint;
	double timeout;
	LinearDigitalFilter ldf = LinearDigitalFilter.movingAverage(pst, 10); ;

    public DriveAuto(double setpoint,double timeout) {
    	super(RobotMap.DriveBaseConstants.P, RobotMap.DriveBaseConstants.I, RobotMap.DriveBaseConstants.D);
		requires(Robot.driveBase);
		this.setpoint = setpoint * RobotMap.DriveBaseConstants.TICKS_PER_INCH;
		this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveBase.resetEncoder();
    	this.getPIDController().reset();
    	this.getPIDController().enable();
    	this.getPIDController().setSetpoint(setpoint);
    	this.getPIDController().setOutputRange(-1,1);
    	this.setTimeout(timeout);
    	this.getPIDController().setAbsoluteTolerance(RobotMap.DriveBaseConstants.TOLERANCE);
    	Robot.driveBase.activatePIDMode();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    public boolean averageOnTarget() {
    	if(ldf.pidGet() >= setpoint-RobotMap.DriveBaseConstants.TOLERANCE) {
    		if(ldf.pidGet() <= setpoint+RobotMap.DriveBaseConstants.TOLERANCE) {
    			return true;
    		}
    	}
    	return false;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(averageOnTarget() || this.isTimedOut()) {
    		return true;
    	}
    	else return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	this.getPIDController().disable();
    	Robot.driveBase.setAllMotors(0);
    	Robot.driveBase.disablePIDMode();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
//		SmartDashboard.putNumber("pid in", ldf.pidGet());
		return (ldf.pidGet());
		
	}

	@Override
	protected void usePIDOutput(double output) {
//		SmartDashboard.putNumber("pid out", output);
//		SmartDashboard.putNumber("setpoint", this.getPIDController().getSetpoint());
//		SmartDashboard.putNumber("error", this.getPIDController().getError());
		Robot.driveBase.setAllMotors(output);
	}
}
