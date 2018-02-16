package org.usfirst.frc.team1675.robot.commands;

import org.usfirst.frc.team1675.robot.Robot;
import org.usfirst.frc.team1675.robot.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveArm extends Command {

    double armPower;
    PowerDistributionPanel pdp;
    

    public MoveArm() {
        requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        pdp = new PowerDistributionPanel();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putNumber("Encoder Position", Robot.arm.getEncoderPosition());
        armPower = Robot.oi.getOperatorRightYAxis();
        if (armPower == 0) {
            //Robot.arm.moveArm(RobotMap.ArmConstants.INITIAL_ARM_HOLD_VOLTAGE * Math.cos(Math.PI/180 * Robot.arm.getArmAngle()));
//            Robot.arm.moveArm(.08 * (12.0 / pdp.getVoltage())); <-- if robot does not have cube but other works for both
            Robot.arm.moveArm(.1 * (12.0 / pdp.getVoltage()));
        }
        else {
//          Robot.arm.moveArm(armPower * .08 * (12.0 / pdp.getVoltage()));
            Robot.arm.moveArm(.8 * armPower);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.arm.moveArm(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
