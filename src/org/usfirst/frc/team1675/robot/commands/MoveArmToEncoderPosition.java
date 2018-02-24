package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmToEncoderPosition extends Command {

    double encoderValue;   
    
    public MoveArmToEncoderPosition(double encoderValue) {
        requires(Robot.arm);
        this.encoderValue = encoderValue;
        if (encoderValue > RobotMap.ArmConstants.FORWARD_LIMIT_POSITION) {
            this.encoderValue = RobotMap.ArmConstants.FORWARD_LIMIT_POSITION;
        }
        if (encoderValue < RobotMap.ArmConstants.REVERSE_LIMIT_POSITION) {
            this.encoderValue = RobotMap.ArmConstants.REVERSE_LIMIT_POSITION;
        }
        this.setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        double direction = Math.signum(encoderValue - Math.abs(Robot.arm.getArmEncoderValue()));
        Robot.arm.moveArm(direction * RobotMap.ArmConstants.ARM_AUTONOMOUS_MOVEMENT_POWER, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean onTarget = Math.abs(encoderValue - Math.abs(Robot.arm.getArmEncoderValue())) <= RobotMap.ArmConstants.ARM_ENCODER_BUFFER;
        boolean hasBeenZeroed = Robot.arm.hasButtonBeenPressed();
        return this.isTimedOut() || !hasBeenZeroed || onTarget;
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
