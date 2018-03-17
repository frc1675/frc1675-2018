package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DropKickstand extends Command {

    public DropKickstand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setTimeout(RobotMap.ArmConstants.DROP_KICKSTAND_TIME);
        Robot.arm.moveArm(RobotMap.ArmConstants.ARM_AUTONOMOUS_MOVEMENT_POWER, 1);
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
        Robot.arm.moveArm(0, 1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
