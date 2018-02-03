package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CheesyDrive extends Command {
	boolean isPIDEnabled;
    public CheesyDrive() {
    requires(Robot.driveBase);
   
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double drive = Robot.oi.getDriverLeftYAxis();
    	double turn = Robot.oi.getDriverRightXAxis();	
    	if(turn == 0 && drive != 0) {
    		isPIDEnabled = true;
    		Robot.driveBase.activatePIDMode();
    	}else{
    		isPIDEnabled = false;
    		Robot.driveBase.disablePIDMode();
    	}
    	SmartDashboard.putBoolean("IsPIDEnabled", isPIDEnabled);
    	SmartDashboard.putNumber("Left enc", Robot.driveBase.getLeftEncoderValue());
    	SmartDashboard.putNumber("Right enc", Robot.driveBase.getRightEncoderValue());
    	Robot.driveBase.setLeftMotors(drive+turn);
    	Robot.driveBase.setRightMotors(drive-turn);
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveBase.setAllMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
