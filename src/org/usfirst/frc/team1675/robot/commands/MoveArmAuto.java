package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmAuto extends Command {
    double setpoint;
    double timeout;
    int count = 0;

    public MoveArmAuto(double setpoint, double timeout) {
        requires(Robot.driveBase);
        this.setpoint = setpoint;
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double power;
        power = (setpoint - Robot.arm.getArmEncoderValue()) / 100;
        if (Robot.arm.getArmEncoderValue() < setpoint + 10) {
            Robot.arm.moveArm(power);
            count = 0;
        }
        if (Robot.arm.getArmEncoderValue() < setpoint - 10) {
            Robot.arm.moveArm(power);
            count = 0;
        }
        if (Robot.arm.getArmEncoderValue() > setpoint + 10) {
            Robot.arm.moveArm(-power);
            count = 0;
        }
        if (Robot.arm.getArmEncoderValue() > setpoint - 10) {
            Robot.arm.moveArm(-power);
            count = 0;
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.arm.getArmEncoderValue() == setpoint + 10) {
            count++;
            if (count == 10) {
                return true;
            }
            if (Robot.arm.getArmEncoderValue() == setpoint - 10) {
                count++;
                if (count == 10) {
                    return true;
                }
            }
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.arm.moveArm(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
