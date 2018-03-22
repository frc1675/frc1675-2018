package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmToEncoderPosition extends Command {

    double setpoint;
    double initialPos;

    public MoveArmToEncoderPosition(double encoderValue) {
        requires(Robot.arm);
        this.setpoint = encoderValue;
        if (encoderValue > RobotMap.ArmConstants.FORWARD_LIMIT_POSITION) {
            this.setpoint = RobotMap.ArmConstants.FORWARD_LIMIT_POSITION;
        }
        if (encoderValue < 0) {
            this.setpoint = 0;
        }
        this.setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        initialPos = Math.abs(Robot.arm.getArmEncoderValue());
        double direction = Math.signum(setpoint - Math.abs(Robot.arm.getArmEncoderValue()));
        Robot.arm.moveArm(direction * RobotMap.ArmConstants.ARM_AUTONOMOUS_MOVEMENT_POWER, 1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //boolean onTarget = Math.abs(
                //encoderValue - Math.abs(Robot.arm.getArmEncoderValue())) <= RobotMap.ArmConstants.ARM_ENCODER_BUFFER;
        boolean hasBeenZeroed = Robot.arm.hasButtonBeenPressed();
        boolean onTarget = false;
        if(initialPos < setpoint) {
            onTarget = Math.abs(Robot.arm.getArmEncoderValue()) >= setpoint;
        }else if(initialPos >= setpoint) {
            onTarget = Math.abs(Robot.arm.getArmEncoderValue()) <= setpoint;
        }
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
