package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimedDrive extends Command {

    double time;

    public TimedDrive(double timeToGo) {
        requires(Robot.driveBase);
        this.time = timeToGo;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speed = RobotMap.DriveBaseConstants.TIMED_AUTO_POWER_CONSTANT;
        Robot.driveBase.setAllMotors(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (this.isTimedOut()) {
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.driveBase.setAllMotors(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
