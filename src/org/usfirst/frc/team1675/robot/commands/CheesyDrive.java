package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CheesyDrive extends Command {
    boolean isPIDEnabled;
    Timer timer = new Timer();
    boolean timerStarted = false;

    public CheesyDrive() {
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double leftYValue = Robot.oi.getDriverLeftYAxis();
        double rightXValue = Robot.oi.getDriverRightXAxis();

        double drive = Math.signum(leftYValue)
                * Math.pow(Math.abs(leftYValue), RobotMap.DriveBaseConstants.DRIVE_EXPONENT);
        double turn = Math.signum(rightXValue)
                * Math.pow(Math.abs(rightXValue), RobotMap.DriveBaseConstants.TURN_EXPONENT);

        if (turn == 0 && drive != 0) {
            if (!timerStarted) {
                timer.start();
            }
            timerStarted = true;
        } else {
            timer.reset();
            timer.stop();
            timerStarted = false;
            isPIDEnabled = false;
            // Robot.driveBase.disablePIDMode();
        }
        SmartDashboard.putNumber("timer", timer.get());
        if (timer.get() >= .25) {
            isPIDEnabled = true;
            // Robot.driveBase.activatePIDMode();
        }
        // SmartDashboard.putBoolean("IsPIDEnabled", isPIDEnabled);
        Robot.driveBase.setLeftMotors(drive + turn);
        Robot.driveBase.setRightMotors(drive - turn);

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
