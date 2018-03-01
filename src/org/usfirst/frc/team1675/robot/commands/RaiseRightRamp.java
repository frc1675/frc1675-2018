package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RaiseRightRamp extends Command {

    public RaiseRightRamp() {
        requires(Robot.ramp);
        this.setTimeout(RobotMap.RampConstants.SOLENOID_ACTIVATION_TIME);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(Robot.ramp.haveRampsBeenDeployed) {
            Robot.ramp.rampRaiseRight();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.ramp.rampStopRight();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
