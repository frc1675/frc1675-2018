package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReckoningTurn extends Command {
    Timer timer = new Timer();
    double time;
    boolean turn;

    public ReckoningTurn(double timeToTurn, boolean turnDir) {
       requires(Robot.driveBase);
       this.time = timeToTurn;
       this.turn = turnDir;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double speed = 0.6;
        if(this.turn = false) {
        Robot.driveBase.setLeftMotors(speed);
        Robot.driveBase.setRightMotors(-speed);
        }
        if(this.turn = true) {
            Robot.driveBase.setLeftMotors(-speed);
            Robot.driveBase.setRightMotors(speed);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(timer.get() >= time) {
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
