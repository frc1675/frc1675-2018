package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class StopDriveWhileDeploying extends Command {

    public StopDriveWhileDeploying() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(Robot.getTeleopTime() >= RobotMap.RampConstants.RAMP_DROP_TIME) {
            Robot.driveBase.setAllMotors(0);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.getTeleopTime() <= RobotMap.RampConstants.RAMP_DROP_TIME;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
